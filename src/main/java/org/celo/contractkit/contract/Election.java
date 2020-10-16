package org.celo.contractkit.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
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
import org.web3j.tuples.generated.Tuple4;
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
public class Election extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_CHECKPROOFOFPOSSESSION = "checkProofOfPossession";

    public static final String FUNC_ELECTABILITYTHRESHOLD = "electabilityThreshold";

    public static final String FUNC_ELECTABLEVALIDATORS = "electableValidators";

    public static final String FUNC_FRACTIONMULEXP = "fractionMulExp";

    public static final String FUNC_GETBLOCKNUMBERFROMHEADER = "getBlockNumberFromHeader";

    public static final String FUNC_GETEPOCHNUMBER = "getEpochNumber";

    public static final String FUNC_GETEPOCHNUMBEROFBLOCK = "getEpochNumberOfBlock";

    public static final String FUNC_GETEPOCHSIZE = "getEpochSize";

    public static final String FUNC_GETPARENTSEALBITMAP = "getParentSealBitmap";

    public static final String FUNC_GETVERIFIEDSEALBITMAPFROMHEADER = "getVerifiedSealBitmapFromHeader";

    public static final String FUNC_HASHHEADER = "hashHeader";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_MAXNUMGROUPSVOTEDFOR = "maxNumGroupsVotedFor";

    public static final String FUNC_MINQUORUMSIZE = "minQuorumSize";

    public static final String FUNC_MINQUORUMSIZEINCURRENTSET = "minQuorumSizeInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINCURRENTSET = "numberValidatorsInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINSET = "numberValidatorsInSet";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMCURRENTSET = "validatorSignerAddressFromCurrentSet";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMSET = "validatorSignerAddressFromSet";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETELECTABLEVALIDATORS = "setElectableValidators";

    public static final String FUNC_GETELECTABLEVALIDATORS = "getElectableValidators";

    public static final String FUNC_SETMAXNUMGROUPSVOTEDFOR = "setMaxNumGroupsVotedFor";

    public static final String FUNC_SETELECTABILITYTHRESHOLD = "setElectabilityThreshold";

    public static final String FUNC_GETELECTABILITYTHRESHOLD = "getElectabilityThreshold";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_ACTIVATE = "activate";

    public static final String FUNC_HASACTIVATABLEPENDINGVOTES = "hasActivatablePendingVotes";

    public static final String FUNC_REVOKEPENDING = "revokePending";

    public static final String FUNC_REVOKEALLACTIVE = "revokeAllActive";

    public static final String FUNC_REVOKEACTIVE = "revokeActive";

    public static final String FUNC_GETTOTALVOTESBYACCOUNT = "getTotalVotesByAccount";

    public static final String FUNC_GETPENDINGVOTESFORGROUPBYACCOUNT = "getPendingVotesForGroupByAccount";

    public static final String FUNC_GETACTIVEVOTESFORGROUPBYACCOUNT = "getActiveVotesForGroupByAccount";

    public static final String FUNC_GETTOTALVOTESFORGROUPBYACCOUNT = "getTotalVotesForGroupByAccount";

    public static final String FUNC_GETACTIVEVOTEUNITSFORGROUPBYACCOUNT = "getActiveVoteUnitsForGroupByAccount";

    public static final String FUNC_GETACTIVEVOTEUNITSFORGROUP = "getActiveVoteUnitsForGroup";

    public static final String FUNC_GETTOTALVOTESFORGROUP = "getTotalVotesForGroup";

    public static final String FUNC_GETACTIVEVOTESFORGROUP = "getActiveVotesForGroup";

    public static final String FUNC_GETPENDINGVOTESFORGROUP = "getPendingVotesForGroup";

    public static final String FUNC_GETGROUPELIGIBILITY = "getGroupEligibility";

    public static final String FUNC_GETGROUPEPOCHREWARDS = "getGroupEpochRewards";

    public static final String FUNC_DISTRIBUTEEPOCHREWARDS = "distributeEpochRewards";

    public static final String FUNC_MARKGROUPINELIGIBLE = "markGroupIneligible";

    public static final String FUNC_MARKGROUPELIGIBLE = "markGroupEligible";

    public static final String FUNC_GETGROUPSVOTEDFORBYACCOUNT = "getGroupsVotedForByAccount";

    public static final String FUNC_CANRECEIVEVOTES = "canReceiveVotes";

    public static final String FUNC_GETNUMVOTESRECEIVABLE = "getNumVotesReceivable";

    public static final String FUNC_GETTOTALVOTES = "getTotalVotes";

    public static final String FUNC_GETACTIVEVOTES = "getActiveVotes";

    public static final String FUNC_GETELIGIBLEVALIDATORGROUPS = "getEligibleValidatorGroups";

    public static final String FUNC_GETTOTALVOTESFORELIGIBLEVALIDATORGROUPS = "getTotalVotesForEligibleValidatorGroups";

    public static final String FUNC_ELECTVALIDATORSIGNERS = "electValidatorSigners";

    public static final String FUNC_ELECTNVALIDATORSIGNERS = "electNValidatorSigners";

    public static final String FUNC_GETCURRENTVALIDATORSIGNERS = "getCurrentValidatorSigners";

    public static final String FUNC_FORCEDECREMENTVOTES = "forceDecrementVotes";

    public static final Event ELECTABILITYTHRESHOLDSET_EVENT = new Event("ElectabilityThresholdSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ELECTABLEVALIDATORSSET_EVENT = new Event("ElectableValidatorsSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event EPOCHREWARDSDISTRIBUTEDTOVOTERS_EVENT = new Event("EpochRewardsDistributedToVoters", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MAXNUMGROUPSVOTEDFORSET_EVENT = new Event("MaxNumGroupsVotedForSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPACTIVEVOTEREVOKED_EVENT = new Event("ValidatorGroupActiveVoteRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPMARKEDELIGIBLE_EVENT = new Event("ValidatorGroupMarkedEligible", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPMARKEDINELIGIBLE_EVENT = new Event("ValidatorGroupMarkedIneligible", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPPENDINGVOTEREVOKED_EVENT = new Event("ValidatorGroupPendingVoteRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPVOTEACTIVATED_EVENT = new Event("ValidatorGroupVoteActivated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPVOTECAST_EVENT = new Event("ValidatorGroupVoteCast", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ElectabilityThresholdSetEventResponse> getElectabilityThresholdSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ELECTABILITYTHRESHOLDSET_EVENT, transactionReceipt);
        ArrayList<ElectabilityThresholdSetEventResponse> responses = new ArrayList<ElectabilityThresholdSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ElectabilityThresholdSetEventResponse typedResponse = new ElectabilityThresholdSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.electabilityThreshold = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ElectabilityThresholdSetEventResponse> electabilityThresholdSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ElectabilityThresholdSetEventResponse>() {
            @Override
            public ElectabilityThresholdSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ELECTABILITYTHRESHOLDSET_EVENT, log);
                ElectabilityThresholdSetEventResponse typedResponse = new ElectabilityThresholdSetEventResponse();
                typedResponse.log = log;
                typedResponse.electabilityThreshold = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ElectabilityThresholdSetEventResponse> electabilityThresholdSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELECTABILITYTHRESHOLDSET_EVENT));
        return electabilityThresholdSetEventFlowable(filter);
    }

    public List<ElectableValidatorsSetEventResponse> getElectableValidatorsSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ELECTABLEVALIDATORSSET_EVENT, transactionReceipt);
        ArrayList<ElectableValidatorsSetEventResponse> responses = new ArrayList<ElectableValidatorsSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ElectableValidatorsSetEventResponse typedResponse = new ElectableValidatorsSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.min = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.max = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ElectableValidatorsSetEventResponse> electableValidatorsSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ElectableValidatorsSetEventResponse>() {
            @Override
            public ElectableValidatorsSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ELECTABLEVALIDATORSSET_EVENT, log);
                ElectableValidatorsSetEventResponse typedResponse = new ElectableValidatorsSetEventResponse();
                typedResponse.log = log;
                typedResponse.min = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.max = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ElectableValidatorsSetEventResponse> electableValidatorsSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELECTABLEVALIDATORSSET_EVENT));
        return electableValidatorsSetEventFlowable(filter);
    }

    public List<EpochRewardsDistributedToVotersEventResponse> getEpochRewardsDistributedToVotersEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(EPOCHREWARDSDISTRIBUTEDTOVOTERS_EVENT, transactionReceipt);
        ArrayList<EpochRewardsDistributedToVotersEventResponse> responses = new ArrayList<EpochRewardsDistributedToVotersEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EpochRewardsDistributedToVotersEventResponse typedResponse = new EpochRewardsDistributedToVotersEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EpochRewardsDistributedToVotersEventResponse> epochRewardsDistributedToVotersEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EpochRewardsDistributedToVotersEventResponse>() {
            @Override
            public EpochRewardsDistributedToVotersEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(EPOCHREWARDSDISTRIBUTEDTOVOTERS_EVENT, log);
                EpochRewardsDistributedToVotersEventResponse typedResponse = new EpochRewardsDistributedToVotersEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EpochRewardsDistributedToVotersEventResponse> epochRewardsDistributedToVotersEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EPOCHREWARDSDISTRIBUTEDTOVOTERS_EVENT));
        return epochRewardsDistributedToVotersEventFlowable(filter);
    }

    public List<MaxNumGroupsVotedForSetEventResponse> getMaxNumGroupsVotedForSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MAXNUMGROUPSVOTEDFORSET_EVENT, transactionReceipt);
        ArrayList<MaxNumGroupsVotedForSetEventResponse> responses = new ArrayList<MaxNumGroupsVotedForSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MaxNumGroupsVotedForSetEventResponse typedResponse = new MaxNumGroupsVotedForSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.maxNumGroupsVotedFor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MaxNumGroupsVotedForSetEventResponse> maxNumGroupsVotedForSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MaxNumGroupsVotedForSetEventResponse>() {
            @Override
            public MaxNumGroupsVotedForSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MAXNUMGROUPSVOTEDFORSET_EVENT, log);
                MaxNumGroupsVotedForSetEventResponse typedResponse = new MaxNumGroupsVotedForSetEventResponse();
                typedResponse.log = log;
                typedResponse.maxNumGroupsVotedFor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MaxNumGroupsVotedForSetEventResponse> maxNumGroupsVotedForSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MAXNUMGROUPSVOTEDFORSET_EVENT));
        return maxNumGroupsVotedForSetEventFlowable(filter);
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

    public List<ValidatorGroupActiveVoteRevokedEventResponse> getValidatorGroupActiveVoteRevokedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPACTIVEVOTEREVOKED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupActiveVoteRevokedEventResponse> responses = new ArrayList<ValidatorGroupActiveVoteRevokedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupActiveVoteRevokedEventResponse typedResponse = new ValidatorGroupActiveVoteRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.units = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupActiveVoteRevokedEventResponse> validatorGroupActiveVoteRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupActiveVoteRevokedEventResponse>() {
            @Override
            public ValidatorGroupActiveVoteRevokedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPACTIVEVOTEREVOKED_EVENT, log);
                ValidatorGroupActiveVoteRevokedEventResponse typedResponse = new ValidatorGroupActiveVoteRevokedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.units = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupActiveVoteRevokedEventResponse> validatorGroupActiveVoteRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPACTIVEVOTEREVOKED_EVENT));
        return validatorGroupActiveVoteRevokedEventFlowable(filter);
    }

    public List<ValidatorGroupMarkedEligibleEventResponse> getValidatorGroupMarkedEligibleEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPMARKEDELIGIBLE_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupMarkedEligibleEventResponse> responses = new ArrayList<ValidatorGroupMarkedEligibleEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupMarkedEligibleEventResponse typedResponse = new ValidatorGroupMarkedEligibleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupMarkedEligibleEventResponse> validatorGroupMarkedEligibleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupMarkedEligibleEventResponse>() {
            @Override
            public ValidatorGroupMarkedEligibleEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPMARKEDELIGIBLE_EVENT, log);
                ValidatorGroupMarkedEligibleEventResponse typedResponse = new ValidatorGroupMarkedEligibleEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupMarkedEligibleEventResponse> validatorGroupMarkedEligibleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPMARKEDELIGIBLE_EVENT));
        return validatorGroupMarkedEligibleEventFlowable(filter);
    }

    public List<ValidatorGroupMarkedIneligibleEventResponse> getValidatorGroupMarkedIneligibleEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPMARKEDINELIGIBLE_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupMarkedIneligibleEventResponse> responses = new ArrayList<ValidatorGroupMarkedIneligibleEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupMarkedIneligibleEventResponse typedResponse = new ValidatorGroupMarkedIneligibleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupMarkedIneligibleEventResponse> validatorGroupMarkedIneligibleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupMarkedIneligibleEventResponse>() {
            @Override
            public ValidatorGroupMarkedIneligibleEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPMARKEDINELIGIBLE_EVENT, log);
                ValidatorGroupMarkedIneligibleEventResponse typedResponse = new ValidatorGroupMarkedIneligibleEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupMarkedIneligibleEventResponse> validatorGroupMarkedIneligibleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPMARKEDINELIGIBLE_EVENT));
        return validatorGroupMarkedIneligibleEventFlowable(filter);
    }

    public List<ValidatorGroupPendingVoteRevokedEventResponse> getValidatorGroupPendingVoteRevokedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPPENDINGVOTEREVOKED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupPendingVoteRevokedEventResponse> responses = new ArrayList<ValidatorGroupPendingVoteRevokedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupPendingVoteRevokedEventResponse typedResponse = new ValidatorGroupPendingVoteRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupPendingVoteRevokedEventResponse> validatorGroupPendingVoteRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupPendingVoteRevokedEventResponse>() {
            @Override
            public ValidatorGroupPendingVoteRevokedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPPENDINGVOTEREVOKED_EVENT, log);
                ValidatorGroupPendingVoteRevokedEventResponse typedResponse = new ValidatorGroupPendingVoteRevokedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupPendingVoteRevokedEventResponse> validatorGroupPendingVoteRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPPENDINGVOTEREVOKED_EVENT));
        return validatorGroupPendingVoteRevokedEventFlowable(filter);
    }

    public List<ValidatorGroupVoteActivatedEventResponse> getValidatorGroupVoteActivatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPVOTEACTIVATED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupVoteActivatedEventResponse> responses = new ArrayList<ValidatorGroupVoteActivatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupVoteActivatedEventResponse typedResponse = new ValidatorGroupVoteActivatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.units = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupVoteActivatedEventResponse> validatorGroupVoteActivatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupVoteActivatedEventResponse>() {
            @Override
            public ValidatorGroupVoteActivatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPVOTEACTIVATED_EVENT, log);
                ValidatorGroupVoteActivatedEventResponse typedResponse = new ValidatorGroupVoteActivatedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.units = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupVoteActivatedEventResponse> validatorGroupVoteActivatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPVOTEACTIVATED_EVENT));
        return validatorGroupVoteActivatedEventFlowable(filter);
    }

    public List<ValidatorGroupVoteCastEventResponse> getValidatorGroupVoteCastEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPVOTECAST_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupVoteCastEventResponse> responses = new ArrayList<ValidatorGroupVoteCastEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupVoteCastEventResponse typedResponse = new ValidatorGroupVoteCastEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupVoteCastEventResponse> validatorGroupVoteCastEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupVoteCastEventResponse>() {
            @Override
            public ValidatorGroupVoteCastEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPVOTECAST_EVENT, log);
                ValidatorGroupVoteCastEventResponse typedResponse = new ValidatorGroupVoteCastEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupVoteCastEventResponse> validatorGroupVoteCastEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPVOTECAST_EVENT));
        return validatorGroupVoteCastEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKPROOFOFPOSSESSION, 
                Arrays.<Type>asList(new Address(sender), 
                new DynamicBytes(blsKey), 
                new DynamicBytes(blsPop)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> electabilityThreshold() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ELECTABILITYTHRESHOLD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> electableValidators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ELECTABLEVALIDATORS, 
                Arrays.<Type>asList(), 
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

    public RemoteFunctionCall<BigInteger> maxNumGroupsVotedFor() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXNUMGROUPSVOTEDFOR, 
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger minElectableValidators, BigInteger maxElectableValidators, BigInteger _maxNumGroupsVotedFor, BigInteger _electabilityThreshold) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(registryAddress), 
                new Uint256(minElectableValidators), 
                new Uint256(maxElectableValidators), 
                new Uint256(_maxNumGroupsVotedFor), 
                new Uint256(_electabilityThreshold)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setElectableValidators(BigInteger min, BigInteger max) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETELECTABLEVALIDATORS, 
                Arrays.<Type>asList(new Uint256(min), 
                new Uint256(max)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getElectableValidators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETELECTABLEVALIDATORS, 
                Arrays.<Type>asList(), 
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

    public RemoteFunctionCall<TransactionReceipt> setMaxNumGroupsVotedFor(BigInteger _maxNumGroupsVotedFor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMAXNUMGROUPSVOTEDFOR, 
                Arrays.<Type>asList(new Uint256(_maxNumGroupsVotedFor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setElectabilityThreshold(BigInteger threshold) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETELECTABILITYTHRESHOLD, 
                Arrays.<Type>asList(new Uint256(threshold)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getElectabilityThreshold() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETELECTABILITYTHRESHOLD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(String group, BigInteger value, String lesser, String greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(value), 
                new Address(lesser), 
                new Address(greater)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> activate(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACTIVATE, 
                Arrays.<Type>asList(new Address(group)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> hasActivatablePendingVotes(String account, String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASACTIVATABLEPENDINGVOTES, 
                Arrays.<Type>asList(new Address(account), 
                new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> revokePending(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEPENDING, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(value), 
                new Address(lesser), 
                new Address(greater), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeAllActive(String group, String lesser, String greater, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEALLACTIVE, 
                Arrays.<Type>asList(new Address(group), 
                new Address(lesser), 
                new Address(greater), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeActive(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEACTIVE, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(value), 
                new Address(lesser), 
                new Address(greater), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesByAccount(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALVOTESBYACCOUNT, 
                Arrays.<Type>asList(new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPendingVotesForGroupByAccount(String group, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPENDINGVOTESFORGROUPBYACCOUNT, 
                Arrays.<Type>asList(new Address(group), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getActiveVotesForGroupByAccount(String group, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACTIVEVOTESFORGROUPBYACCOUNT, 
                Arrays.<Type>asList(new Address(group), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesForGroupByAccount(String group, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALVOTESFORGROUPBYACCOUNT, 
                Arrays.<Type>asList(new Address(group), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getActiveVoteUnitsForGroupByAccount(String group, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACTIVEVOTEUNITSFORGROUPBYACCOUNT, 
                Arrays.<Type>asList(new Address(group), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getActiveVoteUnitsForGroup(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACTIVEVOTEUNITSFORGROUP, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotesForGroup(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALVOTESFORGROUP, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getActiveVotesForGroup(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACTIVEVOTESFORGROUP, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPendingVotesForGroup(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPENDINGVOTESFORGROUP, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> getGroupEligibility(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPELIGIBILITY, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getGroupEpochRewards(String group, BigInteger totalEpochRewards, List<BigInteger> uptimes) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPEPOCHREWARDS, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(totalEpochRewards), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(uptimes, Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeEpochRewards(String group, BigInteger value, String lesser, String greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISTRIBUTEEPOCHREWARDS, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(value), 
                new Address(lesser), 
                new Address(greater)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markGroupIneligible(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MARKGROUPINELIGIBLE, 
                Arrays.<Type>asList(new Address(group)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> markGroupEligible(String group, String lesser, String greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MARKGROUPELIGIBLE, 
                Arrays.<Type>asList(new Address(group), 
                new Address(lesser), 
                new Address(greater)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getGroupsVotedForByAccount(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPSVOTEDFORBYACCOUNT, 
                Arrays.<Type>asList(new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<Boolean> canReceiveVotes(String group, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANRECEIVEVOTES, 
                Arrays.<Type>asList(new Address(group), 
                new Uint256(value)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getNumVotesReceivable(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMVOTESRECEIVABLE, 
                Arrays.<Type>asList(new Address(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getTotalVotes() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALVOTES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getActiveVotes() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACTIVEVOTES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getEligibleValidatorGroups() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETELIGIBLEVALIDATORGROUPS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<Tuple2<List<String>, List<BigInteger>>> getTotalVotesForEligibleValidatorGroups() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALVOTESFORELIGIBLEVALIDATORGROUPS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple2<List<String>, List<BigInteger>>>(function,
                new Callable<Tuple2<List<String>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<String>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<String>, List<BigInteger>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<List> electValidatorSigners() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ELECTVALIDATORSIGNERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<List> electNValidatorSigners(BigInteger minElectableValidators, BigInteger maxElectableValidators) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ELECTNVALIDATORSIGNERS, 
                Arrays.<Type>asList(new Uint256(minElectableValidators), 
                new Uint256(maxElectableValidators)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<List> getCurrentValidatorSigners() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCURRENTVALIDATORSIGNERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> forceDecrementVotes(String account, BigInteger value, List<String> lessers, List<String> greaters, List<BigInteger> indices) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FORCEDECREMENTVOTES, 
                Arrays.<Type>asList(new Address(account), 
                new Uint256(value), 
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(lessers, Address.class)), 
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(greaters, Address.class)), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(indices, Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ElectabilityThresholdSetEventResponse extends BaseEventResponse {
        public BigInteger electabilityThreshold;
    }

    public static class ElectableValidatorsSetEventResponse extends BaseEventResponse {
        public BigInteger min;

        public BigInteger max;
    }

    public static class EpochRewardsDistributedToVotersEventResponse extends BaseEventResponse {
        public String group;

        public BigInteger value;
    }

    public static class MaxNumGroupsVotedForSetEventResponse extends BaseEventResponse {
        public BigInteger maxNumGroupsVotedFor;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }

    public static class ValidatorGroupActiveVoteRevokedEventResponse extends BaseEventResponse {
        public String account;

        public String group;

        public BigInteger value;

        public BigInteger units;
    }

    public static class ValidatorGroupMarkedEligibleEventResponse extends BaseEventResponse {
        public String group;
    }

    public static class ValidatorGroupMarkedIneligibleEventResponse extends BaseEventResponse {
        public String group;
    }

    public static class ValidatorGroupPendingVoteRevokedEventResponse extends BaseEventResponse {
        public String account;

        public String group;

        public BigInteger value;
    }

    public static class ValidatorGroupVoteActivatedEventResponse extends BaseEventResponse {
        public String account;

        public String group;

        public BigInteger value;

        public BigInteger units;
    }

    public static class ValidatorGroupVoteCastEventResponse extends BaseEventResponse {
        public String account;

        public String group;

        public BigInteger value;
    }
}
