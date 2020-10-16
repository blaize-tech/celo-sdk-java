package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Governance;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GovernanceWrapper extends BaseWrapper<Governance> {
    public enum ProposalStage {
        None(0),
        Queued(1),
        Approval(2),
        Referendum(3),
        Execution(4),
        Expiration(5);

        public int id;

        ProposalStage(int id) {
            this.id = id;
        }

        public static ProposalStage fromId(BigInteger id) {
            int intId = id.intValue();
            for (ProposalStage type : values()) {
                if (type.id == intId) {
                    return type;
                }
            }
            return null;
        }
    }

    public static class ProposalMetadata {
        public final String proposer;
        public final BigInteger deposit;
        public final BigInteger timestamp;
        public final int transactionCount;
        public final String descriptionURL;

        public ProposalMetadata(String proposer, BigInteger deposit, BigInteger timestamp, int transactionCount, String descriptionURL) {
            this.proposer = proposer;
            this.deposit = deposit;
            this.timestamp = timestamp;
            this.transactionCount = transactionCount;
            this.descriptionURL = descriptionURL;
        }
    }

    enum VoteValue {
        NONE,
        Abstain,
        No,
        Yes,
    }

    public static class Transaction {
        public final BigInteger value;
        public final String destination;
        public final byte[] data;
        public final String to;
        public final String input;

        public Transaction(BigInteger value, String destination, byte[] data, String to, String input) {
            this.value = value;
            this.destination = destination;
            this.data = data;
            this.to = to;
            this.input = input;
        }
    }

    public static class Votes {
        public final BigInteger yes;
        public final BigInteger no;
        public final BigInteger abstain;

        public Votes() {
            this.yes = BigInteger.valueOf(0);
            this.no = BigInteger.valueOf(0);
            this.abstain = BigInteger.valueOf(0);
        }

        public Votes(BigInteger yes, BigInteger no, BigInteger abstain) {
            this.yes = yes;
            this.no = no;
            this.abstain = abstain;
        }
    }


    public static class ProposalRecord {
        public final ProposalStage stage;
        public final ProposalMetadata metadata;
        public final BigInteger upvotes;
        public final Votes votes;
        public final Proposal proposal;
        public final boolean passing;

        public ProposalRecord(ProposalStage stage, ProposalMetadata metadata, BigInteger upvotes, Votes votes, Proposal proposal, boolean passing) {
            this.stage = stage;
            this.metadata = metadata;
            this.upvotes = upvotes;
            this.votes = votes;
            this.proposal = proposal;
            this.passing = passing;
        }
    }

    public static class Proposal {
        public final List<Transaction> transactions;

        public Proposal() {
            this.transactions = new ArrayList<>();
        }

        public Proposal(List<Transaction> transactions) {
            this.transactions = transactions;
        }

        public void addTx() {

        }
    }

    public static class ProposalParams {
        public final List<BigInteger> values;
        public final List<String> destinations;
        public final byte[] data;
        public final List<BigInteger> dataLengths;
        public final String descriptionUrl;

        public ProposalParams(List<BigInteger> values, List<String> destinations, byte[] data, List<BigInteger> dataLengths, String descriptionUrl) {
            this.values = values;
            this.destinations = destinations;
            this.data = data;
            this.dataLengths = dataLengths;
            this.descriptionUrl = descriptionUrl;
        }
    }

    public static class Config {
        public final BigInteger concurrentProposals;
        public final BigInteger dequeueFrequency;
        public final BigInteger minDeposit;
        public final BigInteger queueExpiry;
        public final BigInteger stageDurationsApproval;
        public final BigInteger stageDurationsReferendum;
        public final BigInteger stageDurationsExecution;

        Config(BigInteger concurrentProposals, BigInteger dequeueFrequency, BigInteger minDeposit, BigInteger queueExpiry, Tuple3<BigInteger, BigInteger, BigInteger> stageDurations) {
            this.concurrentProposals = concurrentProposals;
            this.dequeueFrequency = dequeueFrequency;
            this.minDeposit = minDeposit;
            this.queueExpiry = queueExpiry;
            this.stageDurationsApproval = stageDurations.component1();
            this.stageDurationsReferendum = stageDurations.component2();
            this.stageDurationsExecution = stageDurations.component3();
        }
    }

    public GovernanceWrapper(Governance contract) {
        super(contract);
    }

    public Config getConfig() throws Exception {
        return new Config(
                this.concurrentProposals().send(),
                this.dequeueFrequency().send(),
                this.minDeposit().send(),
                this.queueExpiry().send(),
                this.stageDurations().send()
        );
    }

    public static ProposalParams proposalToParams(Proposal proposal, String description) {
        List<Transaction> transactions = proposal.transactions;

        List<byte[]> arrays = transactions.stream().map(transaction -> transaction.data).collect(Collectors.toList());
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (final byte[] array : arrays) {
            if (array != null) {
                out.write(array, 0, array.length);
            }
        }

        return new ProposalParams(
                transactions.stream().map(transaction -> transaction.value).collect(Collectors.toList()),
                transactions.stream().map(transaction -> transaction.to).collect(Collectors.toList()),
                out.toByteArray(),
                arrays.stream().map(array -> BigInteger.valueOf(array.length)).collect(Collectors.toList()),
                description
        );
    }

    public Proposal getProposal(BigInteger proposalID) throws Exception {
        ProposalMetadata metadata = this.getProposalMetadata(proposalID);

        List<Transaction> transactions = IntStream.range(0, metadata.transactionCount).mapToObj((idx) -> {
            try {
                Tuple3<BigInteger, String, byte[]> transaction = this.getProposalTransaction(proposalID, BigInteger.valueOf(idx)).send();
                return new Transaction(
                        transaction.component1(),
                        transaction.component2(),
                        transaction.component3(),
                        "to",
                        "input");
            } catch (Exception e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return new Proposal(transactions);
    }

    private BigInteger getIndex(BigInteger id, List<BigInteger> array) {
        int index = array.indexOf(id);
        if (index == -1) {
            throw new Error("ID " + id + " not found in array");
        }
        return BigInteger.valueOf(index);
    }

    public BigInteger getDequeueIndex(BigInteger proposalID) throws Exception {
        List dequeue = getDequeue().send();
        return this.getIndex(proposalID, dequeue);
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

    public ProposalMetadata getProposalMetadata(BigInteger proposalID) throws Exception {
        Tuple5<String, BigInteger, BigInteger, BigInteger, String> proposal = contract.getProposal(proposalID).send();
        return new ProposalMetadata(
                proposal.component1(),
                proposal.component2(),
                proposal.component3(),
                proposal.component4().intValue(),
                proposal.component5()
        );
    }

    public ProposalRecord getProposalRecord(BigInteger proposalID) throws Exception {
        ProposalMetadata metadata = this.getProposalMetadata(proposalID);
        Proposal proposal = this.getProposal(proposalID);
        BigInteger stageValue = this.getProposalStage(proposalID).send();
        ProposalStage stage = ProposalStage.fromId(stageValue);
        Boolean passing = this.isProposalPassing(proposalID).send();

        BigInteger upvotes = BigInteger.ZERO;
        Votes votes = new Votes();

        if (stage == ProposalStage.Queued) {
            upvotes = this.getUpvotes(proposalID).send();
        } else if (stage != ProposalStage.Expiration) {
            votes = this.getVotes(proposalID);
        }

        return new ProposalRecord(
                stage,
                metadata,
                upvotes,
                votes,
                proposal,
                passing
        );
    }

    private Votes getVotes(BigInteger proposalID) throws Exception {
        Tuple3<BigInteger, BigInteger, BigInteger> votes = contract.getVoteTotals(proposalID).send();

        return new Votes(votes.component1(), votes.component2(), votes.component3());
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

    public RemoteFunctionCall<TransactionReceipt> propose(Proposal proposal, String descriptionUrl) {
        ProposalParams params = proposalToParams(proposal, descriptionUrl);
        return contract.propose(
                params.values,
                params.destinations,
                params.data,
                params.dataLengths,
                params.descriptionUrl,
                BigInteger.ZERO
        );
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

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposalId, BigInteger value) throws Exception {
        BigInteger index = this.getDequeueIndex(proposalId);
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
