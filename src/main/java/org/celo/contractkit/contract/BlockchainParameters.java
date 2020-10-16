package org.celo.contractkit.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
import org.web3j.tuples.generated.Tuple3;
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
public class BlockchainParameters extends Contract {
    public static final String BINARY = "0x608060405260006100146100b760201b60201c565b9050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3506100bf565b600033905090565b610a42806100ce6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c80638f32d59b116100715780638f32d59b14610197578063a69257f3146101b9578063bb3ff745146101e7578063cb0ec62814610229578063dcbab60814610257578063f2fde38b146102ad576100b4565b8063158ef93e146100b957806325eb315d146100db578063715018a6146101075780637877a79714610111578063808474f11461012f5780638da5cb5b1461014d575b600080fd5b6100c16102f1565b604051808215151515815260200191505060405180910390f35b6100e3610304565b60405180848152602001838152602001828152602001935050505060405180910390f35b61010f610325565b005b61011961045e565b6040518082815260200191505060405180910390f35b610137610464565b6040518082815260200191505060405180910390f35b61015561046a565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61019f610493565b604051808215151515815260200191505060405180910390f35b6101e5600480360360208110156101cf57600080fd5b81019080803590602001909291905050506104f1565b005b610227600480360360608110156101fd57600080fd5b810190808035906020019092919080359060200190929190803590602001909291905050506105ac565b005b6102556004803603602081101561023f57600080fd5b810190808035906020019092919050505061068f565b005b6102ab600480360360a081101561026d57600080fd5b81019080803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919050505061074a565b005b6102ef600480360360208110156102c357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610815565b005b600060149054906101000a900460ff1681565b60008060006001600001546001800154600160020154925092509250909192565b61032d610493565b61039f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a360008060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b60045481565b60055481565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166104d561089b565b73ffffffffffffffffffffffffffffffffffffffff1614905090565b6104f9610493565b61056b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b806004819055507f55311ae9c14427b0863f38ed97a2a5944c50d824bbf692836246512e6822c3cf816040518082815260200191505060405180910390a150565b6105b4610493565b610626576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b82600160000181905550816001800181905550806001600201819055507f809db05bd174a70ede53d18fc046c5ceb86ebffbb7746a0c8605772c97ef0d5283838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b610697610493565b610709576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b806005819055507fba9c6f28c7d9990745a5b5282dbee04706c28cae24a44736c3ba99b57c021f3e816040518082815260200191505060405180910390a150565b600060149054906101000a900460ff16156107cd576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601c8152602001807f636f6e747261637420616c726561647920696e697469616c697a65640000000081525060200191505060405180910390fd5b6001600060146101000a81548160ff0219169083151502179055506107f1336108a3565b6107fc8585856105ac565b610805816104f1565b61080e8261068f565b5050505050565b61081d610493565b61088f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b610898816108a3565b50565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610929576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001806109e86026913960400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505056fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f2061646472657373a265627a7a7231582025e9449e1dfd279a11dc23c060dc4e81fd0e557fe7b1bca986df3d079359f4c964736f6c634300050d0032";

    public static final String FUNC_BLOCKGASLIMIT = "blockGasLimit";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_INTRINSICGASFORALTERNATIVEFEECURRENCY = "intrinsicGasForAlternativeFeeCurrency";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETMINIMUMCLIENTVERSION = "setMinimumClientVersion";

    public static final String FUNC_SETBLOCKGASLIMIT = "setBlockGasLimit";

    public static final String FUNC_SETINTRINSICGASFORALTERNATIVEFEECURRENCY = "setIntrinsicGasForAlternativeFeeCurrency";

    public static final String FUNC_GETMINIMUMCLIENTVERSION = "getMinimumClientVersion";

    public static final Event BLOCKGASLIMITSET_EVENT = new Event("BlockGasLimitSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event INTRINSICGASFORALTERNATIVEFEECURRENCYSET_EVENT = new Event("IntrinsicGasForAlternativeFeeCurrencySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event MINIMUMCLIENTVERSIONSET_EVENT = new Event("MinimumClientVersionSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected BlockchainParameters(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BlockchainParameters(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BlockchainParameters(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BlockchainParameters(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<BlockGasLimitSetEventResponse> getBlockGasLimitSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BLOCKGASLIMITSET_EVENT, transactionReceipt);
        ArrayList<BlockGasLimitSetEventResponse> responses = new ArrayList<BlockGasLimitSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BlockGasLimitSetEventResponse typedResponse = new BlockGasLimitSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.limit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BlockGasLimitSetEventResponse> blockGasLimitSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BlockGasLimitSetEventResponse>() {
            @Override
            public BlockGasLimitSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BLOCKGASLIMITSET_EVENT, log);
                BlockGasLimitSetEventResponse typedResponse = new BlockGasLimitSetEventResponse();
                typedResponse.log = log;
                typedResponse.limit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BlockGasLimitSetEventResponse> blockGasLimitSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BLOCKGASLIMITSET_EVENT));
        return blockGasLimitSetEventFlowable(filter);
    }

    public List<IntrinsicGasForAlternativeFeeCurrencySetEventResponse> getIntrinsicGasForAlternativeFeeCurrencySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(INTRINSICGASFORALTERNATIVEFEECURRENCYSET_EVENT, transactionReceipt);
        ArrayList<IntrinsicGasForAlternativeFeeCurrencySetEventResponse> responses = new ArrayList<IntrinsicGasForAlternativeFeeCurrencySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            IntrinsicGasForAlternativeFeeCurrencySetEventResponse typedResponse = new IntrinsicGasForAlternativeFeeCurrencySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.gas = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IntrinsicGasForAlternativeFeeCurrencySetEventResponse> intrinsicGasForAlternativeFeeCurrencySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IntrinsicGasForAlternativeFeeCurrencySetEventResponse>() {
            @Override
            public IntrinsicGasForAlternativeFeeCurrencySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(INTRINSICGASFORALTERNATIVEFEECURRENCYSET_EVENT, log);
                IntrinsicGasForAlternativeFeeCurrencySetEventResponse typedResponse = new IntrinsicGasForAlternativeFeeCurrencySetEventResponse();
                typedResponse.log = log;
                typedResponse.gas = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IntrinsicGasForAlternativeFeeCurrencySetEventResponse> intrinsicGasForAlternativeFeeCurrencySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INTRINSICGASFORALTERNATIVEFEECURRENCYSET_EVENT));
        return intrinsicGasForAlternativeFeeCurrencySetEventFlowable(filter);
    }

    public List<MinimumClientVersionSetEventResponse> getMinimumClientVersionSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MINIMUMCLIENTVERSIONSET_EVENT, transactionReceipt);
        ArrayList<MinimumClientVersionSetEventResponse> responses = new ArrayList<MinimumClientVersionSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MinimumClientVersionSetEventResponse typedResponse = new MinimumClientVersionSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.major = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.minor = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.patch = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MinimumClientVersionSetEventResponse> minimumClientVersionSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MinimumClientVersionSetEventResponse>() {
            @Override
            public MinimumClientVersionSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MINIMUMCLIENTVERSIONSET_EVENT, log);
                MinimumClientVersionSetEventResponse typedResponse = new MinimumClientVersionSetEventResponse();
                typedResponse.log = log;
                typedResponse.major = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.minor = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.patch = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MinimumClientVersionSetEventResponse> minimumClientVersionSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINIMUMCLIENTVERSIONSET_EVENT));
        return minimumClientVersionSetEventFlowable(filter);
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

    public RemoteFunctionCall<BigInteger> blockGasLimit() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BLOCKGASLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INITIALIZED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> intrinsicGasForAlternativeFeeCurrency() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTRINSICGASFORALTERNATIVEFEECURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
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

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(BigInteger major, BigInteger minor, BigInteger patch, BigInteger _gasForNonGoldCurrencies, BigInteger gasLimit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Uint256(major), 
                new Uint256(minor), 
                new Uint256(patch), 
                new Uint256(_gasForNonGoldCurrencies), 
                new Uint256(gasLimit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMinimumClientVersion(BigInteger major, BigInteger minor, BigInteger patch) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMINIMUMCLIENTVERSION, 
                Arrays.<Type>asList(new Uint256(major), 
                new Uint256(minor), 
                new Uint256(patch)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBlockGasLimit(BigInteger gasLimit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBLOCKGASLIMIT, 
                Arrays.<Type>asList(new Uint256(gasLimit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIntrinsicGasForAlternativeFeeCurrency(BigInteger gas) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETINTRINSICGASFORALTERNATIVEFEECURRENCY, 
                Arrays.<Type>asList(new Uint256(gas)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getMinimumClientVersion() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMINIMUMCLIENTVERSION, 
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

    @Deprecated
    public static BlockchainParameters load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainParameters(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BlockchainParameters load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainParameters(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BlockchainParameters load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BlockchainParameters(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BlockchainParameters load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BlockchainParameters(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BlockchainParameters> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BlockchainParameters.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BlockchainParameters> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BlockchainParameters.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BlockchainParameters> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BlockchainParameters.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BlockchainParameters> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BlockchainParameters.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class BlockGasLimitSetEventResponse extends BaseEventResponse {
        public BigInteger limit;
    }

    public static class IntrinsicGasForAlternativeFeeCurrencySetEventResponse extends BaseEventResponse {
        public BigInteger gas;
    }

    public static class MinimumClientVersionSetEventResponse extends BaseEventResponse {
        public BigInteger major;

        public BigInteger minor;

        public BigInteger patch;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
