package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.GasPriceMinimum;
import org.web3j.protocol.core.RemoteFunctionCall;

import java.math.BigInteger;

/**
 * Stores the gas price minimum
 */
public class GasPriceMinimumWrapper extends  BaseWrapper<GasPriceMinimum> {
    public GasPriceMinimumWrapper(GasPriceMinimum contract) {
        super(contract);
    }

    public RemoteFunctionCall<BigInteger> adjustmentSpeed() {
        return contract.adjustmentSpeed();
    }

    public RemoteFunctionCall<BigInteger> gasPriceMinimum() {
        return contract.gasPriceMinimum();
    }

    public RemoteFunctionCall<BigInteger> getGasPriceMinimum(String tokenAddress) {
        return contract.getGasPriceMinimum(tokenAddress);
    }

    public RemoteFunctionCall<BigInteger> gasPriceMinimumFloor() {
        return contract.gasPriceMinimumFloor();
    }

    public RemoteFunctionCall<BigInteger> targetDensity() {
        return contract.targetDensity();
    }
}
