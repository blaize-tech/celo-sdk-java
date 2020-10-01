package org.celo.contractkit.protocol;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.RawTransaction;
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

public class CeloTransactionManager extends TransactionManager {

    private final Web3j web3j;
    private final Credentials credentials;

    private final double gasInflationFactor;
    private final long chainId;

    private String feeCurrency;
    private String gatewayFeeRecipient;
    private BigInteger gatewayFee;

    protected TxHashVerifier txHashVerifier = new TxHashVerifier();

    public CeloTransactionManager(Web3j web3j, Credentials credentials, String from, long chainId, double gasInflationFactor) {
        super(web3j, credentials != null ? credentials.getAddress() : from);

        this.web3j = web3j;
        this.credentials = credentials;
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

    protected BigInteger getNonce() throws IOException {
        EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(
                        credentials.getAddress(), DefaultBlockParameterName.PENDING)
                        .send();

        return ethGetTransactionCount.getTransactionCount();
    }

    /*
     * @param rawTransaction a RawTransaction instance to be signed
     * @return The transaction signed and encoded without ever broadcasting it
     */
    public String sign(CeloRawTransaction rawTransaction) {
        byte[] signedMessage = CeloTransactionEncoder.signMessage(rawTransaction, chainId, credentials);
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

        if (credentials == null) {
            throw new UnsupportedOperationException(
                    "Only read operations are supported by this transaction manager");
        }

        BigInteger nonce = getNonce();

        RawTransaction rawTransaction =
                RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);

        return signAndSend(rawTransaction);
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

        if (credentials == null) {
            throw new UnsupportedOperationException(
                    "Only read operations are supported by this transaction manager");
        }

        BigInteger nonce = getNonce();

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
