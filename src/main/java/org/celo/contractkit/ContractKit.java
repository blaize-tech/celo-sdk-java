package org.celo.contractkit;

import org.celo.contractkit.contract.GasPriceMinimum;
import org.celo.contractkit.contract.GoldToken;
import org.celo.contractkit.contract.LockedGold;
import org.celo.contractkit.contract.StableToken;
import org.celo.contractkit.protocol.CeloRawTransaction;
import org.celo.contractkit.protocol.CeloTransaction;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.celo.contractkit.wrapper.GasPriceMinimumWrapper;
import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.celo.contractkit.wrapper.LockedGoldWrapper;
import org.celo.contractkit.wrapper.StableTokenWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ContractKit {
    public static final String ALFAJORES_TESTNET = "https://alfajores-forno.celo-testnet.org";
    public static final String BAKLAVA_TESTNET = "https://baklava-blockscout.celo-testnet.org";
    public static final String MAINNET = "https://rc1-forno.celo-testnet.org";

    public static final long CHAIN_ID = 44787;

    private static final Logger logger = LoggerFactory.getLogger(ContractKit.class);

    public final Web3j web3j;
    public final Credentials credentials;
    public final WrapperCache contracts;
    public final ContractKitOptions config;
    public final CeloTransactionManager transactionManager;

    public ContractKit(Web3j web3j) {
        this(web3j, null, null);
    }

    public ContractKit(Web3j web3j, Credentials credentials) {
        this(web3j, credentials, null);
    }

    public ContractKit(Web3j web3j, Credentials credentials, ContractKitOptions config) {
        this.web3j = web3j;
        this.config = config != null ? config : ContractKitOptions.DEFAULT;
        this.credentials = credentials;

        // TODO add chain id
        this.transactionManager = new CeloTransactionManager(web3j, this.credentials, this.config.from, CHAIN_ID, this.config.gasInflationFactor);
        this.contracts = new WrapperCache(web3j, transactionManager);

        setFeeCurrency(this.config.feeCurrency);
    }

    public static ContractKit build(Web3jService web3jService) {
        return new ContractKit(Web3j.build(web3jService));
    }

    public void setFeeCurrency(CeloContract token) {
        String contractAddress = contracts.addressFor(token);
        transactionManager.setFeeCurrency(contractAddress);
        contracts.setFeeCurrency(contractAddress);
    }

    public void setGatewayFeeRecipient(String gatewayFeeRecipient) {
        transactionManager.setGatewayFeeRecipient(gatewayFeeRecipient);
    }

    public void setGatewayFee(BigInteger gatewayFee) {
        transactionManager.setGatewayFee(gatewayFee);
    }

    //TODO add celo exception
    public AccountBalance getTotalBalance(String address) throws Exception {
        GoldTokenWrapper celoToken = contracts.getGoldToken();
        StableTokenWrapper stableToken = contracts.getStableToken();
        LockedGoldWrapper lockedCelo = contracts.getLockedGold();

        BigInteger pending = BigInteger.ZERO;
        try {
            pending = lockedCelo.getTotalPendingWithdrawals(address).send();
        } catch (Exception error) {
            // Just means that it's not an account
        }

        BigInteger goldBalance = celoToken.balanceOf(address);
        BigInteger lockedBalance = lockedCelo.getAccountTotalLockedGold(address).send();
        BigInteger dollarBalance = stableToken.balanceOf(address).send();

        return new AccountBalance.Builder()
                .setCELO(goldBalance)
                .setLockedCELO(lockedBalance)
                .setcUSD(dollarBalance)
                .setPending(pending)
                .createAccountBalance();
    }

    //TODO add celo exception
    public BigInteger getGasPriceMinimum(String feeCurrency, BigInteger defaultGasPrice) throws Exception {
        if (feeCurrency != null && !feeCurrency.isEmpty() && BigInteger.ZERO.equals(defaultGasPrice)) {
            GasPriceMinimumWrapper gasPriceMinimum = contracts.getGasPriceMinimum();
            BigInteger minimalGasPrice = gasPriceMinimum.getGasPriceMinimum(feeCurrency).send();
            return BigDecimal.valueOf(minimalGasPrice.doubleValue() * config.gasPriceSuggestionMultiplier).toBigInteger();
        }
        return defaultGasPrice;
    }

    /**
     * Send a transaction to celo-blockchain.
     * <p>
     * Similar to `web3.eth.sendTransaction()` but with following differences:
     * - applies kit tx's defaults
     * - estimatesGas before sending
     * TODO add celo exception
     */
    public EthSendTransaction sendTransaction(CeloRawTransaction tx) throws Exception {
        String feeCurrency = tx.getFeeCurrency() != null ? tx.getFeeCurrency() : contracts.addressFor(config.feeCurrency);
        BigInteger minimalGasPrice = getGasPriceMinimum(feeCurrency, config.gasPrice);

        CeloTransaction callTransaction = CeloTransaction.createCeloCallTransaction(config.from, tx.getTo(), tx.getData());
        EthEstimateGas estimateGas = web3j.ethEstimateGas(callTransaction).send();
        BigInteger gasLimit = BigDecimal.valueOf(estimateGas.getAmountUsed().doubleValue() * config.gasInflationFactor).toBigInteger();
        logger.debug("Estimated Gas, {}", gasLimit);

        return transactionManager.sendTransaction(
                minimalGasPrice,
                gasLimit,
                tx.getTo() != null ? tx.getTo() : credentials.getAddress(),
                tx.getData(),
                tx.getValue()
        );
    }
}
