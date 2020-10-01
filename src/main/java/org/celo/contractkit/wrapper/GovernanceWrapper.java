package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Governance;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.List;

public class GovernanceWrapper extends BaseWrapper<Governance> {
    public GovernanceWrapper(Governance contract) {
        super(contract);
    }

    public RemoteFunctionCall<String> approver() {
        return contract.approver();
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        return contract.checkProofOfPossession(sender, blsKey, blsPop);
    }

    public RemoteFunctionCall<BigInteger> concurrentProposals() {
        return contract.concurrentProposals();
    }

    public RemoteFunctionCall<BigInteger> dequeueFrequency() {
        return contract.dequeueFrequency();
    }

    public RemoteFunctionCall<BigInteger> dequeued(BigInteger param0) {
        return contract.dequeued(param0);
    }

    public RemoteFunctionCall<BigInteger> emptyIndices(BigInteger param0) {
        return contract.emptyIndices(param0);
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

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>> hotfixes(byte[] param0) {
        return contract.hotfixes(param0);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<BigInteger> lastDequeue() {
        return contract.lastDequeue();
    }

    public RemoteFunctionCall<BigInteger> minDeposit() {
        return contract.minDeposit();
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

    public RemoteFunctionCall<BigInteger> proposalCount() {
        return contract.proposalCount();
    }

    public RemoteFunctionCall<BigInteger> queueExpiry() {
        return contract.queueExpiry();
    }

    public RemoteFunctionCall<BigInteger> refundedDeposits(String param0) {
        return contract.refundedDeposits(param0);
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

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> stageDurations() {
        return contract.stageDurations();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, String _approver, BigInteger _concurrentProposals, BigInteger _minDeposit, BigInteger _queueExpiry, BigInteger _dequeueFrequency, BigInteger approvalStageDuration, BigInteger referendumStageDuration, BigInteger executionStageDuration, BigInteger participationBaseline, BigInteger participationFloor, BigInteger baselineUpdateFactor, BigInteger baselineQuorumFactor) {
        return contract.initialize(registryAddress, _approver, _concurrentProposals, _minDeposit, _queueExpiry, _dequeueFrequency, approvalStageDuration, referendumStageDuration, executionStageDuration, participationBaseline, participationFloor, baselineUpdateFactor, baselineQuorumFactor);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprover(String _approver) {
        return contract.setApprover(_approver);
    }

    public RemoteFunctionCall<TransactionReceipt> setConcurrentProposals(BigInteger _concurrentProposals) {
        return contract.setConcurrentProposals(_concurrentProposals);
    }

    public RemoteFunctionCall<TransactionReceipt> setMinDeposit(BigInteger _minDeposit) {
        return contract.setMinDeposit(_minDeposit);
    }

    public RemoteFunctionCall<TransactionReceipt> setQueueExpiry(BigInteger _queueExpiry) {
        return contract.setQueueExpiry(_queueExpiry);
    }

    public RemoteFunctionCall<TransactionReceipt> setDequeueFrequency(BigInteger _dequeueFrequency) {
        return contract.setDequeueFrequency(_dequeueFrequency);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalStageDuration(BigInteger approvalStageDuration) {
        return contract.setApprovalStageDuration(approvalStageDuration);
    }

    public RemoteFunctionCall<TransactionReceipt> setReferendumStageDuration(BigInteger referendumStageDuration) {
        return contract.setReferendumStageDuration(referendumStageDuration);
    }

    public RemoteFunctionCall<TransactionReceipt> setExecutionStageDuration(BigInteger executionStageDuration) {
        return contract.setExecutionStageDuration(executionStageDuration);
    }

    public RemoteFunctionCall<TransactionReceipt> setParticipationBaseline(BigInteger participationBaseline) {
        return contract.setParticipationBaseline(participationBaseline);
    }

    public RemoteFunctionCall<TransactionReceipt> setParticipationFloor(BigInteger participationFloor) {
        return contract.setParticipationFloor(participationFloor);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaselineUpdateFactor(BigInteger baselineUpdateFactor) {
        return contract.setBaselineUpdateFactor(baselineUpdateFactor);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaselineQuorumFactor(BigInteger baselineQuorumFactor) {
        return contract.setBaselineQuorumFactor(baselineQuorumFactor);
    }

    public RemoteFunctionCall<TransactionReceipt> setConstitution(String destination, byte[] functionId, BigInteger threshold) {
        return contract.setConstitution(destination, functionId, threshold);
    }

    public RemoteFunctionCall<TransactionReceipt> propose(List<BigInteger> values, List<String> destinations, byte[] data, List<BigInteger> dataLengths, String descriptionUrl, BigInteger weiValue) {
        return contract.propose(values, destinations, data, dataLengths, descriptionUrl, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> upvote(BigInteger proposalId, BigInteger lesser, BigInteger greater) {
        return contract.upvote(proposalId, lesser, greater);
    }

    public RemoteFunctionCall<BigInteger> getProposalStage(BigInteger proposalId) {
        return contract.getProposalStage(proposalId);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeUpvote(BigInteger lesser, BigInteger greater) {
        return contract.revokeUpvote(lesser, greater);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(BigInteger proposalId, BigInteger index) {
        return contract.approve(proposalId, index);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposalId, BigInteger index, BigInteger value) {
        return contract.vote(proposalId, index, value);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(BigInteger proposalId, BigInteger index) {
        return contract.execute(proposalId, index);
    }

    public RemoteFunctionCall<TransactionReceipt> approveHotfix(byte[] hash) {
        return contract.approveHotfix(hash);
    }

    public RemoteFunctionCall<Boolean> isHotfixWhitelistedBy(byte[] hash, String whitelister) {
        return contract.isHotfixWhitelistedBy(hash, whitelister);
    }

    public RemoteFunctionCall<TransactionReceipt> whitelistHotfix(byte[] hash) {
        return contract.whitelistHotfix(hash);
    }

    public RemoteFunctionCall<TransactionReceipt> prepareHotfix(byte[] hash) {
        return contract.prepareHotfix(hash);
    }

    public RemoteFunctionCall<TransactionReceipt> executeHotfix(List<BigInteger> values, List<String> destinations, byte[] data, List<BigInteger> dataLengths, byte[] salt) {
        return contract.executeHotfix(values, destinations, data, dataLengths, salt);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        return contract.withdraw();
    }

    public RemoteFunctionCall<Boolean> isVoting(String account) {
        return contract.isVoting(account);
    }

    public RemoteFunctionCall<BigInteger> getApprovalStageDuration() {
        return contract.getApprovalStageDuration();
    }

    public RemoteFunctionCall<BigInteger> getReferendumStageDuration() {
        return contract.getReferendumStageDuration();
    }

    public RemoteFunctionCall<BigInteger> getExecutionStageDuration() {
        return contract.getExecutionStageDuration();
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getParticipationParameters() {
        return contract.getParticipationParameters();
    }

    public RemoteFunctionCall<Boolean> proposalExists(BigInteger proposalId) {
        return contract.proposalExists(proposalId);
    }

    public RemoteFunctionCall<Tuple5<String, BigInteger, BigInteger, BigInteger, String>> getProposal(BigInteger proposalId) {
        return contract.getProposal(proposalId);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, byte[]>> getProposalTransaction(BigInteger proposalId, BigInteger index) {
        return contract.getProposalTransaction(proposalId, index);
    }

    public RemoteFunctionCall<Boolean> isApproved(BigInteger proposalId) {
        return contract.isApproved(proposalId);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getVoteTotals(BigInteger proposalId) {
        return contract.getVoteTotals(proposalId);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getVoteRecord(String account, BigInteger index) {
        return contract.getVoteRecord(account, index);
    }

    public RemoteFunctionCall<BigInteger> getQueueLength() {
        return contract.getQueueLength();
    }

    public RemoteFunctionCall<BigInteger> getUpvotes(BigInteger proposalId) {
        return contract.getUpvotes(proposalId);
    }

    public RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>> getQueue() {
        return contract.getQueue();
    }

    public RemoteFunctionCall<List> getDequeue() {
        return contract.getDequeue();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getUpvoteRecord(String account) {
        return contract.getUpvoteRecord(account);
    }

    public RemoteFunctionCall<BigInteger> getMostRecentReferendumProposal(String account) {
        return contract.getMostRecentReferendumProposal(account);
    }

    public RemoteFunctionCall<BigInteger> hotfixWhitelistValidatorTally(byte[] hash) {
        return contract.hotfixWhitelistValidatorTally(hash);
    }

    public RemoteFunctionCall<Boolean> isHotfixPassing(byte[] hash) {
        return contract.isHotfixPassing(hash);
    }

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>> getHotfixRecord(byte[] hash) {
        return contract.getHotfixRecord(hash);
    }

    public RemoteFunctionCall<TransactionReceipt> dequeueProposalsIfReady() {
        return contract.dequeueProposalsIfReady();
    }

    public RemoteFunctionCall<Boolean> isQueued(BigInteger proposalId) {
        return contract.isQueued(proposalId);
    }

    public RemoteFunctionCall<Boolean> isProposalPassing(BigInteger proposalId) {
        return contract.isProposalPassing(proposalId);
    }

    public RemoteFunctionCall<Boolean> isDequeuedProposal(BigInteger proposalId, BigInteger index) {
        return contract.isDequeuedProposal(proposalId, index);
    }

    public RemoteFunctionCall<Boolean> isDequeuedProposalExpired(BigInteger proposalId) {
        return contract.isDequeuedProposalExpired(proposalId);
    }

    public RemoteFunctionCall<Boolean> isQueuedProposalExpired(BigInteger proposalId) {
        return contract.isQueuedProposalExpired(proposalId);
    }

    public RemoteFunctionCall<BigInteger> getConstitution(String destination, byte[] functionId) {
        return contract.getConstitution(destination, functionId);
    }
}
