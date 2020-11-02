package org.celo.contractkit;

import org.celo.contractkit.contract.GoldToken;
import org.celo.contractkit.protocol.CeloRawTransaction;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.ContractKitOptions.GANACHE_CHAIN_ID;
import static org.celo.contractkit.ContractKitOptions.GANACHE_OPTIONS;
import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.celo.contractkit.TestData.PUBLIC_KEY_2;
import static org.celo.contractkit.protocol.CeloGasProvider.GAS_LIMIT;
import static org.celo.contractkit.protocol.CeloGasProvider.GAS_PRICE;
import static org.junit.Assert.*;

public class ContractKitTest {
    ContractKit contractKit;
    Web3j web3j;
    List<String> accounts;

    BigDecimal toEther(BigInteger value) {
        return Convert.fromWei(new BigDecimal(value), Convert.Unit.ETHER);
    }

    @Before
    public void initialize() throws IOException {
        web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);

        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testContractKitSetup() {
        assertNotNull(contractKit.web3j);
    }

    @Test
    public void testGetTotalBalance() throws Exception {
        AccountBalance balance = contractKit.getTotalBalance(contractKit.getAddress());

        assertEquals(1, toEther(balance.CELO).compareTo(BigDecimal.valueOf(0.1)));
        assertEquals(1, toEther(balance.cUSD).compareTo(BigDecimal.valueOf(0.1)));
        assertTrue(balance.lockedCELO.signum() >= 0);
        assertTrue(balance.pending.signum() >= 0);
    }

    @Test
    public void testGetGasPriceMinimum() throws Exception {
        BigInteger oneGwei = Convert.toWei(BigDecimal.ONE, Convert.Unit.GWEI).toBigInteger();

        CeloRawTransaction tx = CeloRawTransaction.createCeloTransaction(
                BigInteger.ZERO,
                GAS_PRICE,
                GAS_LIMIT,
                PUBLIC_KEY_2,
                oneGwei,
                contractKit.contracts.addressFor(CeloContract.StableToken),
                null,
                null
        );
        BigInteger gasPrice = contractKit.getGasPriceMinimum(tx.getFeeCurrency(), BigInteger.ZERO);
        assertEquals(BigInteger.valueOf(8_500_000_000L), gasPrice);
    }

    @Test
    public void testContractDeploy() throws Exception {
        GoldToken deployedGoldenToken = contractKit.contracts.getGoldToken().deploy().send();

        assertTrue(deployedGoldenToken.getTransactionReceipt().isPresent());
        TransactionReceipt receipt = deployedGoldenToken.getTransactionReceipt().get();

        assertNotNull(receipt.getTransactionHash());
        assertEquals(contractKit.getAddress(), receipt.getFrom());
    }

    @Test
    public void testCustomConfig() throws Exception {
        // Change default config
        ContractKitOptions config = new ContractKitOptions.Builder()
                .setFeeCurrency(CeloContract.GoldToken)
                .setChainId(GANACHE_CHAIN_ID)
                .build();

        // Change default account
        ContractKit contractKit = new ContractKit(web3j, config);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[1]);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[2]);
        contractKit.setDefaultAccount(accounts.get(1));

        GoldToken deployedGoldenToken = contractKit.contracts.getGoldToken().deploy().send();

        assertTrue(deployedGoldenToken.getTransactionReceipt().isPresent());
        TransactionReceipt receipt = deployedGoldenToken.getTransactionReceipt().get();

        assertNotNull(receipt.getTransactionHash());
        assertEquals(accounts.get(1), receipt.getFrom());
    }
}
