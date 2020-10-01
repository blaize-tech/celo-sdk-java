package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Attestations;
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
    public AttestationsWrapper(Attestations contract) {
        super(contract);
    }

    public RemoteFunctionCall<BigInteger> attestationExpiryBlocks() {
        return contract.attestationExpiryBlocks();
    }

    public RemoteFunctionCall<BigInteger> attestationRequestFees(String param0) {
        return contract.attestationRequestFees(param0);
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
