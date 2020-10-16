package org.celo.contractkit.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
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
public class Attestations extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_ATTESTATIONEXPIRYBLOCKS = "attestationExpiryBlocks";

    public static final String FUNC_ATTESTATIONREQUESTFEES = "attestationRequestFees";

    public static final String FUNC_CHECKPROOFOFPOSSESSION = "checkProofOfPossession";

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

    public static final String FUNC_MAXATTESTATIONS = "maxAttestations";

    public static final String FUNC_MINQUORUMSIZE = "minQuorumSize";

    public static final String FUNC_MINQUORUMSIZEINCURRENTSET = "minQuorumSizeInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINCURRENTSET = "numberValidatorsInCurrentSet";

    public static final String FUNC_NUMBERVALIDATORSINSET = "numberValidatorsInSet";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PENDINGWITHDRAWALS = "pendingWithdrawals";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SELECTISSUERSWAITBLOCKS = "selectIssuersWaitBlocks";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_TRANSFERAPPROVALS = "transferApprovals";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMCURRENTSET = "validatorSignerAddressFromCurrentSet";

    public static final String FUNC_VALIDATORSIGNERADDRESSFROMSET = "validatorSignerAddressFromSet";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_REQUEST = "request";

    public static final String FUNC_SELECTISSUERS = "selectIssuers";

    public static final String FUNC_COMPLETE = "complete";

    public static final String FUNC_REVOKE = "revoke";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_GETUNSELECTEDREQUEST = "getUnselectedRequest";

    public static final String FUNC_GETATTESTATIONISSUERS = "getAttestationIssuers";

    public static final String FUNC_GETATTESTATIONSTATS = "getAttestationStats";

    public static final String FUNC_BATCHGETATTESTATIONSTATS = "batchGetAttestationStats";

    public static final String FUNC_GETATTESTATIONSTATE = "getAttestationState";

    public static final String FUNC_GETCOMPLETABLEATTESTATIONS = "getCompletableAttestations";

    public static final String FUNC_GETATTESTATIONREQUESTFEE = "getAttestationRequestFee";

    public static final String FUNC_SETATTESTATIONREQUESTFEE = "setAttestationRequestFee";

    public static final String FUNC_SETATTESTATIONEXPIRYBLOCKS = "setAttestationExpiryBlocks";

    public static final String FUNC_SETSELECTISSUERSWAITBLOCKS = "setSelectIssuersWaitBlocks";

    public static final String FUNC_SETMAXATTESTATIONS = "setMaxAttestations";

    public static final String FUNC_GETMAXATTESTATIONS = "getMaxAttestations";

    public static final String FUNC_VALIDATEATTESTATIONCODE = "validateAttestationCode";

    public static final String FUNC_LOOKUPACCOUNTSFORIDENTIFIER = "lookupAccountsForIdentifier";

    public static final String FUNC_REQUIRENATTESTATIONSREQUESTED = "requireNAttestationsRequested";

    public static final String FUNC_APPROVETRANSFER = "approveTransfer";

    public static final Event ATTESTATIONCOMPLETED_EVENT = new Event("AttestationCompleted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ATTESTATIONEXPIRYBLOCKSSET_EVENT = new Event("AttestationExpiryBlocksSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ATTESTATIONISSUERSELECTED_EVENT = new Event("AttestationIssuerSelected", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event ATTESTATIONREQUESTFEESET_EVENT = new Event("AttestationRequestFeeSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ATTESTATIONSREQUESTED_EVENT = new Event("AttestationsRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ATTESTATIONSTRANSFERRED_EVENT = new Event("AttestationsTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event MAXATTESTATIONSSET_EVENT = new Event("MaxAttestationsSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event SELECTISSUERSWAITBLOCKSSET_EVENT = new Event("SelectIssuersWaitBlocksSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFERAPPROVAL_EVENT = new Event("TransferApproval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event WITHDRAWAL_EVENT = new Event("Withdrawal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Attestations(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Attestations(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Attestations(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Attestations(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AttestationCompletedEventResponse> getAttestationCompletedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONCOMPLETED_EVENT, transactionReceipt);
        ArrayList<AttestationCompletedEventResponse> responses = new ArrayList<AttestationCompletedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationCompletedEventResponse typedResponse = new AttestationCompletedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.issuer = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationCompletedEventResponse> attestationCompletedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationCompletedEventResponse>() {
            @Override
            public AttestationCompletedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONCOMPLETED_EVENT, log);
                AttestationCompletedEventResponse typedResponse = new AttestationCompletedEventResponse();
                typedResponse.log = log;
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.issuer = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationCompletedEventResponse> attestationCompletedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONCOMPLETED_EVENT));
        return attestationCompletedEventFlowable(filter);
    }

    public List<AttestationExpiryBlocksSetEventResponse> getAttestationExpiryBlocksSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONEXPIRYBLOCKSSET_EVENT, transactionReceipt);
        ArrayList<AttestationExpiryBlocksSetEventResponse> responses = new ArrayList<AttestationExpiryBlocksSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationExpiryBlocksSetEventResponse typedResponse = new AttestationExpiryBlocksSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationExpiryBlocksSetEventResponse> attestationExpiryBlocksSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationExpiryBlocksSetEventResponse>() {
            @Override
            public AttestationExpiryBlocksSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONEXPIRYBLOCKSSET_EVENT, log);
                AttestationExpiryBlocksSetEventResponse typedResponse = new AttestationExpiryBlocksSetEventResponse();
                typedResponse.log = log;
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationExpiryBlocksSetEventResponse> attestationExpiryBlocksSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONEXPIRYBLOCKSSET_EVENT));
        return attestationExpiryBlocksSetEventFlowable(filter);
    }

    public List<AttestationIssuerSelectedEventResponse> getAttestationIssuerSelectedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONISSUERSELECTED_EVENT, transactionReceipt);
        ArrayList<AttestationIssuerSelectedEventResponse> responses = new ArrayList<AttestationIssuerSelectedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationIssuerSelectedEventResponse typedResponse = new AttestationIssuerSelectedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.issuer = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.attestationRequestFeeToken = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationIssuerSelectedEventResponse> attestationIssuerSelectedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationIssuerSelectedEventResponse>() {
            @Override
            public AttestationIssuerSelectedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONISSUERSELECTED_EVENT, log);
                AttestationIssuerSelectedEventResponse typedResponse = new AttestationIssuerSelectedEventResponse();
                typedResponse.log = log;
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.issuer = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.attestationRequestFeeToken = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationIssuerSelectedEventResponse> attestationIssuerSelectedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONISSUERSELECTED_EVENT));
        return attestationIssuerSelectedEventFlowable(filter);
    }

    public List<AttestationRequestFeeSetEventResponse> getAttestationRequestFeeSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONREQUESTFEESET_EVENT, transactionReceipt);
        ArrayList<AttestationRequestFeeSetEventResponse> responses = new ArrayList<AttestationRequestFeeSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationRequestFeeSetEventResponse typedResponse = new AttestationRequestFeeSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationRequestFeeSetEventResponse> attestationRequestFeeSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationRequestFeeSetEventResponse>() {
            @Override
            public AttestationRequestFeeSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONREQUESTFEESET_EVENT, log);
                AttestationRequestFeeSetEventResponse typedResponse = new AttestationRequestFeeSetEventResponse();
                typedResponse.log = log;
                typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationRequestFeeSetEventResponse> attestationRequestFeeSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONREQUESTFEESET_EVENT));
        return attestationRequestFeeSetEventFlowable(filter);
    }

    public List<AttestationsRequestedEventResponse> getAttestationsRequestedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONSREQUESTED_EVENT, transactionReceipt);
        ArrayList<AttestationsRequestedEventResponse> responses = new ArrayList<AttestationsRequestedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationsRequestedEventResponse typedResponse = new AttestationsRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.attestationsRequested = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.attestationRequestFeeToken = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationsRequestedEventResponse> attestationsRequestedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationsRequestedEventResponse>() {
            @Override
            public AttestationsRequestedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONSREQUESTED_EVENT, log);
                AttestationsRequestedEventResponse typedResponse = new AttestationsRequestedEventResponse();
                typedResponse.log = log;
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.attestationsRequested = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.attestationRequestFeeToken = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationsRequestedEventResponse> attestationsRequestedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONSREQUESTED_EVENT));
        return attestationsRequestedEventFlowable(filter);
    }

    public List<AttestationsTransferredEventResponse> getAttestationsTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ATTESTATIONSTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<AttestationsTransferredEventResponse> responses = new ArrayList<AttestationsTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttestationsTransferredEventResponse typedResponse = new AttestationsTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAccount = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.toAccount = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AttestationsTransferredEventResponse> attestationsTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AttestationsTransferredEventResponse>() {
            @Override
            public AttestationsTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ATTESTATIONSTRANSFERRED_EVENT, log);
                AttestationsTransferredEventResponse typedResponse = new AttestationsTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.fromAccount = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.toAccount = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AttestationsTransferredEventResponse> attestationsTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTESTATIONSTRANSFERRED_EVENT));
        return attestationsTransferredEventFlowable(filter);
    }

    public List<MaxAttestationsSetEventResponse> getMaxAttestationsSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MAXATTESTATIONSSET_EVENT, transactionReceipt);
        ArrayList<MaxAttestationsSetEventResponse> responses = new ArrayList<MaxAttestationsSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MaxAttestationsSetEventResponse typedResponse = new MaxAttestationsSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MaxAttestationsSetEventResponse> maxAttestationsSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MaxAttestationsSetEventResponse>() {
            @Override
            public MaxAttestationsSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MAXATTESTATIONSSET_EVENT, log);
                MaxAttestationsSetEventResponse typedResponse = new MaxAttestationsSetEventResponse();
                typedResponse.log = log;
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MaxAttestationsSetEventResponse> maxAttestationsSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MAXATTESTATIONSSET_EVENT));
        return maxAttestationsSetEventFlowable(filter);
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

    public List<SelectIssuersWaitBlocksSetEventResponse> getSelectIssuersWaitBlocksSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SELECTISSUERSWAITBLOCKSSET_EVENT, transactionReceipt);
        ArrayList<SelectIssuersWaitBlocksSetEventResponse> responses = new ArrayList<SelectIssuersWaitBlocksSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SelectIssuersWaitBlocksSetEventResponse typedResponse = new SelectIssuersWaitBlocksSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SelectIssuersWaitBlocksSetEventResponse> selectIssuersWaitBlocksSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SelectIssuersWaitBlocksSetEventResponse>() {
            @Override
            public SelectIssuersWaitBlocksSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SELECTISSUERSWAITBLOCKSSET_EVENT, log);
                SelectIssuersWaitBlocksSetEventResponse typedResponse = new SelectIssuersWaitBlocksSetEventResponse();
                typedResponse.log = log;
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SelectIssuersWaitBlocksSetEventResponse> selectIssuersWaitBlocksSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SELECTISSUERSWAITBLOCKSSET_EVENT));
        return selectIssuersWaitBlocksSetEventFlowable(filter);
    }

    public List<TransferApprovalEventResponse> getTransferApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERAPPROVAL_EVENT, transactionReceipt);
        ArrayList<TransferApprovalEventResponse> responses = new ArrayList<TransferApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferApprovalEventResponse typedResponse = new TransferApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.approver = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.indentifier = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferApprovalEventResponse> transferApprovalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferApprovalEventResponse>() {
            @Override
            public TransferApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERAPPROVAL_EVENT, log);
                TransferApprovalEventResponse typedResponse = new TransferApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.approver = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.indentifier = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferApprovalEventResponse> transferApprovalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERAPPROVAL_EVENT));
        return transferApprovalEventFlowable(filter);
    }

    public List<WithdrawalEventResponse> getWithdrawalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWAL_EVENT, transactionReceipt);
        ArrayList<WithdrawalEventResponse> responses = new ArrayList<WithdrawalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawalEventResponse typedResponse = new WithdrawalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawalEventResponse> withdrawalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawalEventResponse>() {
            @Override
            public WithdrawalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWAL_EVENT, log);
                WithdrawalEventResponse typedResponse = new WithdrawalEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.token = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawalEventResponse> withdrawalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWAL_EVENT));
        return withdrawalEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> attestationExpiryBlocks() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ATTESTATIONEXPIRYBLOCKS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> attestationRequestFees(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ATTESTATIONREQUESTFEES, 
                Arrays.<Type>asList(new Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKPROOFOFPOSSESSION, 
                Arrays.<Type>asList(new Address(sender), 
                new DynamicBytes(blsKey), 
                new DynamicBytes(blsPop)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteFunctionCall<BigInteger> maxAttestations() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXATTESTATIONS, 
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

    public RemoteFunctionCall<BigInteger> pendingWithdrawals(String param0, String param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PENDINGWITHDRAWALS, 
                Arrays.<Type>asList(new Address(param0), 
                new Address(param1)), 
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

    public RemoteFunctionCall<BigInteger> selectIssuersWaitBlocks() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SELECTISSUERSWAITBLOCKS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistry(String registryAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETREGISTRY, 
                Arrays.<Type>asList(new Address(registryAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> transferApprovals(String param0, byte[] param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRANSFERAPPROVALS, 
                Arrays.<Type>asList(new Address(param0), 
                new Bytes32(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger _attestationExpiryBlocks, BigInteger _selectIssuersWaitBlocks, BigInteger _maxAttestations, List<String> attestationRequestFeeTokens, List<BigInteger> attestationRequestFeeValues) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(registryAddress), 
                new Uint256(_attestationExpiryBlocks), 
                new Uint256(_selectIssuersWaitBlocks), 
                new Uint256(_maxAttestations), 
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(attestationRequestFeeTokens, Address.class)), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(attestationRequestFeeValues, Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> request(byte[] identifier, BigInteger attestationsRequested, String attestationRequestFeeToken) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REQUEST, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Uint256(attestationsRequested), 
                new Address(attestationRequestFeeToken)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> selectIssuers(byte[] identifier) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELECTISSUERS, 
                Arrays.<Type>asList(new Bytes32(identifier)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> complete(byte[] identifier, BigInteger v, byte[] r, byte[] s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COMPLETE, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Uint8(v), 
                new Bytes32(r), 
                new Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revoke(byte[] identifier, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String token) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new Address(token)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>> getUnselectedRequest(byte[] identifier, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUNSELECTEDREQUEST, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<List> getAttestationIssuers(byte[] identifier, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETATTESTATIONISSUERS, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account)), 
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

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getAttestationStats(byte[] identifier, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETATTESTATIONSTATS, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}));
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

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>>> batchGetAttestationStats(List<byte[]> identifiersToLookup) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BATCHGETATTESTATIONSTATS, 
                Arrays.<Type>asList(new DynamicArray<Bytes32>(
                        Bytes32.class,
                        org.web3j.abi.Utils.typeMap(identifiersToLookup, Bytes32.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint64>>() {}, new TypeReference<DynamicArray<Uint64>>() {}));
        return new RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<BigInteger>, List<String>, List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Address>) results.get(1).getValue()), 
                                convertToNative((List<Uint64>) results.get(2).getValue()), 
                                convertToNative((List<Uint64>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>> getAttestationState(byte[] identifier, String account, String issuer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETATTESTATIONSTATE, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account), 
                new Address(issuer)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint32>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, byte[]>> getCompletableAttestations(byte[] identifier, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOMPLETABLEATTESTATIONS, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint32>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, byte[]>>(function,
                new Callable<Tuple4<List<BigInteger>, List<String>, List<BigInteger>, byte[]>>() {
                    @Override
                    public Tuple4<List<BigInteger>, List<String>, List<BigInteger>, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<BigInteger>, List<String>, List<BigInteger>, byte[]>(
                                convertToNative((List<Uint32>) results.get(0).getValue()), 
                                convertToNative((List<Address>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                (byte[]) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getAttestationRequestFee(String token) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETATTESTATIONREQUESTFEE, 
                Arrays.<Type>asList(new Address(token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setAttestationRequestFee(String token, BigInteger fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETATTESTATIONREQUESTFEE, 
                Arrays.<Type>asList(new Address(token), 
                new Uint256(fee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAttestationExpiryBlocks(BigInteger _attestationExpiryBlocks) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETATTESTATIONEXPIRYBLOCKS, 
                Arrays.<Type>asList(new Uint256(_attestationExpiryBlocks)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSelectIssuersWaitBlocks(BigInteger _selectIssuersWaitBlocks) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSELECTISSUERSWAITBLOCKS, 
                Arrays.<Type>asList(new Uint256(_selectIssuersWaitBlocks)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxAttestations(BigInteger _maxAttestations) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMAXATTESTATIONS, 
                Arrays.<Type>asList(new Uint256(_maxAttestations)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getMaxAttestations() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMAXATTESTATIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> validateAttestationCode(byte[] identifier, String account, BigInteger v, byte[] r, byte[] s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VALIDATEATTESTATIONCODE, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Address(account), 
                new Uint8(v), 
                new Bytes32(r), 
                new Bytes32(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> lookupAccountsForIdentifier(byte[] identifier) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LOOKUPACCOUNTSFORIDENTIFIER, 
                Arrays.<Type>asList(new Bytes32(identifier)), 
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

    public RemoteFunctionCall<TransactionReceipt> approveTransfer(byte[] identifier, BigInteger index, String from, String to, Boolean status) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVETRANSFER, 
                Arrays.<Type>asList(new Bytes32(identifier), 
                new Uint256(index), 
                new Address(from), 
                new Address(to), 
                new Bool(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Attestations load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Attestations(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Attestations load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Attestations(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Attestations load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Attestations(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Attestations load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Attestations(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Attestations> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Attestations.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Attestations> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Attestations.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Attestations> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Attestations.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Attestations> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Attestations.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class AttestationCompletedEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String account;

        public String issuer;
    }

    public static class AttestationExpiryBlocksSetEventResponse extends BaseEventResponse {
        public BigInteger value;
    }

    public static class AttestationIssuerSelectedEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String account;

        public String issuer;

        public String attestationRequestFeeToken;
    }

    public static class AttestationRequestFeeSetEventResponse extends BaseEventResponse {
        public String token;

        public BigInteger value;
    }

    public static class AttestationsRequestedEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String account;

        public BigInteger attestationsRequested;

        public String attestationRequestFeeToken;
    }

    public static class AttestationsTransferredEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String fromAccount;

        public String toAccount;
    }

    public static class MaxAttestationsSetEventResponse extends BaseEventResponse {
        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }

    public static class SelectIssuersWaitBlocksSetEventResponse extends BaseEventResponse {
        public BigInteger value;
    }

    public static class TransferApprovalEventResponse extends BaseEventResponse {
        public String approver;

        public byte[] indentifier;

        public String from;

        public String to;

        public Boolean approved;
    }

    public static class WithdrawalEventResponse extends BaseEventResponse {
        public String account;

        public String token;

        public BigInteger amount;
    }
}
