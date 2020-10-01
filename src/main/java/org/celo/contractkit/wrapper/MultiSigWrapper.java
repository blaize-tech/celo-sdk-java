package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.MultiSig;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

public class MultiSigWrapper extends BaseWrapper<MultiSig> {
    public MultiSigWrapper(MultiSig contract) {
        super(contract);
    }

    public RemoteFunctionCall<BigInteger> MAX_OWNER_COUNT() {
        return contract.MAX_OWNER_COUNT();
    }

    public RemoteFunctionCall<Boolean> confirmations(BigInteger param0, String param1) {
        return contract.confirmations(param0, param1);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<BigInteger> internalRequired() {
        return contract.internalRequired();
    }

    public RemoteFunctionCall<Boolean> isOwner(String param0) {
        return contract.isOwner(param0);
    }

    public RemoteFunctionCall<String> owners(BigInteger param0) {
        return contract.owners(param0);
    }

    public RemoteFunctionCall<BigInteger> required() {
        return contract.required();
    }

    public RemoteFunctionCall<BigInteger> transactionCount() {
        return contract.transactionCount();
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, byte[], Boolean>> transactions(BigInteger param0) {
        return contract.transactions(param0);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(List<String> _owners, BigInteger _required, BigInteger _internalRequired) {
        return contract.initialize(_owners, _required, _internalRequired);
    }

    public RemoteFunctionCall<TransactionReceipt> addOwner(String owner) {
        return contract.addOwner(owner);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOwner(String owner) {
        return contract.removeOwner(owner);
    }

    public RemoteFunctionCall<TransactionReceipt> replaceOwner(String owner, String newOwner) {
        return contract.replaceOwner(owner, newOwner);
    }

    public RemoteFunctionCall<TransactionReceipt> changeRequirement(BigInteger _required) {
        return contract.changeRequirement(_required);
    }

    public RemoteFunctionCall<TransactionReceipt> changeInternalRequirement(BigInteger _internalRequired) {
        return contract.changeInternalRequirement(_internalRequired);
    }

    public RemoteFunctionCall<TransactionReceipt> submitTransaction(String destination, BigInteger value, byte[] data) {
        return contract.submitTransaction(destination, value, data);
    }

    public RemoteFunctionCall<TransactionReceipt> confirmTransaction(BigInteger transactionId) {
        return contract.confirmTransaction(transactionId);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeConfirmation(BigInteger transactionId) {
        return contract.revokeConfirmation(transactionId);
    }

    public RemoteFunctionCall<TransactionReceipt> executeTransaction(BigInteger transactionId) {
        return contract.executeTransaction(transactionId);
    }

    public RemoteFunctionCall<Boolean> isConfirmed(BigInteger transactionId) {
        return contract.isConfirmed(transactionId);
    }

    public RemoteFunctionCall<BigInteger> getConfirmationCount(BigInteger transactionId) {
        return contract.getConfirmationCount(transactionId);
    }

    public RemoteFunctionCall<BigInteger> getTransactionCount(Boolean pending, Boolean executed) {
        return contract.getTransactionCount(pending, executed);
    }

    public RemoteFunctionCall<List> getOwners() {
        return contract.getOwners();
    }

    public RemoteFunctionCall<List> getConfirmations(BigInteger transactionId) {
        return contract.getConfirmations(transactionId);
    }

    public RemoteFunctionCall<List> getTransactionIds(BigInteger from, BigInteger to, Boolean pending, Boolean executed) {
        return contract.getTransactionIds(from, to, pending, executed);
    }

}
