package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.SortedOracles;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.celo.contractkit.Utils.Fixidity.fromFixed;
import static org.celo.contractkit.Utils.Fixidity.toFixed;

public class SortedOraclesWrapper extends BaseWrapper<SortedOracles> {
    public enum MedianRelation {
        Undefined(0),
        Lesser(1),
        Greater(2),
        Equal(3);

        public int id;

        MedianRelation(int id) {
            this.id = id;
        }

        public static MedianRelation fromId(BigInteger id) {
            int intId = id.intValue();
            for (MedianRelation type : values()) {
                if (type.id == intId) {
                    return type;
                }
            }
            return null;
        }
    }



    public static class SortedOraclesConfig {
        public final BigInteger reportExpirySeconds;

        public SortedOraclesConfig(BigInteger reportExpirySeconds) {
            this.reportExpirySeconds = reportExpirySeconds;
        }
    }

    public static class OracleRate {
        public final String address;
        public final BigInteger rate;
        public final MedianRelation medianRelation;

        public OracleRate(String address, BigInteger rate, MedianRelation medianRelation) {
            this.address = address;
            this.rate = rate;
            this.medianRelation = medianRelation;
        }
    }

    public static class OracleTimestamp {
        public final String address;
        public final BigInteger timestamp;
        public final MedianRelation medianRelation;

        public OracleTimestamp(String address, BigInteger timestamp, MedianRelation medianRelation) {
            this.address = address;
            this.timestamp = timestamp;
            this.medianRelation = medianRelation;
        }
    }

    public static class OracleReport {
        public final String address;
        public final BigInteger rate;
        public final BigInteger timestamp;

        public OracleReport(String address, BigInteger rate, BigInteger timestamp) {
            this.address = address;
            this.rate = rate;
            this.timestamp = timestamp;
        }
    }

    public static class Keys {
        public final String greaterKey;
        public final String lesserKey;

        public Keys(String greaterKey, String lesserKey) {
            this.greaterKey = greaterKey;
            this.lesserKey = lesserKey;
        }
    }

    public static class MedianRate {
        public final BigInteger rate;

        public MedianRate(BigInteger rate) {
            this.rate = rate;
        }
    }

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

    public Keys findLesserAndGreaterKeys(String tokenAddress, BigInteger value, String oracleAddress) throws Exception {
        List<OracleRate> currentRates = this.getRates(tokenAddress);
        String greaterKey = "0x0";
        String lesserKey = "0x0";

        // This leverages the fact that the currentRates are already sorted from
        // greatest to lowest value
        for (OracleRate rate : currentRates) {
           if (!rate.address.equalsIgnoreCase(oracleAddress)) {
               if (rate.rate.compareTo(value) < 0) {
                   lesserKey = rate.address;
                   break;
               }
               greaterKey = rate.address;

           }
        }
        return new Keys(greaterKey, lesserKey);
    }

    public RemoteFunctionCall<TransactionReceipt> report(String tokenAddress, BigInteger value, String oracleAddress) throws Exception {
        BigInteger fixedValue = toFixed(value);

        Keys keys = this.findLesserAndGreaterKeys(tokenAddress, value, oracleAddress);

        return contract.report(tokenAddress, fixedValue, keys.lesserKey, keys.greaterKey);
    }

    public RemoteFunctionCall<BigInteger> numRates(String token) {
        return contract.numRates(token);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> medianRate(String token) {
        return contract.medianRate(token);
    }

    public List<OracleRate> getRates(String token) throws Exception {
        Tuple3<List<String>, List<BigInteger>, List<BigInteger>> response = contract.getRates(token).send();

        List<OracleRate> rates = new ArrayList<>();
        for (int i = 0; i < response.component1().size(); i++) {
            MedianRelation medRelIndex = MedianRelation.fromId(response.component3().get(i));
            BigInteger rate = fromFixed(response.component2().get(i));
            rates.add(new OracleRate(response.component1().get(i), rate, medRelIndex));
        }
        return rates;
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
