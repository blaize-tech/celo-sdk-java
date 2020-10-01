package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.LockedGold;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

public class LockedGoldWrapper extends BaseWrapper<LockedGold> {
    public LockedGoldWrapper(LockedGold contract) {
        super(contract);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<String> owner() {
        return contract.owner();
    }

    public RemoteFunctionCall<String> registry() {
        return contract.registry();
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        return contract.renounceOwnership();
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistry(String registryAddress) {
        return contract.setRegistry(registryAddress);
    }

    public RemoteFunctionCall<byte[]> slashingWhitelist(BigInteger param0) {
        return contract.slashingWhitelist(param0);
    }

    public RemoteFunctionCall<BigInteger> totalNonvoting() {
        return contract.totalNonvoting();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<BigInteger> unlockingPeriod() {
        return contract.unlockingPeriod();
    }

    public RemoteFunctionCall<Boolean> isSlasher(String slasher) {
        return contract.isSlasher(slasher);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        return contract.getVersionNumber();
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger _unlockingPeriod) {
        return contract.initialize(registryAddress, _unlockingPeriod);
    }

    public RemoteFunctionCall<TransactionReceipt> setUnlockingPeriod(BigInteger value) {
        return contract.setUnlockingPeriod(value);
    }

    public RemoteFunctionCall<TransactionReceipt> lock(BigInteger weiValue) {
        return contract.lock(weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> incrementNonvotingAccountBalance(String account, BigInteger value) {
        return contract.incrementNonvotingAccountBalance(account, value);
    }

    public RemoteFunctionCall<TransactionReceipt> decrementNonvotingAccountBalance(String account, BigInteger value) {
        return contract.decrementNonvotingAccountBalance(account, value);
    }

    public RemoteFunctionCall<TransactionReceipt> unlock(BigInteger value) {
        return contract.unlock(value);
    }

    public RemoteFunctionCall<TransactionReceipt> relock(BigInteger index, BigInteger value) {
        return contract.relock(index, value);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger index) {
        return contract.withdraw(index);
    }

    public RemoteFunctionCall<BigInteger> getTotalLockedGold() {
        return contract.getTotalLockedGold();
    }

    public RemoteFunctionCall<BigInteger> getNonvotingLockedGold() {
        return contract.getNonvotingLockedGold();
    }

    public RemoteFunctionCall<BigInteger> getAccountTotalLockedGold(String account) {
        return contract.getAccountTotalLockedGold(account);
    }

    public RemoteFunctionCall<BigInteger> getAccountNonvotingLockedGold(String account) {
        return contract.getAccountNonvotingLockedGold(account);
    }

    public RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>> getPendingWithdrawals(String account) {
        return contract.getPendingWithdrawals(account);
    }

    public RemoteFunctionCall<BigInteger> getTotalPendingWithdrawals(String account) {
        return contract.getTotalPendingWithdrawals(account);
    }

    public RemoteFunctionCall<List> getSlashingWhitelist() {
        return contract.getSlashingWhitelist();
    }

    public RemoteFunctionCall<TransactionReceipt> addSlasher(String slasherIdentifier) {
        return contract.addSlasher(slasherIdentifier);
    }

    public RemoteFunctionCall<TransactionReceipt> removeSlasher(String slasherIdentifier, BigInteger index) {
        return contract.removeSlasher(slasherIdentifier, index);
    }

    public RemoteFunctionCall<TransactionReceipt> slash(String account, BigInteger penalty, String reporter, BigInteger reward, List<String> lessers, List<String> greaters, List<BigInteger> indices) {
        return contract.slash(account, penalty, reporter, reward, lessers, greaters, indices);
    }
}
