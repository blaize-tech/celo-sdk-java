package org.celo.contractkit.protocol;

import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;

/**
 * Custom transaction object with CELO specific fields.
 *
 * This means that using web3.eth.sendTransaction or myContract.methods.transfer().send() should be avoided.
 * Instead, kit provides an utility method to send transaction in both scenarios. If you use contract wrappers, there is no need to use this.
 */
public class CeloTransaction extends Transaction {
    /**
     * Address of the ERC20 contract to use to pay for gas and the gateway fee
     */
    private String feeCurrency;
    /**
     * Coinbase address of the full serving the light client's transactions
     */
    private String gatewayFeeRecipient;
    /**
     * Value paid to the gateway fee recipient, denominated in the fee currency
     */
    private BigInteger gatewayFee;

    public CeloTransaction(String from, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, BigInteger value, String data, String feeCurrency, String gatewayFeeRecipient, BigInteger gatewayFee) {
        super(from, nonce, gasPrice, gasLimit, to, value, data);
        this.feeCurrency = feeCurrency;
        this.gatewayFeeRecipient = gatewayFeeRecipient;
        this.gatewayFee = gatewayFee;
    }

    public CeloTransaction(String from, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, BigInteger value, String data, BigInteger gasPremium, BigInteger feeCap, String feeCurrency, String gatewayFeeRecipient, BigInteger gatewayFee) {
        super(from, nonce, gasPrice, gasLimit, to, value, data, gasPremium, feeCap);
        this.feeCurrency = feeCurrency;
        this.gatewayFeeRecipient = gatewayFeeRecipient;
        this.gatewayFee = gatewayFee;
    }

    public static CeloTransaction createCeloCallTransaction(String from, String to, String data) {

        return new CeloTransaction(from, null, null, null, to, null, data, null, null, null);
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public String getGatewayFeeRecipient() {
        return gatewayFeeRecipient;
    }

    public void setGatewayFeeRecipient(String gatewayFeeRecipient) {
        this.gatewayFeeRecipient = gatewayFeeRecipient;
    }

    public BigInteger getGatewayFee() {
        return gatewayFee;
    }

    public void setGatewayFee(BigInteger gatewayFee) {
        this.gatewayFee = gatewayFee;
    }

    public BigInteger getGatewayFeeRaw() {
        return gatewayFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CeloTransaction that = (CeloTransaction) o;

        if (getFeeCurrency() != null
                ? !getFeeCurrency().equals(that.getFeeCurrency())
                : that.getFeeCurrency() != null) {
            return false;
        }

        if (getGatewayFeeRecipient() != null
                ? !getGatewayFeeRecipient().equals(that.getGatewayFeeRecipient())
                : that.getGatewayFeeRecipient() != null) {
            return false;
        }

        return getGatewayFee() != null
                ? getGatewayFee().equals(that.getGatewayFee())
                : that.getGatewayFee() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFeeCurrency() != null ? getFeeCurrency().hashCode() : 0);
        result = 31 * result + (getGatewayFeeRecipient() != null ? getGatewayFeeRecipient().hashCode() : 0);
        result = 31 * result + (getGatewayFee() != null ? getGatewayFee().hashCode() : 0);
        return result;
    }
}
