package org.celo.contractkit.wrapper;

import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;

import java.math.BigInteger;

public abstract class BaseWrapper<T extends Contract> {

    protected final T contract;
    protected final Web3j web3j;
    protected final CeloTransactionManager transactionManager;
    protected final CeloGasProvider gasProvider;

    public BaseWrapper(T contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        this.contract = contract;
        this.web3j = web3j;
        this.transactionManager = transactionManager;
        this.gasProvider = gasProvider;
    }

    public T getContract() {
        return contract;
    }

    public String getContractAddress() {
        return getContract().getContractAddress();
    }

    public abstract RemoteCall<T> deploy();

    public static BigInteger valueToFrac(BigInteger numerator, BigInteger denominator) {
        return numerator.divide(denominator);
    }
}
