package org.celo.contractkit.wrapper;

import org.web3j.tx.Contract;

import java.math.BigInteger;

public class BaseWrapper<T extends Contract> {

    protected final T contract;

    public BaseWrapper(T contract) {
        this.contract = contract;
    }

    public T getContract() {
        return contract;
    }

    public static BigInteger valueToFrac(BigInteger numerator, BigInteger denominator) {
        return numerator.divide(denominator);
    }
}
