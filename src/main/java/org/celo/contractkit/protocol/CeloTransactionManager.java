package org.celo.contractkit.protocol;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.exceptions.TxHashMismatchException;
import org.web3j.utils.Numeric;
import org.web3j.utils.TxHashVerifier;

import java.io.IOException;
import java.math.BigInteger;

import static org.celo.contractkit.ContractKitOptions.GANACHE_CHAIN_ID;

public class CeloTransactionManager extends TransactionManager {

    private final Web3j web3j;

    private final double gasInflationFactor;
    private final long chainId;
    public final CeloWallet wallet;

    private String feeCurrency;
    private String gatewayFeeRecipient;
    private BigInteger gatewayFee;

    protected TxHashVerifier txHashVerifier = new TxHashVerifier();

    public CeloTransactionManager(Web3j web3j, String from, long chainId, double gasInflationFactor) {
        super(web3j, from);

        this.web3j = web3j;
        this.wallet = new CeloWallet();

        this.gasInflationFactor = gasInflationFactor;
        this.chainId = chainId;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public void setGatewayFeeRecipient(String gatewayFeeRecipient) {
        this.gatewayFeeRecipient = gatewayFeeRecipient;
    }

    public void setGatewayFee(BigInteger gatewayFee) {
        this.gatewayFee = gatewayFee;
    }

    public Credentials getCredentials() {
        return wallet.getDefaultAccount();
    }

    public Credentials getCredentials(String from) {
        Credentials credentials = wallet.getKey(from);
        return credentials != null ? credentials : getCredentials();
    }

    protected BigInteger getNonce(String from) throws IOException {
        EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(
                        getCredentials(from).getAddress(), DefaultBlockParameterName.PENDING)
                        .send();

        return ethGetTransactionCount.getTransactionCount();
    }

    /*
     * @param rawTransaction a RawTransaction instance to be signed
     * @return The transaction signed and encoded without ever broadcasting it
     */
    public String sign(CeloRawTransaction rawTransaction) {
        String from = rawTransaction.getFrom();

        byte[] signedMessage = chainId != GANACHE_CHAIN_ID
                ? CeloTransactionEncoder.signMessage(rawTransaction, chainId, getCredentials(from))
                : TransactionEncoder.signMessage(rawTransaction, chainId, getCredentials(from));
        return Numeric.toHexString(signedMessage);
    }

    public EthSendTransaction signAndSend(CeloRawTransaction rawTransaction) throws IOException {
        String hexValue = sign(rawTransaction);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

        if (ethSendTransaction != null && !ethSendTransaction.hasError()) {
            String txHashLocal = Hash.sha3(hexValue);
            String txHashRemote = ethSendTransaction.getTransactionHash();
            if (!txHashVerifier.verify(txHashLocal, txHashRemote)) {
                throw new TxHashMismatchException(txHashLocal, txHashRemote);
            }
        }

        return ethSendTransaction;
    }

    public EthSendTransaction signAndSend(RawTransaction rawTransaction, String from) throws IOException {
        CeloRawTransaction celoRawTransaction = new CeloRawTransaction(
                rawTransaction.getNonce(),
                rawTransaction.getGasPrice(),
                rawTransaction.getGasLimit(),
                rawTransaction.getTo(),
                rawTransaction.getValue(),
                rawTransaction.getData(),
                rawTransaction.getGasPremium(),
                rawTransaction.getFeeCap(),
                feeCurrency,
                gatewayFeeRecipient,
                gatewayFee,
                from
        );

        return signAndSend(celoRawTransaction);
    }

    public EthSendTransaction signAndSend(RawTransaction rawTransaction) throws IOException {
        CeloRawTransaction celoRawTransaction = new CeloRawTransaction(
                rawTransaction.getNonce(),
                rawTransaction.getGasPrice(),
                rawTransaction.getGasLimit(),
                rawTransaction.getTo(),
                rawTransaction.getValue(),
                rawTransaction.getData(),
                rawTransaction.getGasPremium(),
                rawTransaction.getFeeCap(),
                feeCurrency,
                gatewayFeeRecipient,
                gatewayFee
        );

        return signAndSend(celoRawTransaction);
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor)
            throws IOException {

        if (getCredentials() == null) {
            throw new UnsupportedOperationException(
                    "Only read operations are supported by this transaction manager");
        }

        BigInteger nonce = getNonce(null);

        RawTransaction rawTransaction =
                RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);

        return signAndSend(rawTransaction);
    }

    public EthSendTransaction sendTransaction(
            BigInteger gasPrice,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            String from)
            throws IOException {

        if (getCredentials(from) == null) {
            throw new UnsupportedOperationException(
                    "Only read operations are supported by this transaction manager");
        }

        BigInteger nonce = getNonce(from);

        RawTransaction rawTransaction =
                RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);

        return signAndSend(rawTransaction, from);
    }

    @Override
    public EthSendTransaction sendTransactionEIP1559(
            BigInteger gasPremium,
            BigInteger feeCap,
            BigInteger gasLimit,
            String to,
            String data,
            BigInteger value,
            boolean constructor)
            throws IOException {

        if (getCredentials() == null) {
            throw new UnsupportedOperationException(
                    "Only read operations are supported by this transaction manager");
        }

        BigInteger nonce = getNonce(null);

        RawTransaction rawTransaction =
                RawTransaction.createTransaction(
                        nonce, null, gasLimit, to, value, data, gasPremium, feeCap);

        return signAndSend(rawTransaction);
    }

    @Override
    public String sendCall(String to, String data, DefaultBlockParameter defaultBlockParameter)
            throws IOException {
        EthCall ethCall =
                web3j.ethCall(
                        Transaction.createEthCallTransaction(getFromAddress(), to, data),
                        defaultBlockParameter)
                        .send();

        if (ethCall.isReverted()) {
            throw new ContractCallException(
                    String.format(REVERT_ERR_STR, ethCall.getRevertReason()));
        }

        return ethCall.getValue();
    }

    @Override
    public EthGetCode getCode(
            final String contractAddress, final DefaultBlockParameter defaultBlockParameter)
            throws IOException {
        return web3j.ethGetCode(contractAddress, defaultBlockParameter).send();
    }
}
