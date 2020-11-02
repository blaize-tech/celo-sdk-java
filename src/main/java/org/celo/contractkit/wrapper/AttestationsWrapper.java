package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Attestations;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

/**
 * Contract for handling deposits needed for voting.
 * TODO add docs, utils methods
 */
public class AttestationsWrapper extends BaseWrapper<Attestations> {

    static final int DEFAULT_NUM_ATTESTATIONS_REQUIRED = 3;
    static final double DEFAULT_ATTESTATION_THRESHOLD = 0.25;

    public static class AttestationsStatus {
        public boolean isVerified;
        public int numAttestationsRemaining;
        public int total;
        public int completed;

        public AttestationsStatus(boolean isVerified, int numAttestationsRemaining, int total, int completed) {
            this.isVerified = isVerified;
            this.numAttestationsRemaining = numAttestationsRemaining;
            this.total = total;
            this.completed = completed;
        }
    }

    public static class AttestationStat {
        public int completed;
        public int total;

        public AttestationStat(int completed, int total) {
            this.completed = completed;
            this.total = total;
        }
    }

    public AttestationsWrapper(Attestations contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static AttestationsWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        Attestations contract = Attestations.load(contractAddress, web3j, transactionManager, gasProvider);
        return new AttestationsWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<Attestations> deploy() {
        return Attestations.deploy(web3j, transactionManager, gasProvider);
    }

    public static AttestationsStatus isAccountConsideredVerified(AttestationStat stats) {
        return isAccountConsideredVerified(stats, DEFAULT_NUM_ATTESTATIONS_REQUIRED, DEFAULT_ATTESTATION_THRESHOLD);
    }

    /**
     * Returns true if an AttestationStat is considered verified using the given factors,
     * or defaults if factors are ommited.
     * @param stats AttestationStat of the account's attestation identitifer, retrievable via lookupIdentitfiers
     * @param numAttestationsRequired Optional number of attestations required.  Will default to
     *  hardcoded value if absent.
     * @param attestationThreshold Optional threshold for fraction attestations completed. Will
     *  default to hardcoded value if absent.
     */
    public static AttestationsStatus isAccountConsideredVerified(AttestationStat stats, int numAttestationsRequired, double attestationThreshold) {
        if (stats == null) {
            return new AttestationsStatus(false, 0, 0, 0);
        }

        int numAttestationsRemaining = numAttestationsRequired - stats.completed;
        double fractionAttestation = stats.total < 1 ? 0 : stats.completed * 1.0 / stats.total;
        // 'verified' is a term of convenience to mean that the attestation stats for a
        // given identifier are beyond a certain threshold of confidence
        boolean isVerified = numAttestationsRemaining <= 0 && fractionAttestation >= attestationThreshold;
        return new AttestationsStatus(isVerified, numAttestationsRemaining, stats.total, stats.completed);
    }

    public RemoteFunctionCall<BigInteger> attestationExpiryBlocks() {
        return contract.attestationExpiryBlocks();
    }

    public RemoteFunctionCall<BigInteger> attestationRequestFees(String param0) {
        return contract.attestationRequestFees(param0);
    }

    public AttestationsStatus getVerifiedStatus(byte[] identifier, String account) throws Exception {
        return getVerifiedStatus(identifier, account, DEFAULT_NUM_ATTESTATIONS_REQUIRED, DEFAULT_ATTESTATION_THRESHOLD);
    }

    public AttestationsStatus getVerifiedStatus(
            byte[] identifier,
            String account,
            int numAttestationsRequired,
            double attestationThreshold
    ) throws Exception {
        AttestationStat attestationStats = this.getAttestationStat(identifier, account);

        return AttestationsWrapper.isAccountConsideredVerified(
                attestationStats,
                numAttestationsRequired,
                attestationThreshold
        );
    }

    public RemoteFunctionCall<BigInteger> selectIssuersWaitBlocks() {
        return contract.selectIssuersWaitBlocks();
    }

    public RemoteFunctionCall<TransactionReceipt> selectIssuers(byte[] identifier) {
        return contract.selectIssuers(identifier);
    }

    public RemoteFunctionCall<TransactionReceipt> complete(byte[] identifier, BigInteger v, byte[] r, byte[] s) {
        return contract.complete(identifier, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> revoke(byte[] identifier, BigInteger index) {
        return contract.revoke(identifier, index);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>> getUnselectedRequest(byte[] identifier, String account) {
        return contract.getUnselectedRequest(identifier, account);
    }

    public RemoteFunctionCall<List> getAttestationIssuers(byte[] identifier, String account) {
        return contract.getAttestationIssuers(identifier, account);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getAttestationStats(byte[] identifier, String account) {
        return contract.getAttestationStats(identifier, account);
    }

    public AttestationStat getAttestationStat(byte[] identifier, String account) throws Exception {
        Tuple2<BigInteger, BigInteger> attestationStats = this.getAttestationStats(identifier, account).send();

        return new AttestationStat(attestationStats.component1().intValue(), attestationStats.component2().intValue());
    }

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>>> batchGetAttestationStats(List<byte[]> identifiersToLookup) {
        return contract.batchGetAttestationStats(identifiersToLookup);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>> getAttestationState(byte[] identifier, String account, String issuer) {
        return contract.getAttestationState(identifier, account, issuer);
    }

    public RemoteFunctionCall<BigInteger> getAttestationRequestFee(String token) {
        return contract.getAttestationRequestFee(token);
    }

    public RemoteFunctionCall<List> lookupAccountsForIdentifier(byte[] identifier) {
        return contract.lookupAccountsForIdentifier(identifier);
    }
}
