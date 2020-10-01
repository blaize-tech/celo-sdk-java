package org.celo.contractkit.protocol;

import java.math.BigInteger;

public class CeloRawTransactionBuilder {
    private BigInteger nonce;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private String to;
    private BigInteger value;
    private String data;
    private String feeCurrency;
    private String gatewayFeeRecipient;
    private BigInteger gatewayFee;

    public CeloRawTransactionBuilder setNonce(BigInteger nonce) {
        this.nonce = nonce;
        return this;
    }

    public CeloRawTransactionBuilder setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
        return this;
    }

    public CeloRawTransactionBuilder setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
        return this;
    }

    public CeloRawTransactionBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public CeloRawTransactionBuilder setValue(BigInteger value) {
        this.value = value;
        return this;
    }

    public CeloRawTransactionBuilder setData(String data) {
        this.data = data;
        return this;
    }

    public CeloRawTransactionBuilder setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
        return this;
    }

    public CeloRawTransactionBuilder setGatewayFeeRecipient(String gatewayFeeRecipient) {
        this.gatewayFeeRecipient = gatewayFeeRecipient;
        return this;
    }

    public CeloRawTransactionBuilder setGatewayFee(BigInteger gatewayFee) {
        this.gatewayFee = gatewayFee;
        return this;
    }

    public CeloRawTransaction build() {
        return new CeloRawTransaction(nonce, gasPrice, gasLimit, to, value, data, feeCurrency, gatewayFeeRecipient, gatewayFee);
    }
}