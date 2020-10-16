package org.celo.contractkit.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.6.1.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Governance extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_APPROVER = "approver";

    public static final String FUNC_CHECKPROOFOFPOSSESSION = "checkProofOfPossession";

    public static final String FUNC_CONCURRENTPROPOSALS = "concurrentProposals";

    public static final String FUNC_DEQUEUEFREQUENCY = "dequeueFrequency";

    public static final String FUNC_DEQUEUED = "dequeued";

    public static final String FUNC_EMPTYINDICES = "emptyIndices";

    public static final String FUNC_FRACTIONMULEXP = "fractionMulExp";

    public static final String FUNC_GETBLOCKNUMBERFROMHEADER = "getBlockNumberFromHeader";

    public static final String FUNC_GETEPOCHNUMBER = "getEpochNumber";

    public static final String FUNC_GETEPOCHNUMBEROFBLOCK = "getEpochNumberOfBlock";

    public static final String FUNC_GETEPOCHSIZE = "getEpochSize";

    public static final String FUNC_GETPARENTSEALBITMAP = "getParentSealBitmap";

    public static final String FUNC_GETVERIFIEDSEALBITMAPFROMHEADER = "getVerifiedSealBitmapFromHeader";

    public static final String FUNC_HASHHEADER = "hashHeader";

    public static final String FUNC_HOTFIXES = "hotfixes";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_LASTDEQUEUE = "lastDequeue";

    public static final String FUNC_MINDEPOSIT = "minDeposit";

    public static final String FUNC_MINQUORUMSIZE = "minQuorumSize";

    public static final String FUNC_MINQUORUMSIZEINCURRENTSET = "minQuorumSizeInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINCURRENTSET = "numberValidatorsInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINSET = "numberValidatorsInSet";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PROPOSALCOUNT = "proposalCount";

    public static final String FUNC_QUEUEEXPIRY = "queueExpiry";

    public static final String FUNC_REFUNDEDDEPOSITS = "refundedDeposits";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_STAGEDURATIONS = "stageDurations";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMCURRENTSET = "validatorSignerAddressFromCurrentSet";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMSET = "validatorSignerAddressFromSet";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETAPPROVER = "setApprover";

    public static final String FUNC_SETCONCURRENTPROPOSALS = "setConcurrentProposals";

    public static final String FUNC_SETMINDEPOSIT = "setMinDeposit";

    public static final String FUNC_SETQUEUEEXPIRY = "setQueueExpiry";

    public static final String FUNC_SETDEQUEUEFREQUENCY = "setDequeueFrequency";

    public static final String FUNC_SETAPPROVALSTAGEDURATION = "setApprovalStageDuration";

    public static final String FUNC_SETREFERENDUMSTAGEDURATION = "setReferendumStageDuration";

    public static final String FUNC_SETEXECUTIONSTAGEDURATION = "setExecutionStageDuration";

    public static final String FUNC_SETPARTICIPATIONBASELINE = "setParticipationBaseline";

    public static final String FUNC_SETPARTICIPATIONFLOOR = "setParticipationFloor";

    public static final String FUNC_SETBASELINEUPDATEFACTOR = "setBaselineUpdateFactor";

    public static final String FUNC_SETBASELINEQUORUMFACTOR = "setBaselineQuorumFactor";

    public static final String FUNC_SETCONSTITUTION = "setConstitution";

    public static final String FUNC_PROPOSE = "propose";

    public static final String FUNC_UPVOTE = "upvote";

    public static final String FUNC_GETPROPOSALSTAGE = "getProposalStage";

    public static final String FUNC_REVOKEUPVOTE = "revokeUpvote";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_APPROVEHOTFIX = "approveHotfix";

    public static final String FUNC_ISHOTFIXWHITELISTEDBY = "isHotfixWhitelistedBy";

    public static final String FUNC_WHITELISTHOTFIX = "whitelistHotfix";

    public static final String FUNC_PREPAREHOTFIX = "prepareHotfix";

    public static final String FUNC_EXECUTEHOTFIX = "executeHotfix";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_ISVOTING = "isVoting";

    public static final String FUNC_GETAPPROVALSTAGEDURATION = "getApprovalStageDuration";

    public static final String FUNC_GETREFERENDUMSTAGEDURATION = "getReferendumStageDuration";

    public static final String FUNC_GETEXECUTIONSTAGEDURATION = "getExecutionStageDuration";

    public static final String FUNC_GETPARTICIPATIONPARAMETERS = "getParticipationParameters";

    public static final String FUNC_PROPOSALEXISTS = "proposalExists";

    public static final String FUNC_GETPROPOSAL = "getProposal";

    public static final String FUNC_GETPROPOSALTRANSACTION = "getProposalTransaction";

    public static final String FUNC_ISAPPROVED = "isApproved";

    public static final String FUNC_GETVOTETOTALS = "getVoteTotals";

    public static final String FUNC_GETVOTERECORD = "getVoteRecord";

    public static final String FUNC_GETQUEUELENGTH = "getQueueLength";

    public static final String FUNC_GETUPVOTES = "getUpvotes";

    public static final String FUNC_GETQUEUE = "getQueue";

    public static final String FUNC_GETDEQUEUE = "getDequeue";

    public static final String FUNC_GETUPVOTERECORD = "getUpvoteRecord";

    public static final String FUNC_GETMOSTRECENTREFERENDUMPROPOSAL = "getMostRecentReferendumProposal";

    public static final String FUNC_HOTFIXWHITELISTVALIDATORTALLY = "hotfixWhitelistValidatorTally";

    public static final String FUNC_ISHOTFIXPASSING = "isHotfixPassing";

    public static final String FUNC_GETHOTFIXRECORD = "getHotfixRecord";

    public static final String FUNC_DEQUEUEPROPOSALSIFREADY = "dequeueProposalsIfReady";

    public static final String FUNC_ISQUEUED = "isQueued";

    public static final String FUNC_ISPROPOSALPASSING = "isProposalPassing";

    public static final String FUNC_ISDEQUEUEDPROPOSAL = "isDequeuedProposal";

    public static final String FUNC_ISDEQUEUEDPROPOSALEXPIRED = "isDequeuedProposalExpired";

    public static final String FUNC_ISQUEUEDPROPOSALEXPIRED = "isQueuedProposalExpired";

    public static final String FUNC_GETCONSTITUTION = "getConstitution";

    public static final Event APPROVALSTAGEDURATIONSET_EVENT = new Event("ApprovalStageDurationSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVERSET_EVENT = new Event("ApproverSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event CONCURRENTPROPOSALSSET_EVENT = new Event("ConcurrentProposalsSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event CONSTITUTIONSET_EVENT = new Event("ConstitutionSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes4>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event DEQUEUEFREQUENCYSET_EVENT = new Event("DequeueFrequencySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event EXECUTIONSTAGEDURATIONSET_EVENT = new Event("ExecutionStageDurationSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event HOTFIXAPPROVED_EVENT = new Event("HotfixApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event HOTFIXEXECUTED_EVENT = new Event("HotfixExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event HOTFIXPREPARED_EVENT = new Event("HotfixPrepared", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event HOTFIXWHITELISTED_EVENT = new Event("HotfixWhitelisted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event MINDEPOSITSET_EVENT = new Event("MinDepositSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PARTICIPATIONBASELINEQUORUMFACTORSET_EVENT = new Event("ParticipationBaselineQuorumFactorSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PARTICIPATIONBASELINEUPDATEFACTORSET_EVENT = new Event("ParticipationBaselineUpdateFactorSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PARTICIPATIONBASELINEUPDATED_EVENT = new Event("ParticipationBaselineUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PARTICIPATIONFLOORSET_EVENT = new Event("ParticipationFloorSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALAPPROVED_EVENT = new Event("ProposalApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PROPOSALDEQUEUED_EVENT = new Event("ProposalDequeued", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALEXECUTED_EVENT = new Event("ProposalExecuted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PROPOSALEXPIRED_EVENT = new Event("ProposalExpired", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PROPOSALQUEUED_EVENT = new Event("ProposalQueued", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALUPVOTEREVOKED_EVENT = new Event("ProposalUpvoteRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALUPVOTED_EVENT = new Event("ProposalUpvoted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PROPOSALVOTED_EVENT = new Event("ProposalVoted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event QUEUEEXPIRYSET_EVENT = new Event("QueueExpirySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event REFERENDUMSTAGEDURATIONSET_EVENT = new Event("ReferendumStageDurationSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Governance(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Governance(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Governance(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Governance(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalStageDurationSetEventResponse> getApprovalStageDurationSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALSTAGEDURATIONSET_EVENT, transactionReceipt);
        ArrayList<ApprovalStageDurationSetEventResponse> responses = new ArrayList<ApprovalStageDurationSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalStageDurationSetEventResponse typedResponse = new ApprovalStageDurationSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.approvalStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalStageDurationSetEventResponse> approvalStageDurationSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalStageDurationSetEventResponse>() {
            @Override
            public ApprovalStageDurationSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALSTAGEDURATIONSET_EVENT, log);
                ApprovalStageDurationSetEventResponse typedResponse = new ApprovalStageDurationSetEventResponse();
                typedResponse.log = log;
                typedResponse.approvalStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalStageDurationSetEventResponse> approvalStageDurationSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALSTAGEDURATIONSET_EVENT));
        return approvalStageDurationSetEventFlowable(filter);
    }

    public List<ApproverSetEventResponse> getApproverSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVERSET_EVENT, transactionReceipt);
        ArrayList<ApproverSetEventResponse> responses = new ArrayList<ApproverSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApproverSetEventResponse typedResponse = new ApproverSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.approver = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApproverSetEventResponse> approverSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApproverSetEventResponse>() {
            @Override
            public ApproverSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVERSET_EVENT, log);
                ApproverSetEventResponse typedResponse = new ApproverSetEventResponse();
                typedResponse.log = log;
                typedResponse.approver = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApproverSetEventResponse> approverSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVERSET_EVENT));
        return approverSetEventFlowable(filter);
    }

    public List<ConcurrentProposalsSetEventResponse> getConcurrentProposalsSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONCURRENTPROPOSALSSET_EVENT, transactionReceipt);
        ArrayList<ConcurrentProposalsSetEventResponse> responses = new ArrayList<ConcurrentProposalsSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ConcurrentProposalsSetEventResponse typedResponse = new ConcurrentProposalsSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.concurrentProposals = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ConcurrentProposalsSetEventResponse> concurrentProposalsSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ConcurrentProposalsSetEventResponse>() {
            @Override
            public ConcurrentProposalsSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CONCURRENTPROPOSALSSET_EVENT, log);
                ConcurrentProposalsSetEventResponse typedResponse = new ConcurrentProposalsSetEventResponse();
                typedResponse.log = log;
                typedResponse.concurrentProposals = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ConcurrentProposalsSetEventResponse> concurrentProposalsSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONCURRENTPROPOSALSSET_EVENT));
        return concurrentProposalsSetEventFlowable(filter);
    }

    public List<ConstitutionSetEventResponse> getConstitutionSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONSTITUTIONSET_EVENT, transactionReceipt);
        ArrayList<ConstitutionSetEventResponse> responses = new ArrayList<ConstitutionSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ConstitutionSetEventResponse typedResponse = new ConstitutionSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.functionId = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.threshold = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ConstitutionSetEventResponse> constitutionSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ConstitutionSetEventResponse>() {
            @Override
            public ConstitutionSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CONSTITUTIONSET_EVENT, log);
                ConstitutionSetEventResponse typedResponse = new ConstitutionSetEventResponse();
                typedResponse.log = log;
                typedResponse.destination = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.functionId = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.threshold = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ConstitutionSetEventResponse> constitutionSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONSTITUTIONSET_EVENT));
        return constitutionSetEventFlowable(filter);
    }

    public List<DequeueFrequencySetEventResponse> getDequeueFrequencySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DEQUEUEFREQUENCYSET_EVENT, transactionReceipt);
        ArrayList<DequeueFrequencySetEventResponse> responses = new ArrayList<DequeueFrequencySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DequeueFrequencySetEventResponse typedResponse = new DequeueFrequencySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dequeueFrequency = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DequeueFrequencySetEventResponse> dequeueFrequencySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DequeueFrequencySetEventResponse>() {
            @Override
            public DequeueFrequencySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DEQUEUEFREQUENCYSET_EVENT, log);
                DequeueFrequencySetEventResponse typedResponse = new DequeueFrequencySetEventResponse();
                typedResponse.log = log;
                typedResponse.dequeueFrequency = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DequeueFrequencySetEventResponse> dequeueFrequencySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEQUEUEFREQUENCYSET_EVENT));
        return dequeueFrequencySetEventFlowable(filter);
    }

    public List<ExecutionStageDurationSetEventResponse> getExecutionStageDurationSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(EXECUTIONSTAGEDURATIONSET_EVENT, transactionReceipt);
        ArrayList<ExecutionStageDurationSetEventResponse> responses = new ArrayList<ExecutionStageDurationSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ExecutionStageDurationSetEventResponse typedResponse = new ExecutionStageDurationSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.executionStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ExecutionStageDurationSetEventResponse> executionStageDurationSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ExecutionStageDurationSetEventResponse>() {
            @Override
            public ExecutionStageDurationSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(EXECUTIONSTAGEDURATIONSET_EVENT, log);
                ExecutionStageDurationSetEventResponse typedResponse = new ExecutionStageDurationSetEventResponse();
                typedResponse.log = log;
                typedResponse.executionStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ExecutionStageDurationSetEventResponse> executionStageDurationSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EXECUTIONSTAGEDURATIONSET_EVENT));
        return executionStageDurationSetEventFlowable(filter);
    }

    public List<HotfixApprovedEventResponse> getHotfixApprovedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(HOTFIXAPPROVED_EVENT, transactionReceipt);
        ArrayList<HotfixApprovedEventResponse> responses = new ArrayList<HotfixApprovedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            HotfixApprovedEventResponse typedResponse = new HotfixApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<HotfixApprovedEventResponse> hotfixApprovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, HotfixApprovedEventResponse>() {
            @Override
            public HotfixApprovedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(HOTFIXAPPROVED_EVENT, log);
                HotfixApprovedEventResponse typedResponse = new HotfixApprovedEventResponse();
                typedResponse.log = log;
                typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<HotfixApprovedEventResponse> hotfixApprovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HOTFIXAPPROVED_EVENT));
        return hotfixApprovedEventFlowable(filter);
    }

    public List<HotfixExecutedEventResponse> getHotfixExecutedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(HOTFIXEXECUTED_EVENT, transactionReceipt);
        ArrayList<HotfixExecutedEventResponse> responses = new ArrayList<HotfixExecutedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            HotfixExecutedEventResponse typedResponse = new HotfixExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<HotfixExecutedEventResponse> hotfixExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, HotfixExecutedEventResponse>() {
            @Override
            public HotfixExecutedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(HOTFIXEXECUTED_EVENT, log);
                HotfixExecutedEventResponse typedResponse = new HotfixExecutedEventResponse();
                typedResponse.log = log;
                typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<HotfixExecutedEventResponse> hotfixExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HOTFIXEXECUTED_EVENT));
        return hotfixExecutedEventFlowable(filter);
    }

    public List<HotfixPreparedEventResponse> getHotfixPreparedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(HOTFIXPREPARED_EVENT, transactionReceipt);
        ArrayList<HotfixPreparedEventResponse> responses = new ArrayList<HotfixPreparedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            HotfixPreparedEventResponse typedResponse = new HotfixPreparedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<HotfixPreparedEventResponse> hotfixPreparedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, HotfixPreparedEventResponse>() {
            @Override
            public HotfixPreparedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(HOTFIXPREPARED_EVENT, log);
                HotfixPreparedEventResponse typedResponse = new HotfixPreparedEventResponse();
                typedResponse.log = log;
                typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<HotfixPreparedEventResponse> hotfixPreparedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HOTFIXPREPARED_EVENT));
        return hotfixPreparedEventFlowable(filter);
    }

    public List<HotfixWhitelistedEventResponse> getHotfixWhitelistedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(HOTFIXWHITELISTED_EVENT, transactionReceipt);
        ArrayList<HotfixWhitelistedEventResponse> responses = new ArrayList<HotfixWhitelistedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            HotfixWhitelistedEventResponse typedResponse = new HotfixWhitelistedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.whitelister = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<HotfixWhitelistedEventResponse> hotfixWhitelistedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, HotfixWhitelistedEventResponse>() {
            @Override
            public HotfixWhitelistedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(HOTFIXWHITELISTED_EVENT, log);
                HotfixWhitelistedEventResponse typedResponse = new HotfixWhitelistedEventResponse();
                typedResponse.log = log;
                typedResponse.hash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.whitelister = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<HotfixWhitelistedEventResponse> hotfixWhitelistedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HOTFIXWHITELISTED_EVENT));
        return hotfixWhitelistedEventFlowable(filter);
    }

    public List<MinDepositSetEventResponse> getMinDepositSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MINDEPOSITSET_EVENT, transactionReceipt);
        ArrayList<MinDepositSetEventResponse> responses = new ArrayList<MinDepositSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MinDepositSetEventResponse typedResponse = new MinDepositSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.minDeposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MinDepositSetEventResponse> minDepositSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MinDepositSetEventResponse>() {
            @Override
            public MinDepositSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MINDEPOSITSET_EVENT, log);
                MinDepositSetEventResponse typedResponse = new MinDepositSetEventResponse();
                typedResponse.log = log;
                typedResponse.minDeposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MinDepositSetEventResponse> minDepositSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINDEPOSITSET_EVENT));
        return minDepositSetEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<ParticipationBaselineQuorumFactorSetEventResponse> getParticipationBaselineQuorumFactorSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PARTICIPATIONBASELINEQUORUMFACTORSET_EVENT, transactionReceipt);
        ArrayList<ParticipationBaselineQuorumFactorSetEventResponse> responses = new ArrayList<ParticipationBaselineQuorumFactorSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ParticipationBaselineQuorumFactorSetEventResponse typedResponse = new ParticipationBaselineQuorumFactorSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.baselineQuorumFactor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ParticipationBaselineQuorumFactorSetEventResponse> participationBaselineQuorumFactorSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ParticipationBaselineQuorumFactorSetEventResponse>() {
            @Override
            public ParticipationBaselineQuorumFactorSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PARTICIPATIONBASELINEQUORUMFACTORSET_EVENT, log);
                ParticipationBaselineQuorumFactorSetEventResponse typedResponse = new ParticipationBaselineQuorumFactorSetEventResponse();
                typedResponse.log = log;
                typedResponse.baselineQuorumFactor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ParticipationBaselineQuorumFactorSetEventResponse> participationBaselineQuorumFactorSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PARTICIPATIONBASELINEQUORUMFACTORSET_EVENT));
        return participationBaselineQuorumFactorSetEventFlowable(filter);
    }

    public List<ParticipationBaselineUpdateFactorSetEventResponse> getParticipationBaselineUpdateFactorSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PARTICIPATIONBASELINEUPDATEFACTORSET_EVENT, transactionReceipt);
        ArrayList<ParticipationBaselineUpdateFactorSetEventResponse> responses = new ArrayList<ParticipationBaselineUpdateFactorSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ParticipationBaselineUpdateFactorSetEventResponse typedResponse = new ParticipationBaselineUpdateFactorSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.baselineUpdateFactor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ParticipationBaselineUpdateFactorSetEventResponse> participationBaselineUpdateFactorSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ParticipationBaselineUpdateFactorSetEventResponse>() {
            @Override
            public ParticipationBaselineUpdateFactorSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PARTICIPATIONBASELINEUPDATEFACTORSET_EVENT, log);
                ParticipationBaselineUpdateFactorSetEventResponse typedResponse = new ParticipationBaselineUpdateFactorSetEventResponse();
                typedResponse.log = log;
                typedResponse.baselineUpdateFactor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ParticipationBaselineUpdateFactorSetEventResponse> participationBaselineUpdateFactorSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PARTICIPATIONBASELINEUPDATEFACTORSET_EVENT));
        return participationBaselineUpdateFactorSetEventFlowable(filter);
    }

    public List<ParticipationBaselineUpdatedEventResponse> getParticipationBaselineUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PARTICIPATIONBASELINEUPDATED_EVENT, transactionReceipt);
        ArrayList<ParticipationBaselineUpdatedEventResponse> responses = new ArrayList<ParticipationBaselineUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ParticipationBaselineUpdatedEventResponse typedResponse = new ParticipationBaselineUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.participationBaseline = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ParticipationBaselineUpdatedEventResponse> participationBaselineUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ParticipationBaselineUpdatedEventResponse>() {
            @Override
            public ParticipationBaselineUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PARTICIPATIONBASELINEUPDATED_EVENT, log);
                ParticipationBaselineUpdatedEventResponse typedResponse = new ParticipationBaselineUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.participationBaseline = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ParticipationBaselineUpdatedEventResponse> participationBaselineUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PARTICIPATIONBASELINEUPDATED_EVENT));
        return participationBaselineUpdatedEventFlowable(filter);
    }

    public List<ParticipationFloorSetEventResponse> getParticipationFloorSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PARTICIPATIONFLOORSET_EVENT, transactionReceipt);
        ArrayList<ParticipationFloorSetEventResponse> responses = new ArrayList<ParticipationFloorSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ParticipationFloorSetEventResponse typedResponse = new ParticipationFloorSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.participationFloor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ParticipationFloorSetEventResponse> participationFloorSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ParticipationFloorSetEventResponse>() {
            @Override
            public ParticipationFloorSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PARTICIPATIONFLOORSET_EVENT, log);
                ParticipationFloorSetEventResponse typedResponse = new ParticipationFloorSetEventResponse();
                typedResponse.log = log;
                typedResponse.participationFloor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ParticipationFloorSetEventResponse> participationFloorSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PARTICIPATIONFLOORSET_EVENT));
        return participationFloorSetEventFlowable(filter);
    }

    public List<ProposalApprovedEventResponse> getProposalApprovedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALAPPROVED_EVENT, transactionReceipt);
        ArrayList<ProposalApprovedEventResponse> responses = new ArrayList<ProposalApprovedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalApprovedEventResponse typedResponse = new ProposalApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalApprovedEventResponse> proposalApprovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalApprovedEventResponse>() {
            @Override
            public ProposalApprovedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALAPPROVED_EVENT, log);
                ProposalApprovedEventResponse typedResponse = new ProposalApprovedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalApprovedEventResponse> proposalApprovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALAPPROVED_EVENT));
        return proposalApprovedEventFlowable(filter);
    }

    public List<ProposalDequeuedEventResponse> getProposalDequeuedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALDEQUEUED_EVENT, transactionReceipt);
        ArrayList<ProposalDequeuedEventResponse> responses = new ArrayList<ProposalDequeuedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalDequeuedEventResponse typedResponse = new ProposalDequeuedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalDequeuedEventResponse> proposalDequeuedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalDequeuedEventResponse>() {
            @Override
            public ProposalDequeuedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALDEQUEUED_EVENT, log);
                ProposalDequeuedEventResponse typedResponse = new ProposalDequeuedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalDequeuedEventResponse> proposalDequeuedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALDEQUEUED_EVENT));
        return proposalDequeuedEventFlowable(filter);
    }

    public List<ProposalExecutedEventResponse> getProposalExecutedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALEXECUTED_EVENT, transactionReceipt);
        ArrayList<ProposalExecutedEventResponse> responses = new ArrayList<ProposalExecutedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalExecutedEventResponse typedResponse = new ProposalExecutedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalExecutedEventResponse> proposalExecutedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalExecutedEventResponse>() {
            @Override
            public ProposalExecutedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALEXECUTED_EVENT, log);
                ProposalExecutedEventResponse typedResponse = new ProposalExecutedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalExecutedEventResponse> proposalExecutedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALEXECUTED_EVENT));
        return proposalExecutedEventFlowable(filter);
    }

    public List<ProposalExpiredEventResponse> getProposalExpiredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALEXPIRED_EVENT, transactionReceipt);
        ArrayList<ProposalExpiredEventResponse> responses = new ArrayList<ProposalExpiredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalExpiredEventResponse typedResponse = new ProposalExpiredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalExpiredEventResponse> proposalExpiredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalExpiredEventResponse>() {
            @Override
            public ProposalExpiredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALEXPIRED_EVENT, log);
                ProposalExpiredEventResponse typedResponse = new ProposalExpiredEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalExpiredEventResponse> proposalExpiredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALEXPIRED_EVENT));
        return proposalExpiredEventFlowable(filter);
    }

    public List<ProposalQueuedEventResponse> getProposalQueuedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALQUEUED_EVENT, transactionReceipt);
        ArrayList<ProposalQueuedEventResponse> responses = new ArrayList<ProposalQueuedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalQueuedEventResponse typedResponse = new ProposalQueuedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.proposer = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.transactionCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalQueuedEventResponse> proposalQueuedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalQueuedEventResponse>() {
            @Override
            public ProposalQueuedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALQUEUED_EVENT, log);
                ProposalQueuedEventResponse typedResponse = new ProposalQueuedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.proposer = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.transactionCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalQueuedEventResponse> proposalQueuedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALQUEUED_EVENT));
        return proposalQueuedEventFlowable(filter);
    }

    public List<ProposalUpvoteRevokedEventResponse> getProposalUpvoteRevokedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALUPVOTEREVOKED_EVENT, transactionReceipt);
        ArrayList<ProposalUpvoteRevokedEventResponse> responses = new ArrayList<ProposalUpvoteRevokedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalUpvoteRevokedEventResponse typedResponse = new ProposalUpvoteRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.revokedUpvotes = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalUpvoteRevokedEventResponse> proposalUpvoteRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalUpvoteRevokedEventResponse>() {
            @Override
            public ProposalUpvoteRevokedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALUPVOTEREVOKED_EVENT, log);
                ProposalUpvoteRevokedEventResponse typedResponse = new ProposalUpvoteRevokedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.revokedUpvotes = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalUpvoteRevokedEventResponse> proposalUpvoteRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALUPVOTEREVOKED_EVENT));
        return proposalUpvoteRevokedEventFlowable(filter);
    }

    public List<ProposalUpvotedEventResponse> getProposalUpvotedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALUPVOTED_EVENT, transactionReceipt);
        ArrayList<ProposalUpvotedEventResponse> responses = new ArrayList<ProposalUpvotedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalUpvotedEventResponse typedResponse = new ProposalUpvotedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.upvotes = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalUpvotedEventResponse> proposalUpvotedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalUpvotedEventResponse>() {
            @Override
            public ProposalUpvotedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALUPVOTED_EVENT, log);
                ProposalUpvotedEventResponse typedResponse = new ProposalUpvotedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.upvotes = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalUpvotedEventResponse> proposalUpvotedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALUPVOTED_EVENT));
        return proposalUpvotedEventFlowable(filter);
    }

    public List<ProposalVotedEventResponse> getProposalVotedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALVOTED_EVENT, transactionReceipt);
        ArrayList<ProposalVotedEventResponse> responses = new ArrayList<ProposalVotedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ProposalVotedEventResponse typedResponse = new ProposalVotedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalVotedEventResponse> proposalVotedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalVotedEventResponse>() {
            @Override
            public ProposalVotedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALVOTED_EVENT, log);
                ProposalVotedEventResponse typedResponse = new ProposalVotedEventResponse();
                typedResponse.log = log;
                typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.weight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalVotedEventResponse> proposalVotedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALVOTED_EVENT));
        return proposalVotedEventFlowable(filter);
    }

    public List<QueueExpirySetEventResponse> getQueueExpirySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(QUEUEEXPIRYSET_EVENT, transactionReceipt);
        ArrayList<QueueExpirySetEventResponse> responses = new ArrayList<QueueExpirySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            QueueExpirySetEventResponse typedResponse = new QueueExpirySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.queueExpiry = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<QueueExpirySetEventResponse> queueExpirySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, QueueExpirySetEventResponse>() {
            @Override
            public QueueExpirySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(QUEUEEXPIRYSET_EVENT, log);
                QueueExpirySetEventResponse typedResponse = new QueueExpirySetEventResponse();
                typedResponse.log = log;
                typedResponse.queueExpiry = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<QueueExpirySetEventResponse> queueExpirySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QUEUEEXPIRYSET_EVENT));
        return queueExpirySetEventFlowable(filter);
    }

    public List<ReferendumStageDurationSetEventResponse> getReferendumStageDurationSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REFERENDUMSTAGEDURATIONSET_EVENT, transactionReceipt);
        ArrayList<ReferendumStageDurationSetEventResponse> responses = new ArrayList<ReferendumStageDurationSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ReferendumStageDurationSetEventResponse typedResponse = new ReferendumStageDurationSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.referendumStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReferendumStageDurationSetEventResponse> referendumStageDurationSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReferendumStageDurationSetEventResponse>() {
            @Override
            public ReferendumStageDurationSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REFERENDUMSTAGEDURATIONSET_EVENT, log);
                ReferendumStageDurationSetEventResponse typedResponse = new ReferendumStageDurationSetEventResponse();
                typedResponse.log = log;
                typedResponse.referendumStageDuration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ReferendumStageDurationSetEventResponse> referendumStageDurationSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFERENDUMSTAGEDURATIONSET_EVENT));
        return referendumStageDurationSetEventFlowable(filter);
    }

    public List<RegistrySetEventResponse> getRegistrySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTRYSET_EVENT, transactionReceipt);
        ArrayList<RegistrySetEventResponse> responses = new ArrayList<RegistrySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RegistrySetEventResponse typedResponse = new RegistrySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.registryAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegistrySetEventResponse> registrySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RegistrySetEventResponse>() {
            @Override
            public RegistrySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTRYSET_EVENT, log);
                RegistrySetEventResponse typedResponse = new RegistrySetEventResponse();
                typedResponse.log = log;
                typedResponse.registryAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistrySetEventResponse> registrySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTRYSET_EVENT));
        return registrySetEventFlowable(filter);
    }

    public RemoteFunctionCall<String> approver() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_APPROVER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKPROOFOFPOSSESSION, 
                Arrays.<Type>asList(new Address(sender), 
                new DynamicBytes(blsKey), 
                new DynamicBytes(blsPop)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> concurrentProposals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CONCURRENTPROPOSALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> dequeueFrequency() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEQUEUEFREQUENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> dequeued(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEQUEUED, 
                Arrays.<Type>asList(new Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> emptyIndices(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EMPTYINDICES, 
                Arrays.<Type>asList(new Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> fractionMulExp(BigInteger aNumerator, BigInteger aDenominator, BigInteger bNumerator, BigInteger bDenominator, BigInteger exponent, BigInteger _decimals) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FRACTIONMULEXP, 
                Arrays.<Type>asList(new Uint256(aNumerator), 
                new Uint256(aDenominator), 
                new Uint256(bNumerator), 
                new Uint256(bDenominator), 
                new Uint256(exponent), 
                new Uint256(_decimals)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getBlockNumberFromHeader(byte[] header) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETBLOCKNUMBERFROMHEADER, 
                Arrays.<Type>asList(new DynamicBytes(header)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getEpochNumber() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEPOCHNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getEpochNumberOfBlock(BigInteger blockNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEPOCHNUMBEROFBLOCK, 
                Arrays.<Type>asList(new Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getEpochSize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEPOCHSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> getParentSealBitmap(BigInteger blockNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPARENTSEALBITMAP, 
                Arrays.<Type>asList(new Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> getVerifiedSealBitmapFromHeader(byte[] header) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVERIFIEDSEALBITMAPFROMHEADER, 
                Arrays.<Type>asList(new DynamicBytes(header)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> hashHeader(byte[] header) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASHHEADER, 
                Arrays.<Type>asList(new DynamicBytes(header)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>> hotfixes(byte[] param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HOTFIXES, 
                Arrays.<Type>asList(new Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>>(function,
                new Callable<Tuple3<Boolean, Boolean, BigInteger>>() {
                    @Override
                    public Tuple3<Boolean, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Boolean, Boolean, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> initialized() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INITIALIZED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> lastDequeue() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LASTDEQUEUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> minDeposit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINDEPOSIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> minQuorumSize(BigInteger blockNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINQUORUMSIZE, 
                Arrays.<Type>asList(new Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> minQuorumSizeInCurrentSet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINQUORUMSIZEINCURRENTSET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInCurrentSet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NUMBERVALIDATORSINCURRENTSET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInSet(BigInteger blockNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NUMBERVALIDATORSINSET, 
                Arrays.<Type>asList(new Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> proposalCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PROPOSALCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> queueExpiry() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_QUEUEEXPIRY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> refundedDeposits(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REFUNDEDDEPOSITS, 
                Arrays.<Type>asList(new Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> registry() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REGISTRY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistry(String registryAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETREGISTRY, 
                Arrays.<Type>asList(new Address(registryAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> stageDurations() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STAGEDURATIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> validatorSignerAddressFromCurrentSet(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VALIDATORSIGNERADDRESSFROMCURRENTSET, 
                Arrays.<Type>asList(new Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> validatorSignerAddressFromSet(BigInteger index, BigInteger blockNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VALIDATORSIGNERADDRESSFROMSET, 
                Arrays.<Type>asList(new Uint256(index), 
                new Uint256(blockNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVERSIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, String _approver, BigInteger _concurrentProposals, BigInteger _minDeposit, BigInteger _queueExpiry, BigInteger _dequeueFrequency, BigInteger approvalStageDuration, BigInteger referendumStageDuration, BigInteger executionStageDuration, BigInteger participationBaseline, BigInteger participationFloor, BigInteger baselineUpdateFactor, BigInteger baselineQuorumFactor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(registryAddress), 
                new Address(_approver), 
                new Uint256(_concurrentProposals), 
                new Uint256(_minDeposit), 
                new Uint256(_queueExpiry), 
                new Uint256(_dequeueFrequency), 
                new Uint256(approvalStageDuration), 
                new Uint256(referendumStageDuration), 
                new Uint256(executionStageDuration), 
                new Uint256(participationBaseline), 
                new Uint256(participationFloor), 
                new Uint256(baselineUpdateFactor), 
                new Uint256(baselineQuorumFactor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprover(String _approver) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVER, 
                Arrays.<Type>asList(new Address(_approver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setConcurrentProposals(BigInteger _concurrentProposals) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCONCURRENTPROPOSALS, 
                Arrays.<Type>asList(new Uint256(_concurrentProposals)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMinDeposit(BigInteger _minDeposit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMINDEPOSIT, 
                Arrays.<Type>asList(new Uint256(_minDeposit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setQueueExpiry(BigInteger _queueExpiry) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETQUEUEEXPIRY, 
                Arrays.<Type>asList(new Uint256(_queueExpiry)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDequeueFrequency(BigInteger _dequeueFrequency) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETDEQUEUEFREQUENCY, 
                Arrays.<Type>asList(new Uint256(_dequeueFrequency)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalStageDuration(BigInteger approvalStageDuration) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALSTAGEDURATION, 
                Arrays.<Type>asList(new Uint256(approvalStageDuration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setReferendumStageDuration(BigInteger referendumStageDuration) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETREFERENDUMSTAGEDURATION, 
                Arrays.<Type>asList(new Uint256(referendumStageDuration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setExecutionStageDuration(BigInteger executionStageDuration) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETEXECUTIONSTAGEDURATION, 
                Arrays.<Type>asList(new Uint256(executionStageDuration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setParticipationBaseline(BigInteger participationBaseline) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPARTICIPATIONBASELINE, 
                Arrays.<Type>asList(new Uint256(participationBaseline)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setParticipationFloor(BigInteger participationFloor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPARTICIPATIONFLOOR, 
                Arrays.<Type>asList(new Uint256(participationFloor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaselineUpdateFactor(BigInteger baselineUpdateFactor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBASELINEUPDATEFACTOR, 
                Arrays.<Type>asList(new Uint256(baselineUpdateFactor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaselineQuorumFactor(BigInteger baselineQuorumFactor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBASELINEQUORUMFACTOR, 
                Arrays.<Type>asList(new Uint256(baselineQuorumFactor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setConstitution(String destination, byte[] functionId, BigInteger threshold) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCONSTITUTION, 
                Arrays.<Type>asList(new Address(destination), 
                new Bytes4(functionId), 
                new Uint256(threshold)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> propose(List<BigInteger> values, List<String> destinations, byte[] data, List<BigInteger> dataLengths, String descriptionUrl, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROPOSE, 
                Arrays.<Type>asList(new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, Uint256.class)), 
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(destinations, Address.class)), 
                new DynamicBytes(data), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(dataLengths, Uint256.class)), 
                new Utf8String(descriptionUrl)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> upvote(BigInteger proposalId, BigInteger lesser, BigInteger greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPVOTE, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(lesser), 
                new Uint256(greater)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getProposalStage(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROPOSALSTAGE, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeUpvote(BigInteger lesser, BigInteger greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEUPVOTE, 
                Arrays.<Type>asList(new Uint256(lesser), 
                new Uint256(greater)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(BigInteger proposalId, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposalId, BigInteger index, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(index), 
                new Uint8(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(BigInteger proposalId, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approveHotfix(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVEHOTFIX, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isHotfixWhitelistedBy(byte[] hash, String whitelister) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISHOTFIXWHITELISTEDBY, 
                Arrays.<Type>asList(new Bytes32(hash), 
                new Address(whitelister)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> whitelistHotfix(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WHITELISTHOTFIX, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> prepareHotfix(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PREPAREHOTFIX, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeHotfix(List<BigInteger> values, List<String> destinations, byte[] data, List<BigInteger> dataLengths, byte[] salt) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTEHOTFIX, 
                Arrays.<Type>asList(new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(values, Uint256.class)), 
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(destinations, Address.class)), 
                new DynamicBytes(data), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(dataLengths, Uint256.class)), 
                new Bytes32(salt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isVoting(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISVOTING, 
                Arrays.<Type>asList(new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getApprovalStageDuration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVALSTAGEDURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getReferendumStageDuration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREFERENDUMSTAGEDURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getExecutionStageDuration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEXECUTIONSTAGEDURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getParticipationParameters() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPARTICIPATIONPARAMETERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> proposalExists(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PROPOSALEXISTS, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple5<String, BigInteger, BigInteger, BigInteger, String>> getProposal(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROPOSAL, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, BigInteger, BigInteger, BigInteger, String>>(function,
                new Callable<Tuple5<String, BigInteger, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<String, BigInteger, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, BigInteger, BigInteger, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, byte[]>> getProposalTransaction(BigInteger proposalId, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROPOSALTRANSACTION, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, byte[]>>(function,
                new Callable<Tuple3<BigInteger, String, byte[]>>() {
                    @Override
                    public Tuple3<BigInteger, String, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isApproved(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVED, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getVoteTotals(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVOTETOTALS, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getVoteRecord(String account, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVOTERECORD, 
                Arrays.<Type>asList(new Address(account), 
                new Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getQueueLength() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETQUEUELENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getUpvotes(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUPVOTES, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>> getQueue() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETQUEUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple2<List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<List> getDequeue() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDEQUEUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getUpvoteRecord(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUPVOTERECORD, 
                Arrays.<Type>asList(new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getMostRecentReferendumProposal(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMOSTRECENTREFERENDUMPROPOSAL, 
                Arrays.<Type>asList(new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> hotfixWhitelistValidatorTally(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HOTFIXWHITELISTVALIDATORTALLY, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isHotfixPassing(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISHOTFIXPASSING, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>> getHotfixRecord(byte[] hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETHOTFIXRECORD, 
                Arrays.<Type>asList(new Bytes32(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<Boolean, Boolean, BigInteger>>(function,
                new Callable<Tuple3<Boolean, Boolean, BigInteger>>() {
                    @Override
                    public Tuple3<Boolean, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Boolean, Boolean, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> dequeueProposalsIfReady() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEQUEUEPROPOSALSIFREADY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isQueued(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISQUEUED, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isProposalPassing(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISPROPOSALPASSING, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isDequeuedProposal(BigInteger proposalId, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISDEQUEUEDPROPOSAL, 
                Arrays.<Type>asList(new Uint256(proposalId), 
                new Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isDequeuedProposalExpired(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISDEQUEUEDPROPOSALEXPIRED, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isQueuedProposalExpired(BigInteger proposalId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISQUEUEDPROPOSALEXPIRED, 
                Arrays.<Type>asList(new Uint256(proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getConstitution(String destination, byte[] functionId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCONSTITUTION, 
                Arrays.<Type>asList(new Address(destination), 
                new Bytes4(functionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Governance load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Governance(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Governance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Governance(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Governance load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Governance(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Governance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Governance(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Governance> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Governance.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Governance> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Governance.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Governance> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Governance.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Governance> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Governance.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ApprovalStageDurationSetEventResponse extends BaseEventResponse {
        public BigInteger approvalStageDuration;
    }

    public static class ApproverSetEventResponse extends BaseEventResponse {
        public String approver;
    }

    public static class ConcurrentProposalsSetEventResponse extends BaseEventResponse {
        public BigInteger concurrentProposals;
    }

    public static class ConstitutionSetEventResponse extends BaseEventResponse {
        public String destination;

        public byte[] functionId;

        public BigInteger threshold;
    }

    public static class DequeueFrequencySetEventResponse extends BaseEventResponse {
        public BigInteger dequeueFrequency;
    }

    public static class ExecutionStageDurationSetEventResponse extends BaseEventResponse {
        public BigInteger executionStageDuration;
    }

    public static class HotfixApprovedEventResponse extends BaseEventResponse {
        public byte[] hash;
    }

    public static class HotfixExecutedEventResponse extends BaseEventResponse {
        public byte[] hash;
    }

    public static class HotfixPreparedEventResponse extends BaseEventResponse {
        public byte[] hash;

        public BigInteger epoch;
    }

    public static class HotfixWhitelistedEventResponse extends BaseEventResponse {
        public byte[] hash;

        public String whitelister;
    }

    public static class MinDepositSetEventResponse extends BaseEventResponse {
        public BigInteger minDeposit;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class ParticipationBaselineQuorumFactorSetEventResponse extends BaseEventResponse {
        public BigInteger baselineQuorumFactor;
    }

    public static class ParticipationBaselineUpdateFactorSetEventResponse extends BaseEventResponse {
        public BigInteger baselineUpdateFactor;
    }

    public static class ParticipationBaselineUpdatedEventResponse extends BaseEventResponse {
        public BigInteger participationBaseline;
    }

    public static class ParticipationFloorSetEventResponse extends BaseEventResponse {
        public BigInteger participationFloor;
    }

    public static class ProposalApprovedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class ProposalDequeuedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger timestamp;
    }

    public static class ProposalExecutedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class ProposalExpiredEventResponse extends BaseEventResponse {
        public BigInteger proposalId;
    }

    public static class ProposalQueuedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String proposer;

        public BigInteger transactionCount;

        public BigInteger deposit;

        public BigInteger timestamp;
    }

    public static class ProposalUpvoteRevokedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String account;

        public BigInteger revokedUpvotes;
    }

    public static class ProposalUpvotedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String account;

        public BigInteger upvotes;
    }

    public static class ProposalVotedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String account;

        public BigInteger value;

        public BigInteger weight;
    }

    public static class QueueExpirySetEventResponse extends BaseEventResponse {
        public BigInteger queueExpiry;
    }

    public static class ReferendumStageDurationSetEventResponse extends BaseEventResponse {
        public BigInteger referendumStageDuration;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }
}
