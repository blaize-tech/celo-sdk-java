package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.GasPriceMinimum;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;

import java.math.BigInteger;

/**
 * Stores the gas price minimum
 */
public class GasPriceMinimumWrapper extends  BaseWrapper<GasPriceMinimum> {

    public GasPriceMinimumWrapper(GasPriceMinimum contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static GasPriceMinimumWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        GasPriceMinimum contract = GasPriceMinimum.load(contractAddress, web3j, transactionManager, gasProvider);
        return new GasPriceMinimumWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<GasPriceMinimum> deploy() {
        return GasPriceMinimum.deploy(web3j, transactionManager, gasProvider);
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
