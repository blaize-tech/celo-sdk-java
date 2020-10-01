package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.SortedOracles;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

public class SortedOraclesWrapper extends BaseWrapper<SortedOracles> {
    public SortedOraclesWrapper(SortedOracles contract) {
        super(contract);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOracle(String param0, String param1) {
        return contract.isOracle(param0, param1);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<String> oracles(String param0, BigInteger param1) {
        return contract.oracles(param0, param1);
    }

    public RemoteFunctionCall<String> owner() {
        return contract.owner();
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        return contract.renounceOwnership();
    }

    public RemoteFunctionCall<BigInteger> reportExpirySeconds() {
        return contract.reportExpirySeconds();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        return contract.getVersionNumber();
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(BigInteger _reportExpirySeconds) {
        return contract.initialize(_reportExpirySeconds);
    }

    public RemoteFunctionCall<TransactionReceipt> setReportExpiry(BigInteger _reportExpirySeconds) {
        return contract.setReportExpiry(_reportExpirySeconds);
    }

    public RemoteFunctionCall<TransactionReceipt> addOracle(String token, String oracleAddress) {
        return contract.addOracle(token, oracleAddress);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOracle(String token, String oracleAddress, BigInteger index) {
        return contract.removeOracle(token, oracleAddress, index);
    }

    public RemoteFunctionCall<TransactionReceipt> removeExpiredReports(String token, BigInteger n) {
        return contract.removeExpiredReports(token, n);
    }

    public RemoteFunctionCall<Tuple2<Boolean, String>> isOldestReportExpired(String token) {
        return contract.isOldestReportExpired(token);
    }

    public RemoteFunctionCall<TransactionReceipt> report(String token, BigInteger value, String lesserKey, String greaterKey) {
        return contract.report(token, value, lesserKey, greaterKey);
    }

    public RemoteFunctionCall<BigInteger> numRates(String token) {
        return contract.numRates(token);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> medianRate(String token) {
        return contract.medianRate(token);
    }

    public RemoteFunctionCall<Tuple3<List<String>, List<BigInteger>, List<BigInteger>>> getRates(String token) {
        return contract.getRates(token);
    }

    public RemoteFunctionCall<BigInteger> numTimestamps(String token) {
        return contract.numTimestamps(token);
    }

    public RemoteFunctionCall<BigInteger> medianTimestamp(String token) {
        return contract.medianTimestamp(token);
    }

    public RemoteFunctionCall<Tuple3<List<String>, List<BigInteger>, List<BigInteger>>> getTimestamps(String token) {
        return contract.getTimestamps(token);
    }

    public RemoteFunctionCall<List> getOracles(String token) {
        return contract.getOracles(token);
    }
}
