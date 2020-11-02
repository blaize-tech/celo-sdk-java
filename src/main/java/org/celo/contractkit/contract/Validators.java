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
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple7;
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
public class Validators extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_CHECKPROOFOFPOSSESSION = "checkProofOfPossession";

    public static final String FUNC_COMMISSIONUPDATEDELAY = "commissionUpdateDelay";

    public static final String FUNC_FRACTIONMULEXP = "fractionMulExp";

    public static final String FUNC_GETBLOCKNUMBERFROMHEADER = "getBlockNumberFromHeader";

    public static final String FUNC_GETEPOCHNUMBER = "getEpochNumber";

    public static final String FUNC_GETEPOCHNUMBEROFBLOCK = "getEpochNumberOfBlock";

    public static final String FUNC_GETEPOCHSIZE = "getEpochSize";

    public static final String FUNC_GETPARENTSEALBITMAP = "getParentSealBitmap";

    public static final String FUNC_GETVERIFIEDSEALBITMAPFROMHEADER = "getVerifiedSealBitmapFromHeader";

    public static final String FUNC_GROUPLOCKEDGOLDREQUIREMENTS = "groupLockedGoldRequirements";

    public static final String FUNC_HASHHEADER = "hashHeader";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_MAXGROUPSIZE = "maxGroupSize";

    public static final String FUNC_MEMBERSHIPHISTORYLENGTH = "membershipHistoryLength";

    public static final String FUNC_MINQUORUMSIZE = "minQuorumSize";

    public static final String FUNC_MINQUORUMSIZEINCURRENTSET = "minQuorumSizeInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINCURRENTSET = "numberValidatorsInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINSET = "numberValidatorsInSet";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_SLASHINGMULTIPLIERRESETPERIOD = "slashingMultiplierResetPeriod";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VALIDATORLOCKEDGOLDREQUIREMENTS = "validatorLockedGoldRequirements";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMCURRENTSET = "validatorSignerAddressFromCurrentSet";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMSET = "validatorSignerAddressFromSet";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETCOMMISSIONUPDATEDELAY = "setCommissionUpdateDelay";

    public static final String FUNC_SETMAXGROUPSIZE = "setMaxGroupSize";

    public static final String FUNC_SETMEMBERSHIPHISTORYLENGTH = "setMembershipHistoryLength";

    public static final String FUNC_SETVALIDATORSCOREPARAMETERS = "setValidatorScoreParameters";

    public static final String FUNC_GETMAXGROUPSIZE = "getMaxGroupSize";

    public static final String FUNC_GETCOMMISSIONUPDATEDELAY = "getCommissionUpdateDelay";

    public static final String FUNC_SETGROUPLOCKEDGOLDREQUIREMENTS = "setGroupLockedGoldRequirements";

    public static final String FUNC_SETVALIDATORLOCKEDGOLDREQUIREMENTS = "setValidatorLockedGoldRequirements";

    public static final String FUNC_REGISTERVALIDATOR = "registerValidator";

    public static final String FUNC_GETVALIDATORSCOREPARAMETERS = "getValidatorScoreParameters";

    public static final String FUNC_GETMEMBERSHIPHISTORY = "getMembershipHistory";

    public static final String FUNC_CALCULATEEPOCHSCORE = "calculateEpochScore";

    public static final String FUNC_CALCULATEGROUPEPOCHSCORE = "calculateGroupEpochScore";

    public static final String FUNC_UPDATEVALIDATORSCOREFROMSIGNER = "updateValidatorScoreFromSigner";

    public static final String FUNC_DISTRIBUTEEPOCHPAYMENTSFROMSIGNER = "distributeEpochPaymentsFromSigner";

    public static final String FUNC_DEREGISTERVALIDATOR = "deregisterValidator";

    public static final String FUNC_AFFILIATE = "affiliate";

    public static final String FUNC_DEAFFILIATE = "deaffiliate";

    public static final String FUNC_UPDATEBLSPUBLICKEY = "updateBlsPublicKey";

    public static final String FUNC_UPDATEECDSAPUBLICKEY = "updateEcdsaPublicKey";

    public static final String FUNC_UPDATEPUBLICKEYS = "updatePublicKeys";

    public static final String FUNC_REGISTERVALIDATORGROUP = "registerValidatorGroup";

    public static final String FUNC_DEREGISTERVALIDATORGROUP = "deregisterValidatorGroup";

    public static final String FUNC_ADDMEMBER = "addMember";

    public static final String FUNC_ADDFIRSTMEMBER = "addFirstMember";

    public static final String FUNC_REMOVEMEMBER = "removeMember";

    public static final String FUNC_REORDERMEMBER = "reorderMember";

    public static final String FUNC_SETNEXTCOMMISSIONUPDATE = "setNextCommissionUpdate";

    public static final String FUNC_UPDATECOMMISSION = "updateCommission";

    public static final String FUNC_GETACCOUNTLOCKEDGOLDREQUIREMENT = "getAccountLockedGoldRequirement";

    public static final String FUNC_MEETSACCOUNTLOCKEDGOLDREQUIREMENTS = "meetsAccountLockedGoldRequirements";

    public static final String FUNC_GETVALIDATORBLSPUBLICKEYFROMSIGNER = "getValidatorBlsPublicKeyFromSigner";

    public static final String FUNC_GETVALIDATOR = "getValidator";

    public static final String FUNC_GETVALIDATORGROUP = "getValidatorGroup";

    public static final String FUNC_GETGROUPNUMMEMBERS = "getGroupNumMembers";

    public static final String FUNC_GETTOPGROUPVALIDATORS = "getTopGroupValidators";

    public static final String FUNC_GETGROUPSNUMMEMBERS = "getGroupsNumMembers";

    public static final String FUNC_GETNUMREGISTEREDVALIDATORS = "getNumRegisteredValidators";

    public static final String FUNC_GETVALIDATORLOCKEDGOLDREQUIREMENTS = "getValidatorLockedGoldRequirements";

    public static final String FUNC_GETGROUPLOCKEDGOLDREQUIREMENTS = "getGroupLockedGoldRequirements";

    public static final String FUNC_GETREGISTEREDVALIDATORS = "getRegisteredValidators";

    public static final String FUNC_GETREGISTEREDVALIDATORSIGNERS = "getRegisteredValidatorSigners";

    public static final String FUNC_GETREGISTEREDVALIDATORGROUPS = "getRegisteredValidatorGroups";

    public static final String FUNC_ISVALIDATORGROUP = "isValidatorGroup";

    public static final String FUNC_ISVALIDATOR = "isValidator";

    public static final String FUNC_GETMEMBERSHIPINLASTEPOCHFROMSIGNER = "getMembershipInLastEpochFromSigner";

    public static final String FUNC_GETMEMBERSHIPINLASTEPOCH = "getMembershipInLastEpoch";

    public static final String FUNC_FORCEDEAFFILIATEIFVALIDATOR = "forceDeaffiliateIfValidator";

    public static final String FUNC_SETSLASHINGMULTIPLIERRESETPERIOD = "setSlashingMultiplierResetPeriod";

    public static final String FUNC_RESETSLASHINGMULTIPLIER = "resetSlashingMultiplier";

    public static final String FUNC_HALVESLASHINGMULTIPLIER = "halveSlashingMultiplier";

    public static final String FUNC_GETVALIDATORGROUPSLASHINGMULTIPLIER = "getValidatorGroupSlashingMultiplier";

    public static final String FUNC_GROUPMEMBERSHIPINEPOCH = "groupMembershipInEpoch";

    public static final Event COMMISSIONUPDATEDELAYSET_EVENT = new Event("CommissionUpdateDelaySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event GROUPLOCKEDGOLDREQUIREMENTSSET_EVENT = new Event("GroupLockedGoldRequirementsSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MAXGROUPSIZESET_EVENT = new Event("MaxGroupSizeSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event MEMBERSHIPHISTORYLENGTHSET_EVENT = new Event("MembershipHistoryLengthSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORAFFILIATED_EVENT = new Event("ValidatorAffiliated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORBLSPUBLICKEYUPDATED_EVENT = new Event("ValidatorBlsPublicKeyUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event VALIDATORDEAFFILIATED_EVENT = new Event("ValidatorDeaffiliated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORDEREGISTERED_EVENT = new Event("ValidatorDeregistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORECDSAPUBLICKEYUPDATED_EVENT = new Event("ValidatorEcdsaPublicKeyUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event VALIDATOREPOCHPAYMENTDISTRIBUTED_EVENT = new Event("ValidatorEpochPaymentDistributed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPCOMMISSIONUPDATEQUEUED_EVENT = new Event("ValidatorGroupCommissionUpdateQueued", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPCOMMISSIONUPDATED_EVENT = new Event("ValidatorGroupCommissionUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORGROUPDEREGISTERED_EVENT = new Event("ValidatorGroupDeregistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPMEMBERADDED_EVENT = new Event("ValidatorGroupMemberAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPMEMBERREMOVED_EVENT = new Event("ValidatorGroupMemberRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPMEMBERREORDERED_EVENT = new Event("ValidatorGroupMemberReordered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORGROUPREGISTERED_EVENT = new Event("ValidatorGroupRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORLOCKEDGOLDREQUIREMENTSSET_EVENT = new Event("ValidatorLockedGoldRequirementsSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORREGISTERED_EVENT = new Event("ValidatorRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event VALIDATORSCOREPARAMETERSSET_EVENT = new Event("ValidatorScoreParametersSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATORSCOREUPDATED_EVENT = new Event("ValidatorScoreUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Validators(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Validators(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Validators(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Validators(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CommissionUpdateDelaySetEventResponse> getCommissionUpdateDelaySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(COMMISSIONUPDATEDELAYSET_EVENT, transactionReceipt);
        ArrayList<CommissionUpdateDelaySetEventResponse> responses = new ArrayList<CommissionUpdateDelaySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CommissionUpdateDelaySetEventResponse typedResponse = new CommissionUpdateDelaySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.delay = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CommissionUpdateDelaySetEventResponse> commissionUpdateDelaySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CommissionUpdateDelaySetEventResponse>() {
            @Override
            public CommissionUpdateDelaySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(COMMISSIONUPDATEDELAYSET_EVENT, log);
                CommissionUpdateDelaySetEventResponse typedResponse = new CommissionUpdateDelaySetEventResponse();
                typedResponse.log = log;
                typedResponse.delay = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CommissionUpdateDelaySetEventResponse> commissionUpdateDelaySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COMMISSIONUPDATEDELAYSET_EVENT));
        return commissionUpdateDelaySetEventFlowable(filter);
    }

    public List<GroupLockedGoldRequirementsSetEventResponse> getGroupLockedGoldRequirementsSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(GROUPLOCKEDGOLDREQUIREMENTSSET_EVENT, transactionReceipt);
        ArrayList<GroupLockedGoldRequirementsSetEventResponse> responses = new ArrayList<GroupLockedGoldRequirementsSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            GroupLockedGoldRequirementsSetEventResponse typedResponse = new GroupLockedGoldRequirementsSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GroupLockedGoldRequirementsSetEventResponse> groupLockedGoldRequirementsSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, GroupLockedGoldRequirementsSetEventResponse>() {
            @Override
            public GroupLockedGoldRequirementsSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(GROUPLOCKEDGOLDREQUIREMENTSSET_EVENT, log);
                GroupLockedGoldRequirementsSetEventResponse typedResponse = new GroupLockedGoldRequirementsSetEventResponse();
                typedResponse.log = log;
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GroupLockedGoldRequirementsSetEventResponse> groupLockedGoldRequirementsSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GROUPLOCKEDGOLDREQUIREMENTSSET_EVENT));
        return groupLockedGoldRequirementsSetEventFlowable(filter);
    }

    public List<MaxGroupSizeSetEventResponse> getMaxGroupSizeSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MAXGROUPSIZESET_EVENT, transactionReceipt);
        ArrayList<MaxGroupSizeSetEventResponse> responses = new ArrayList<MaxGroupSizeSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MaxGroupSizeSetEventResponse typedResponse = new MaxGroupSizeSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MaxGroupSizeSetEventResponse> maxGroupSizeSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MaxGroupSizeSetEventResponse>() {
            @Override
            public MaxGroupSizeSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MAXGROUPSIZESET_EVENT, log);
                MaxGroupSizeSetEventResponse typedResponse = new MaxGroupSizeSetEventResponse();
                typedResponse.log = log;
                typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MaxGroupSizeSetEventResponse> maxGroupSizeSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MAXGROUPSIZESET_EVENT));
        return maxGroupSizeSetEventFlowable(filter);
    }

    public List<MembershipHistoryLengthSetEventResponse> getMembershipHistoryLengthSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MEMBERSHIPHISTORYLENGTHSET_EVENT, transactionReceipt);
        ArrayList<MembershipHistoryLengthSetEventResponse> responses = new ArrayList<MembershipHistoryLengthSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MembershipHistoryLengthSetEventResponse typedResponse = new MembershipHistoryLengthSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.length = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MembershipHistoryLengthSetEventResponse> membershipHistoryLengthSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MembershipHistoryLengthSetEventResponse>() {
            @Override
            public MembershipHistoryLengthSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MEMBERSHIPHISTORYLENGTHSET_EVENT, log);
                MembershipHistoryLengthSetEventResponse typedResponse = new MembershipHistoryLengthSetEventResponse();
                typedResponse.log = log;
                typedResponse.length = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MembershipHistoryLengthSetEventResponse> membershipHistoryLengthSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MEMBERSHIPHISTORYLENGTHSET_EVENT));
        return membershipHistoryLengthSetEventFlowable(filter);
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

    public List<ValidatorAffiliatedEventResponse> getValidatorAffiliatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORAFFILIATED_EVENT, transactionReceipt);
        ArrayList<ValidatorAffiliatedEventResponse> responses = new ArrayList<ValidatorAffiliatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorAffiliatedEventResponse typedResponse = new ValidatorAffiliatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorAffiliatedEventResponse> validatorAffiliatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorAffiliatedEventResponse>() {
            @Override
            public ValidatorAffiliatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORAFFILIATED_EVENT, log);
                ValidatorAffiliatedEventResponse typedResponse = new ValidatorAffiliatedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorAffiliatedEventResponse> validatorAffiliatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORAFFILIATED_EVENT));
        return validatorAffiliatedEventFlowable(filter);
    }

    public List<ValidatorBlsPublicKeyUpdatedEventResponse> getValidatorBlsPublicKeyUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORBLSPUBLICKEYUPDATED_EVENT, transactionReceipt);
        ArrayList<ValidatorBlsPublicKeyUpdatedEventResponse> responses = new ArrayList<ValidatorBlsPublicKeyUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorBlsPublicKeyUpdatedEventResponse typedResponse = new ValidatorBlsPublicKeyUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.blsPublicKey = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorBlsPublicKeyUpdatedEventResponse> validatorBlsPublicKeyUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorBlsPublicKeyUpdatedEventResponse>() {
            @Override
            public ValidatorBlsPublicKeyUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORBLSPUBLICKEYUPDATED_EVENT, log);
                ValidatorBlsPublicKeyUpdatedEventResponse typedResponse = new ValidatorBlsPublicKeyUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.blsPublicKey = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorBlsPublicKeyUpdatedEventResponse> validatorBlsPublicKeyUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORBLSPUBLICKEYUPDATED_EVENT));
        return validatorBlsPublicKeyUpdatedEventFlowable(filter);
    }

    public List<ValidatorDeaffiliatedEventResponse> getValidatorDeaffiliatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORDEAFFILIATED_EVENT, transactionReceipt);
        ArrayList<ValidatorDeaffiliatedEventResponse> responses = new ArrayList<ValidatorDeaffiliatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorDeaffiliatedEventResponse typedResponse = new ValidatorDeaffiliatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorDeaffiliatedEventResponse> validatorDeaffiliatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorDeaffiliatedEventResponse>() {
            @Override
            public ValidatorDeaffiliatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORDEAFFILIATED_EVENT, log);
                ValidatorDeaffiliatedEventResponse typedResponse = new ValidatorDeaffiliatedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorDeaffiliatedEventResponse> validatorDeaffiliatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORDEAFFILIATED_EVENT));
        return validatorDeaffiliatedEventFlowable(filter);
    }

    public List<ValidatorDeregisteredEventResponse> getValidatorDeregisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORDEREGISTERED_EVENT, transactionReceipt);
        ArrayList<ValidatorDeregisteredEventResponse> responses = new ArrayList<ValidatorDeregisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorDeregisteredEventResponse typedResponse = new ValidatorDeregisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorDeregisteredEventResponse> validatorDeregisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorDeregisteredEventResponse>() {
            @Override
            public ValidatorDeregisteredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORDEREGISTERED_EVENT, log);
                ValidatorDeregisteredEventResponse typedResponse = new ValidatorDeregisteredEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorDeregisteredEventResponse> validatorDeregisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORDEREGISTERED_EVENT));
        return validatorDeregisteredEventFlowable(filter);
    }

    public List<ValidatorEcdsaPublicKeyUpdatedEventResponse> getValidatorEcdsaPublicKeyUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORECDSAPUBLICKEYUPDATED_EVENT, transactionReceipt);
        ArrayList<ValidatorEcdsaPublicKeyUpdatedEventResponse> responses = new ArrayList<ValidatorEcdsaPublicKeyUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorEcdsaPublicKeyUpdatedEventResponse typedResponse = new ValidatorEcdsaPublicKeyUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ecdsaPublicKey = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorEcdsaPublicKeyUpdatedEventResponse> validatorEcdsaPublicKeyUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorEcdsaPublicKeyUpdatedEventResponse>() {
            @Override
            public ValidatorEcdsaPublicKeyUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORECDSAPUBLICKEYUPDATED_EVENT, log);
                ValidatorEcdsaPublicKeyUpdatedEventResponse typedResponse = new ValidatorEcdsaPublicKeyUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ecdsaPublicKey = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorEcdsaPublicKeyUpdatedEventResponse> validatorEcdsaPublicKeyUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORECDSAPUBLICKEYUPDATED_EVENT));
        return validatorEcdsaPublicKeyUpdatedEventFlowable(filter);
    }

    public List<ValidatorEpochPaymentDistributedEventResponse> getValidatorEpochPaymentDistributedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATOREPOCHPAYMENTDISTRIBUTED_EVENT, transactionReceipt);
        ArrayList<ValidatorEpochPaymentDistributedEventResponse> responses = new ArrayList<ValidatorEpochPaymentDistributedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorEpochPaymentDistributedEventResponse typedResponse = new ValidatorEpochPaymentDistributedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.validatorPayment = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.groupPayment = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorEpochPaymentDistributedEventResponse> validatorEpochPaymentDistributedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorEpochPaymentDistributedEventResponse>() {
            @Override
            public ValidatorEpochPaymentDistributedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATOREPOCHPAYMENTDISTRIBUTED_EVENT, log);
                ValidatorEpochPaymentDistributedEventResponse typedResponse = new ValidatorEpochPaymentDistributedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.group = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.validatorPayment = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.groupPayment = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorEpochPaymentDistributedEventResponse> validatorEpochPaymentDistributedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATOREPOCHPAYMENTDISTRIBUTED_EVENT));
        return validatorEpochPaymentDistributedEventFlowable(filter);
    }

    public List<ValidatorGroupCommissionUpdateQueuedEventResponse> getValidatorGroupCommissionUpdateQueuedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPCOMMISSIONUPDATEQUEUED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupCommissionUpdateQueuedEventResponse> responses = new ArrayList<ValidatorGroupCommissionUpdateQueuedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupCommissionUpdateQueuedEventResponse typedResponse = new ValidatorGroupCommissionUpdateQueuedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.activationBlock = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupCommissionUpdateQueuedEventResponse> validatorGroupCommissionUpdateQueuedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupCommissionUpdateQueuedEventResponse>() {
            @Override
            public ValidatorGroupCommissionUpdateQueuedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPCOMMISSIONUPDATEQUEUED_EVENT, log);
                ValidatorGroupCommissionUpdateQueuedEventResponse typedResponse = new ValidatorGroupCommissionUpdateQueuedEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.activationBlock = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupCommissionUpdateQueuedEventResponse> validatorGroupCommissionUpdateQueuedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPCOMMISSIONUPDATEQUEUED_EVENT));
        return validatorGroupCommissionUpdateQueuedEventFlowable(filter);
    }

    public List<ValidatorGroupCommissionUpdatedEventResponse> getValidatorGroupCommissionUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPCOMMISSIONUPDATED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupCommissionUpdatedEventResponse> responses = new ArrayList<ValidatorGroupCommissionUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupCommissionUpdatedEventResponse typedResponse = new ValidatorGroupCommissionUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupCommissionUpdatedEventResponse> validatorGroupCommissionUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupCommissionUpdatedEventResponse>() {
            @Override
            public ValidatorGroupCommissionUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPCOMMISSIONUPDATED_EVENT, log);
                ValidatorGroupCommissionUpdatedEventResponse typedResponse = new ValidatorGroupCommissionUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupCommissionUpdatedEventResponse> validatorGroupCommissionUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPCOMMISSIONUPDATED_EVENT));
        return validatorGroupCommissionUpdatedEventFlowable(filter);
    }

    public List<ValidatorGroupDeregisteredEventResponse> getValidatorGroupDeregisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPDEREGISTERED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupDeregisteredEventResponse> responses = new ArrayList<ValidatorGroupDeregisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupDeregisteredEventResponse typedResponse = new ValidatorGroupDeregisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupDeregisteredEventResponse> validatorGroupDeregisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupDeregisteredEventResponse>() {
            @Override
            public ValidatorGroupDeregisteredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPDEREGISTERED_EVENT, log);
                ValidatorGroupDeregisteredEventResponse typedResponse = new ValidatorGroupDeregisteredEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupDeregisteredEventResponse> validatorGroupDeregisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPDEREGISTERED_EVENT));
        return validatorGroupDeregisteredEventFlowable(filter);
    }

    public List<ValidatorGroupMemberAddedEventResponse> getValidatorGroupMemberAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPMEMBERADDED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupMemberAddedEventResponse> responses = new ArrayList<ValidatorGroupMemberAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupMemberAddedEventResponse typedResponse = new ValidatorGroupMemberAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupMemberAddedEventResponse> validatorGroupMemberAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupMemberAddedEventResponse>() {
            @Override
            public ValidatorGroupMemberAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPMEMBERADDED_EVENT, log);
                ValidatorGroupMemberAddedEventResponse typedResponse = new ValidatorGroupMemberAddedEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupMemberAddedEventResponse> validatorGroupMemberAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPMEMBERADDED_EVENT));
        return validatorGroupMemberAddedEventFlowable(filter);
    }

    public List<ValidatorGroupMemberRemovedEventResponse> getValidatorGroupMemberRemovedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPMEMBERREMOVED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupMemberRemovedEventResponse> responses = new ArrayList<ValidatorGroupMemberRemovedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupMemberRemovedEventResponse typedResponse = new ValidatorGroupMemberRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupMemberRemovedEventResponse> validatorGroupMemberRemovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupMemberRemovedEventResponse>() {
            @Override
            public ValidatorGroupMemberRemovedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPMEMBERREMOVED_EVENT, log);
                ValidatorGroupMemberRemovedEventResponse typedResponse = new ValidatorGroupMemberRemovedEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupMemberRemovedEventResponse> validatorGroupMemberRemovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPMEMBERREMOVED_EVENT));
        return validatorGroupMemberRemovedEventFlowable(filter);
    }

    public List<ValidatorGroupMemberReorderedEventResponse> getValidatorGroupMemberReorderedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPMEMBERREORDERED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupMemberReorderedEventResponse> responses = new ArrayList<ValidatorGroupMemberReorderedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupMemberReorderedEventResponse typedResponse = new ValidatorGroupMemberReorderedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupMemberReorderedEventResponse> validatorGroupMemberReorderedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupMemberReorderedEventResponse>() {
            @Override
            public ValidatorGroupMemberReorderedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPMEMBERREORDERED_EVENT, log);
                ValidatorGroupMemberReorderedEventResponse typedResponse = new ValidatorGroupMemberReorderedEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.validator = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupMemberReorderedEventResponse> validatorGroupMemberReorderedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPMEMBERREORDERED_EVENT));
        return validatorGroupMemberReorderedEventFlowable(filter);
    }

    public List<ValidatorGroupRegisteredEventResponse> getValidatorGroupRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORGROUPREGISTERED_EVENT, transactionReceipt);
        ArrayList<ValidatorGroupRegisteredEventResponse> responses = new ArrayList<ValidatorGroupRegisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorGroupRegisteredEventResponse typedResponse = new ValidatorGroupRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorGroupRegisteredEventResponse> validatorGroupRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorGroupRegisteredEventResponse>() {
            @Override
            public ValidatorGroupRegisteredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORGROUPREGISTERED_EVENT, log);
                ValidatorGroupRegisteredEventResponse typedResponse = new ValidatorGroupRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.group = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.commission = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorGroupRegisteredEventResponse> validatorGroupRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORGROUPREGISTERED_EVENT));
        return validatorGroupRegisteredEventFlowable(filter);
    }

    public List<ValidatorLockedGoldRequirementsSetEventResponse> getValidatorLockedGoldRequirementsSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORLOCKEDGOLDREQUIREMENTSSET_EVENT, transactionReceipt);
        ArrayList<ValidatorLockedGoldRequirementsSetEventResponse> responses = new ArrayList<ValidatorLockedGoldRequirementsSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorLockedGoldRequirementsSetEventResponse typedResponse = new ValidatorLockedGoldRequirementsSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorLockedGoldRequirementsSetEventResponse> validatorLockedGoldRequirementsSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorLockedGoldRequirementsSetEventResponse>() {
            @Override
            public ValidatorLockedGoldRequirementsSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORLOCKEDGOLDREQUIREMENTSSET_EVENT, log);
                ValidatorLockedGoldRequirementsSetEventResponse typedResponse = new ValidatorLockedGoldRequirementsSetEventResponse();
                typedResponse.log = log;
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorLockedGoldRequirementsSetEventResponse> validatorLockedGoldRequirementsSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORLOCKEDGOLDREQUIREMENTSSET_EVENT));
        return validatorLockedGoldRequirementsSetEventFlowable(filter);
    }

    public List<ValidatorRegisteredEventResponse> getValidatorRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORREGISTERED_EVENT, transactionReceipt);
        ArrayList<ValidatorRegisteredEventResponse> responses = new ArrayList<ValidatorRegisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorRegisteredEventResponse typedResponse = new ValidatorRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorRegisteredEventResponse> validatorRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorRegisteredEventResponse>() {
            @Override
            public ValidatorRegisteredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORREGISTERED_EVENT, log);
                ValidatorRegisteredEventResponse typedResponse = new ValidatorRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorRegisteredEventResponse> validatorRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORREGISTERED_EVENT));
        return validatorRegisteredEventFlowable(filter);
    }

    public List<ValidatorScoreParametersSetEventResponse> getValidatorScoreParametersSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORSCOREPARAMETERSSET_EVENT, transactionReceipt);
        ArrayList<ValidatorScoreParametersSetEventResponse> responses = new ArrayList<ValidatorScoreParametersSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorScoreParametersSetEventResponse typedResponse = new ValidatorScoreParametersSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.exponent = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.adjustmentSpeed = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorScoreParametersSetEventResponse> validatorScoreParametersSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorScoreParametersSetEventResponse>() {
            @Override
            public ValidatorScoreParametersSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORSCOREPARAMETERSSET_EVENT, log);
                ValidatorScoreParametersSetEventResponse typedResponse = new ValidatorScoreParametersSetEventResponse();
                typedResponse.log = log;
                typedResponse.exponent = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.adjustmentSpeed = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorScoreParametersSetEventResponse> validatorScoreParametersSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORSCOREPARAMETERSSET_EVENT));
        return validatorScoreParametersSetEventFlowable(filter);
    }

    public List<ValidatorScoreUpdatedEventResponse> getValidatorScoreUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATORSCOREUPDATED_EVENT, transactionReceipt);
        ArrayList<ValidatorScoreUpdatedEventResponse> responses = new ArrayList<ValidatorScoreUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ValidatorScoreUpdatedEventResponse typedResponse = new ValidatorScoreUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.score = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.epochScore = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValidatorScoreUpdatedEventResponse> validatorScoreUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ValidatorScoreUpdatedEventResponse>() {
            @Override
            public ValidatorScoreUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATORSCOREUPDATED_EVENT, log);
                ValidatorScoreUpdatedEventResponse typedResponse = new ValidatorScoreUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.validator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.score = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.epochScore = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValidatorScoreUpdatedEventResponse> validatorScoreUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATORSCOREUPDATED_EVENT));
        return validatorScoreUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKPROOFOFPOSSESSION, 
                Arrays.<Type>asList(new Address(sender),
                new DynamicBytes(blsKey),
                new DynamicBytes(blsPop)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> commissionUpdateDelay() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_COMMISSIONUPDATEDELAY, 
                Arrays.<Type>asList(), 
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

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> groupLockedGoldRequirements() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GROUPLOCKEDGOLDREQUIREMENTS, 
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

    public RemoteFunctionCall<BigInteger> maxGroupSize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXGROUPSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> membershipHistoryLength() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MEMBERSHIPHISTORYLENGTH, 
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

    public RemoteFunctionCall<BigInteger> slashingMultiplierResetPeriod() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SLASHINGMULTIPLIERRESETPERIOD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> validatorLockedGoldRequirements() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VALIDATORLOCKEDGOLDREQUIREMENTS, 
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger groupRequirementValue, BigInteger groupRequirementDuration, BigInteger validatorRequirementValue, BigInteger validatorRequirementDuration, BigInteger validatorScoreExponent, BigInteger validatorScoreAdjustmentSpeed, BigInteger _membershipHistoryLength, BigInteger _slashingMultiplierResetPeriod, BigInteger _maxGroupSize, BigInteger _commissionUpdateDelay) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(registryAddress),
                new Uint256(groupRequirementValue),
                new Uint256(groupRequirementDuration),
                new Uint256(validatorRequirementValue),
                new Uint256(validatorRequirementDuration),
                new Uint256(validatorScoreExponent),
                new Uint256(validatorScoreAdjustmentSpeed),
                new Uint256(_membershipHistoryLength),
                new Uint256(_slashingMultiplierResetPeriod),
                new Uint256(_maxGroupSize),
                new Uint256(_commissionUpdateDelay)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCommissionUpdateDelay(BigInteger delay) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCOMMISSIONUPDATEDELAY, 
                Arrays.<Type>asList(new Uint256(delay)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxGroupSize(BigInteger size) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMAXGROUPSIZE, 
                Arrays.<Type>asList(new Uint256(size)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMembershipHistoryLength(BigInteger length) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMEMBERSHIPHISTORYLENGTH, 
                Arrays.<Type>asList(new Uint256(length)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setValidatorScoreParameters(BigInteger exponent, BigInteger adjustmentSpeed) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVALIDATORSCOREPARAMETERS, 
                Arrays.<Type>asList(new Uint256(exponent),
                new Uint256(adjustmentSpeed)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getMaxGroupSize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMAXGROUPSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCommissionUpdateDelay() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOMMISSIONUPDATEDELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setGroupLockedGoldRequirements(BigInteger value, BigInteger duration) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETGROUPLOCKEDGOLDREQUIREMENTS, 
                Arrays.<Type>asList(new Uint256(value),
                new Uint256(duration)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setValidatorLockedGoldRequirements(BigInteger value, BigInteger duration) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVALIDATORLOCKEDGOLDREQUIREMENTS, 
                Arrays.<Type>asList(new Uint256(value),
                new Uint256(duration)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerValidator(byte[] ecdsaPublicKey, byte[] blsPublicKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERVALIDATOR, 
                Arrays.<Type>asList(new DynamicBytes(ecdsaPublicKey),
                new DynamicBytes(blsPublicKey),
                new DynamicBytes(blsPop)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getValidatorScoreParameters() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATORSCOREPARAMETERS, 
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

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger>> getMembershipHistory(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMEMBERSHIPHISTORY, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Address>) results.get(1).getValue()), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> calculateEpochScore(BigInteger uptime) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CALCULATEEPOCHSCORE, 
                Arrays.<Type>asList(new Uint256(uptime)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateGroupEpochScore(List<BigInteger> uptimes) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CALCULATEGROUPEPOCHSCORE, 
                Arrays.<Type>asList(new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(uptimes, Uint256.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateValidatorScoreFromSigner(String signer, BigInteger uptime) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEVALIDATORSCOREFROMSIGNER, 
                Arrays.<Type>asList(new Address(signer),
                new Uint256(uptime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeEpochPaymentsFromSigner(String signer, BigInteger maxPayment) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISTRIBUTEEPOCHPAYMENTSFROMSIGNER, 
                Arrays.<Type>asList(new Address(signer),
                new Uint256(maxPayment)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deregisterValidator(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEREGISTERVALIDATOR, 
                Arrays.<Type>asList(new Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> affiliate(String group) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_AFFILIATE, 
                Arrays.<Type>asList(new Address(group)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deaffiliate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEAFFILIATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateBlsPublicKey(byte[] blsPublicKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEBLSPUBLICKEY, 
                Arrays.<Type>asList(new DynamicBytes(blsPublicKey),
                new DynamicBytes(blsPop)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateEcdsaPublicKey(String account, String signer, byte[] ecdsaPublicKey) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEECDSAPUBLICKEY, 
                Arrays.<Type>asList(new Address(account),
                new Address(signer),
                new DynamicBytes(ecdsaPublicKey)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updatePublicKeys(String account, String signer, byte[] ecdsaPublicKey, byte[] blsPublicKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEPUBLICKEYS, 
                Arrays.<Type>asList(new Address(account),
                new Address(signer),
                new DynamicBytes(ecdsaPublicKey),
                new DynamicBytes(blsPublicKey),
                new DynamicBytes(blsPop)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerValidatorGroup(BigInteger commission) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERVALIDATORGROUP, 
                Arrays.<Type>asList(new Uint256(commission)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deregisterValidatorGroup(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEREGISTERVALIDATORGROUP, 
                Arrays.<Type>asList(new Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addMember(String validator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDMEMBER, 
                Arrays.<Type>asList(new Address(validator)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addFirstMember(String validator, String lesser, String greater) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDFIRSTMEMBER, 
                Arrays.<Type>asList(new Address(validator),
                new Address(lesser),
                new Address(greater)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeMember(String validator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEMEMBER, 
                Arrays.<Type>asList(new Address(validator)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reorderMember(String validator, String lesserMember, String greaterMember) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REORDERMEMBER, 
                Arrays.<Type>asList(new Address(validator),
                new Address(lesserMember),
                new Address(greaterMember)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNextCommissionUpdate(BigInteger commission) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETNEXTCOMMISSIONUPDATE, 
                Arrays.<Type>asList(new Uint256(commission)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateCommission() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATECOMMISSION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getAccountLockedGoldRequirement(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETACCOUNTLOCKEDGOLDREQUIREMENT, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> meetsAccountLockedGoldRequirements(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MEETSACCOUNTLOCKEDGOLDREQUIREMENTS, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> getValidatorBlsPublicKeyFromSigner(String signer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATORBLSPUBLICKEYFROMSIGNER, 
                Arrays.<Type>asList(new Address(signer)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<Tuple5<byte[], byte[], String, BigInteger, String>> getValidator(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATOR, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple5<byte[], byte[], String, BigInteger, String>>(function,
                new Callable<Tuple5<byte[], byte[], String, BigInteger, String>>() {
                    @Override
                    public Tuple5<byte[], byte[], String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<byte[], byte[], String, BigInteger, String>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger>> getValidatorGroup(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATORGROUP, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                convertToNative((List<Uint256>) results.get(4).getValue()), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getGroupNumMembers(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPNUMMEMBERS, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getTopGroupValidators(String account, BigInteger n) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOPGROUPVALIDATORS, 
                Arrays.<Type>asList(new Address(account),
                new Uint256(n)),
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

    public RemoteFunctionCall<List> getGroupsNumMembers(List<String> accounts) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPSNUMMEMBERS, 
                Arrays.<Type>asList(new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(accounts, Address.class))),
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

    public RemoteFunctionCall<BigInteger> getNumRegisteredValidators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMREGISTEREDVALIDATORS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getValidatorLockedGoldRequirements() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATORLOCKEDGOLDREQUIREMENTS, 
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

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getGroupLockedGoldRequirements() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGROUPLOCKEDGOLDREQUIREMENTS, 
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

    public RemoteFunctionCall<List> getRegisteredValidators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREGISTEREDVALIDATORS, 
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

    public RemoteFunctionCall<List> getRegisteredValidatorSigners() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREGISTEREDVALIDATORSIGNERS, 
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

    public RemoteFunctionCall<List> getRegisteredValidatorGroups() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREGISTEREDVALIDATORGROUPS, 
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

    public RemoteFunctionCall<Boolean> isValidatorGroup(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISVALIDATORGROUP, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isValidator(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISVALIDATOR, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> getMembershipInLastEpochFromSigner(String signer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMEMBERSHIPINLASTEPOCHFROMSIGNER, 
                Arrays.<Type>asList(new Address(signer)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getMembershipInLastEpoch(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMEMBERSHIPINLASTEPOCH, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> forceDeaffiliateIfValidator(String validatorAccount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FORCEDEAFFILIATEIFVALIDATOR, 
                Arrays.<Type>asList(new Address(validatorAccount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSlashingMultiplierResetPeriod(BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSLASHINGMULTIPLIERRESETPERIOD, 
                Arrays.<Type>asList(new Uint256(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> resetSlashingMultiplier() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RESETSLASHINGMULTIPLIER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> halveSlashingMultiplier(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_HALVESLASHINGMULTIPLIER, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getValidatorGroupSlashingMultiplier(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVALIDATORGROUPSLASHINGMULTIPLIER, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> groupMembershipInEpoch(String account, BigInteger epochNumber, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GROUPMEMBERSHIPINEPOCH, 
                Arrays.<Type>asList(new Address(account),
                new Uint256(epochNumber),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Validators load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Validators(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Validators load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Validators(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Validators load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Validators(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Validators load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Validators(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Validators> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Validators.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Validators> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Validators.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Validators> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Validators.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Validators> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Validators.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class CommissionUpdateDelaySetEventResponse extends BaseEventResponse {
        public BigInteger delay;
    }

    public static class GroupLockedGoldRequirementsSetEventResponse extends BaseEventResponse {
        public BigInteger value;

        public BigInteger duration;
    }

    public static class MaxGroupSizeSetEventResponse extends BaseEventResponse {
        public BigInteger size;
    }

    public static class MembershipHistoryLengthSetEventResponse extends BaseEventResponse {
        public BigInteger length;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }

    public static class ValidatorAffiliatedEventResponse extends BaseEventResponse {
        public String validator;

        public String group;
    }

    public static class ValidatorBlsPublicKeyUpdatedEventResponse extends BaseEventResponse {
        public String validator;

        public byte[] blsPublicKey;
    }

    public static class ValidatorDeaffiliatedEventResponse extends BaseEventResponse {
        public String validator;

        public String group;
    }

    public static class ValidatorDeregisteredEventResponse extends BaseEventResponse {
        public String validator;
    }

    public static class ValidatorEcdsaPublicKeyUpdatedEventResponse extends BaseEventResponse {
        public String validator;

        public byte[] ecdsaPublicKey;
    }

    public static class ValidatorEpochPaymentDistributedEventResponse extends BaseEventResponse {
        public String validator;

        public String group;

        public BigInteger validatorPayment;

        public BigInteger groupPayment;
    }

    public static class ValidatorGroupCommissionUpdateQueuedEventResponse extends BaseEventResponse {
        public String group;

        public BigInteger commission;

        public BigInteger activationBlock;
    }

    public static class ValidatorGroupCommissionUpdatedEventResponse extends BaseEventResponse {
        public String group;

        public BigInteger commission;
    }

    public static class ValidatorGroupDeregisteredEventResponse extends BaseEventResponse {
        public String group;
    }

    public static class ValidatorGroupMemberAddedEventResponse extends BaseEventResponse {
        public String group;

        public String validator;
    }

    public static class ValidatorGroupMemberRemovedEventResponse extends BaseEventResponse {
        public String group;

        public String validator;
    }

    public static class ValidatorGroupMemberReorderedEventResponse extends BaseEventResponse {
        public String group;

        public String validator;
    }

    public static class ValidatorGroupRegisteredEventResponse extends BaseEventResponse {
        public String group;

        public BigInteger commission;
    }

    public static class ValidatorLockedGoldRequirementsSetEventResponse extends BaseEventResponse {
        public BigInteger value;

        public BigInteger duration;
    }

    public static class ValidatorRegisteredEventResponse extends BaseEventResponse {
        public String validator;
    }

    public static class ValidatorScoreParametersSetEventResponse extends BaseEventResponse {
        public BigInteger exponent;

        public BigInteger adjustmentSpeed;
    }

    public static class ValidatorScoreUpdatedEventResponse extends BaseEventResponse {
        public String validator;

        public BigInteger score;

        public BigInteger epochScore;
    }
}
