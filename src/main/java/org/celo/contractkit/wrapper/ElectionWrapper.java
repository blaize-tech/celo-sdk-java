package org.celo.contractkit.wrapper;

import io.reactivex.Flowable;
import org.celo.contractkit.contract.Election;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

/**
 * Contract for voting for validators and managing validator groups.
 * TODO add docs, utils methods
 */
public class ElectionWrapper extends BaseWrapper<Election> {

    public ElectionWrapper(Election contract) {
        super(contract);
    }

    public Flowable<Election.RegistrySetEventResponse> registrySetEventFlowable(EthFilter filter) {
        return contract.registrySetEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        return contract.checkProofOfPossession(sender, blsKey, blsPop);
    }

    public RemoteFunctionCall<BigInteger> electabilityThreshold() {
        return contract.electabilityThreshold();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> electableValidators() {
        return contract.electableValidators();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> fractionMulExp(BigInteger aNumerator, BigInteger aDenominator, BigInteger bNumerator, BigInteger bDenominator, BigInteger exponent, BigInteger _decimals) {
        return contract.fractionMulExp(aNumerator, aDenominator, bNumerator, bDenominator, exponent, _decimals);
    }

    public RemoteFunctionCall<BigInteger> getBlockNumberFromHeader(byte[] header) {
        return contract.getBlockNumberFromHeader(header);
    }

    public RemoteFunctionCall<BigInteger> getEpochNumber() {
        return contract.getEpochNumber();
    }

    public RemoteFunctionCall<BigInteger> getEpochNumberOfBlock(BigInteger blockNumber) {
        return contract.getEpochNumberOfBlock(blockNumber);
    }

    public RemoteFunctionCall<BigInteger> getEpochSize() {
        return contract.getEpochSize();
    }

    public RemoteFunctionCall<byte[]> getParentSealBitmap(BigInteger blockNumber) {
        return contract.getParentSealBitmap(blockNumber);
    }

    public RemoteFunctionCall<byte[]> getVerifiedSealBitmapFromHeader(byte[] header) {
        return contract.getVerifiedSealBitmapFromHeader(header);
    }

    public RemoteFunctionCall<byte[]> hashHeader(byte[] header) {
        return contract.hashHeader(header);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<BigInteger> maxNumGroupsVotedFor() {
        return contract.maxNumGroupsVotedFor();
    }

    public RemoteFunctionCall<BigInteger> minQuorumSize(BigInteger blockNumber) {
        return contract.minQuorumSize(blockNumber);
    }

    public RemoteFunctionCall<BigInteger> minQuorumSizeInCurrentSet() {
        return contract.minQuorumSizeInCurrentSet();
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInCurrentSet() {
        return contract.numberValidatorsInCurrentSet();
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInSet(BigInteger blockNumber) {
        return contract.numberValidatorsInSet(blockNumber);
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

    public RemoteFunctionCall<String> validatorSignerAddressFromCurrentSet(BigInteger index) {
        return contract.validatorSignerAddressFromCurrentSet(index);
    }

    public RemoteFunctionCall<String> validatorSignerAddressFromSet(BigInteger index, BigInteger blockNumber) {
        return contract.validatorSignerAddressFromSet(index, blockNumber);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        return contract.getVersionNumber();
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger minElectableValidators, BigInteger maxElectableValidators, BigInteger _maxNumGroupsVotedFor, BigInteger _electabilityThreshold) {
        return contract.initialize(registryAddress, minElectableValidators, maxElectableValidators, _maxNumGroupsVotedFor, _electabilityThreshold);
    }

    public RemoteFunctionCall<TransactionReceipt> setElectableValidators(BigInteger min, BigInteger max) {
        return contract.setElectableValidators(min, max);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getElectableValidators() {
        return contract.getElectableValidators();
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxNumGroupsVotedFor(BigInteger _maxNumGroupsVotedFor) {
        return contract.setMaxNumGroupsVotedFor(_maxNumGroupsVotedFor);
    }

    public RemoteFunctionCall<TransactionReceipt> setElectabilityThreshold(BigInteger threshold) {
        return contract.setElectabilityThreshold(threshold);
    }

    public RemoteFunctionCall<BigInteger> getElectabilityThreshold() {
        return contract.getElectabilityThreshold();
    }

    public RemoteFunctionCall<TransactionReceipt> vote(String group, BigInteger value, String lesser, String greater) {
        return contract.vote(group, value, lesser, greater);
    }

    public RemoteFunctionCall<TransactionReceipt> activate(String group) {
        return contract.activate(group);
    }

    public RemoteFunctionCall<Boolean> hasActivatablePendingVotes(String account, String group) {
        return contract.hasActivatablePendingVotes(account, group);
    }

    public RemoteFunctionCall<TransactionReceipt> revokePending(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        return contract.revokePending(group, value, lesser, greater, index);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeAllActive(String group, String lesser, String greater, BigInteger index) {
        return contract.revokeAllActive(group, lesser, greater, index);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeActive(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        return contract.revokeActive(group, value, lesser, greater, index);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesByAccount(String account) {
        return contract.getTotalVotesByAccount(account);
    }

    public RemoteFunctionCall<BigInteger> getPendingVotesForGroupByAccount(String group, String account) {
        return contract.getPendingVotesForGroupByAccount(group, account);
    }

    public RemoteFunctionCall<BigInteger> getActiveVotesForGroupByAccount(String group, String account) {
        return contract.getActiveVotesForGroupByAccount(group, account);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesForGroupByAccount(String group, String account) {
        return contract.getTotalVotesForGroupByAccount(group, account);
    }

    public RemoteFunctionCall<BigInteger> getActiveVoteUnitsForGroupByAccount(String group, String account) {
        return contract.getActiveVoteUnitsForGroupByAccount(group, account);
    }

    public RemoteFunctionCall<BigInteger> getActiveVoteUnitsForGroup(String group) {
        return contract.getActiveVoteUnitsForGroup(group);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesForGroup(String group) {
        return contract.getTotalVotesForGroup(group);
    }

    public RemoteFunctionCall<BigInteger> getActiveVotesForGroup(String group) {
        return contract.getActiveVotesForGroup(group);
    }

    public RemoteFunctionCall<BigInteger> getPendingVotesForGroup(String group) {
        return contract.getPendingVotesForGroup(group);
    }

    public RemoteFunctionCall<Boolean> getGroupEligibility(String group) {
        return contract.getGroupEligibility(group);
    }

    public RemoteFunctionCall<BigInteger> getGroupEpochRewards(String group, BigInteger totalEpochRewards, List<BigInteger> uptimes) {
        return contract.getGroupEpochRewards(group, totalEpochRewards, uptimes);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeEpochRewards(String group, BigInteger value, String lesser, String greater) {
        return contract.distributeEpochRewards(group, value, lesser, greater);
    }

    public RemoteFunctionCall<TransactionReceipt> markGroupIneligible(String group) {
        return contract.markGroupIneligible(group);
    }

    public RemoteFunctionCall<TransactionReceipt> markGroupEligible(String group, String lesser, String greater) {
        return contract.markGroupEligible(group, lesser, greater);
    }

    public RemoteFunctionCall<List> getGroupsVotedForByAccount(String account) {
        return contract.getGroupsVotedForByAccount(account);
    }

    public RemoteFunctionCall<Boolean> canReceiveVotes(String group, BigInteger value) {
        return contract.canReceiveVotes(group, value);
    }

    public RemoteFunctionCall<BigInteger> getNumVotesReceivable(String group) {
        return contract.getNumVotesReceivable(group);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotes() {
        return contract.getTotalVotes();
    }

    public RemoteFunctionCall<BigInteger> getActiveVotes() {
        return contract.getActiveVotes();
    }

    public RemoteFunctionCall<List> getEligibleValidatorGroups() {
        return contract.getEligibleValidatorGroups();
    }

    public RemoteFunctionCall<Tuple2<List<String>, List<BigInteger>>> getTotalVotesForEligibleValidatorGroups() {
        return contract.getTotalVotesForEligibleValidatorGroups();
    }

    public RemoteFunctionCall<List> electValidatorSigners() {
        return contract.electValidatorSigners();
    }

    public RemoteFunctionCall<List> electNValidatorSigners(BigInteger minElectableValidators, BigInteger maxElectableValidators) {
        return contract.electNValidatorSigners(minElectableValidators, maxElectableValidators);
    }

    public RemoteFunctionCall<List> getCurrentValidatorSigners() {
        return contract.getCurrentValidatorSigners();
    }

    public RemoteFunctionCall<TransactionReceipt> forceDecrementVotes(String account, BigInteger value, List<String> lessers, List<String> greaters, List<BigInteger> indices) {
        return contract.forceDecrementVotes(account, value, lessers, greaters, indices);
    }
}
