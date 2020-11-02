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
public class GasPriceMinimum extends Contract {
    public static final String BINARY = "0x608060405260006100146100b760201b60201c565b9050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3506100bf565b600033905090565b612126806100ce6000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c80638f32d59b116100ad578063b830f4a411610071578063b830f4a4146103ef578063c12398b41461041d578063ceff0bd614610469578063ef712c5b14610487578063f2fde38b146104d357610121565b80638f32d59b146102e557806393ca6fc414610307578063a54b7fc014610335578063a68f548e1461038d578063a91ee0dc146103ab57610121565b80634ec81af1116100f45780634ec81af1146101b257806354255be014610214578063715018a6146102475780637b103999146102515780638da5cb5b1461029b57610121565b8063158ef93e1461012657806330f726b91461014857806336945c2d146101765780634a3d5fe214610194575b600080fd5b61012e610517565b604051808215151515815260200191505060405180910390f35b6101746004803603602081101561015e57600080fd5b810190808035906020019092919050505061052a565b005b61017e61067b565b6040518082815260200191505060405180910390f35b61019c610681565b6040518082815260200191505060405180910390f35b610212600480360360808110156101c857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001909291908035906020019092919050505061068d565b005b61021c610765565b6040518085815260200184815260200183815260200182815260200194505050505060405180910390f35b61024f61078c565b005b6102596108c5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6102a36108eb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6102ed610914565b604051808215151515815260200191505060405180910390f35b6103336004803603602081101561031d57600080fd5b8101908080359060200190929190505050610972565b005b6103776004803603602081101561034b57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ac3565b6040518082815260200191505060405180910390f35b610395610e22565b6040518082815260200191505060405180910390f35b6103ed600480360360208110156103c157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e2e565b005b61041b6004803603602081101561040557600080fd5b8101908080359060200190929190505050610fd2565b005b6104536004803603604081101561043357600080fd5b8101908080359060200190929190803590602001909291905050506110e6565b6040518082815260200191505060405180910390f35b6104716111dd565b6040518082815260200191505060405180910390f35b6104bd6004803603604081101561049d57600080fd5b8101908080359060200190929190803590602001909291905050506111e3565b6040518082815260200191505060405180910390f35b610515600480360360208110156104e957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611389565b005b600060149054906101000a900460ff1681565b610532610914565b6105a4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b6105ad8161140f565b6005600082015181600001559050506105ec6105c761142d565b600560405180602001604052908160008201548152505061145390919063ffffffff16565b610641576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526027815260200180611ff86027913960400191505060405180910390fd5b7fd2e71cd7012df1df07d4908ff75ae4b2bfbb6c49d39144404661f1fd47253283816040518082815260200191505060405180910390a150565b60025481565b60048060000154905081565b600060149054906101000a900460ff1615610710576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601c8152602001807f636f6e747261637420616c726561647920696e697469616c697a65640000000081525060200191505060405180910390fd5b6001600060146101000a81548160ff02191690831515021790555061073433611468565b61073d84610e2e565b8260028190555061074d83610fd2565b61075682610972565b61075f8161052a565b50505050565b60008060008060018060016000839350829250819150809050935093509350935090919293565b610794610914565b610806576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a360008060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166109566115ac565b73ffffffffffffffffffffffffffffffffffffffff1614905090565b61097a610914565b6109ec576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b6109f58161140f565b600460008201518160000155905050610a34610a0f61142d565b600460405180602001604052908160008201548152505061145390919063ffffffff16565b610a89576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001806120cd6025913960400191505060405180910390fd5b7f2a109bad06121312708ed2a3e9b3556ea85ef8eadd4d10d8181f50d114eb4fab816040518082815260200191505060405180910390a150565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161480610c1d5750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dcf0aaed60405160200180807f476f6c64546f6b656e00000000000000000000000000000000000000000000008152506009019050604051602081830303815290604052805190602001206040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610bb357600080fd5b505afa158015610bc7573d6000803e3d6000fd5b505050506040513d6020811015610bdd57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16145b15610c2c576002549050610e1d565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dcf0aaed60405160200180807f536f727465644f7261636c657300000000000000000000000000000000000000815250600d019050604051602081830303815290604052805190602001206040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610ce757600080fd5b505afa158015610cfb573d6000803e3d6000fd5b505050506040513d6020811015610d1157600080fd5b810190808051906020019092919050505090506000808273ffffffffffffffffffffffffffffffffffffffff1663ef90e1b0866040518263ffffffff1660e01b8152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050604080518083038186803b158015610da357600080fd5b505afa158015610db7573d6000803e3d6000fd5b505050506040513d6040811015610dcd57600080fd5b8101908080519060200190929190805190602001909291905050508092508193505050610e1781610e09846002546115b490919063ffffffff16565b61163a90919063ffffffff16565b93505050505b919050565b60058060000154905081565b610e36610914565b610ea8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610f4b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f43616e6e6f7420726567697374657220746865206e756c6c206164647265737381525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff167f27fe5f0c1c3b1ed427cc63d0f05759ffdecf9aec9e18d31ef366fc8a6cb5dc3b60405160405180910390a250565b610fda610914565b61104c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600081116110a5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603181526020018061207b6031913960400191505060405180910390fd5b806003819055507f5548a13ccc1d9e4e2860461edda5ad49ba8a4fda485f67d954f9d7da8d2aff27816040518082815260200191505060405180910390a150565b60008073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614611189576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f4f6e6c7920564d2063616e2063616c6c0000000000000000000000000000000081525060200191505060405180910390fd5b61119383836111e3565b6002819055507f6e53b2f8b69496c2a175588ad1326dbabe2f66df4d82f817aeca52e3474807fb6002546040518082815260200191505060405180910390a1600254905092915050565b60035481565b60006111ed611fe4565b6111f78484611684565b905060006112246004604051806020016040529081600082015481525050836116c690919063ffffffff16565b905061122e611fe4565b816112615761125c8360046040518060200160405290816000820154815250506116db90919063ffffffff16565b61128b565b61128a6004604051806020016040529081600082015481525050846116db90919063ffffffff16565b5b9050611295611fe4565b826112e1576112dc6112c683600560405180602001604052908160008201548152505061178290919063ffffffff16565b6112ce61142d565b6116db90919063ffffffff16565b611324565b61132361130d83600560405180602001604052908160008201548152505061178290919063ffffffff16565b61131561142d565b611be190919063ffffffff16565b5b9050600061136661136161133661142d565b611353611344600254611c8a565b8661178290919063ffffffff16565b611be190919063ffffffff16565b611d14565b905060035481101561137a5760035461137c565b805b9550505050505092915050565b611391610914565b611403576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b61140c81611468565b50565b611417611fe4565b6040518060200160405280838152509050919050565b611435611fe4565b604051806020016040528069d3c21bcecceda1000000815250905090565b60008160000151836000015110905092915050565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156114ee576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602681526020018061201f6026913960400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600033905090565b6000808314156115c75760009050611634565b60008284029050828482816115d857fe5b041461162f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260218152602001806120ac6021913960400191505060405180910390fd5b809150505b92915050565b600061167c83836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250611d35565b905092915050565b61168c611fe4565b611694611fe4565b61169d84611c8a565b90506116a7611fe4565b6116b084611c8a565b90506116bc8282611dfb565b9250505092915050565b60008160000151836000015111905092915050565b6116e3611fe4565b816000015183600001511015611761576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f737562737472616374696f6e20756e646572666c6f772064657465637465640081525060200191505060405180910390fd5b60405180602001604052808360000151856000015103815250905092915050565b61178a611fe4565b6000836000015114806117a1575060008260000151145b156117bd57604051806020016040528060008152509050611bdb565b69d3c21bcecceda1000000826000015114156117db57829050611bdb565b69d3c21bcecceda1000000836000015114156117f957819050611bdb565b600069d3c21bcecceda100000061180f85611f44565b600001518161181a57fe5b049050600061182885611f7b565b600001519050600069d3c21bcecceda100000061184486611f44565b600001518161184f57fe5b049050600061185d86611f7b565b60000151905060008285029050600085146118f1578285828161187c57fe5b04146118f0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f6f766572666c6f7720783179312064657465637465640000000000000000000081525060200191505060405180910390fd5b5b600069d3c21bcecceda100000082029050600082146119935769d3c21bcecceda100000082828161191e57fe5b0414611992576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f6f766572666c6f772078317931202a206669786564312064657465637465640081525060200191505060405180910390fd5b5b8091506000848602905060008614611a2457848682816119af57fe5b0414611a23576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f6f766572666c6f7720783279312064657465637465640000000000000000000081525060200191505060405180910390fd5b5b6000848802905060008814611ab25784888281611a3d57fe5b0414611ab1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f6f766572666c6f7720783179322064657465637465640000000000000000000081525060200191505060405180910390fd5b5b611aba611fb8565b8781611ac257fe5b049650611acd611fb8565b8581611ad557fe5b0494506000858802905060008814611b665785888281611af157fe5b0414611b65576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f6f766572666c6f7720783279322064657465637465640000000000000000000081525060200191505060405180910390fd5b5b611b6e611fe4565b6040518060200160405280878152509050611b9781604051806020016040528087815250611be1565b9050611bb181604051806020016040528086815250611be1565b9050611bcb81604051806020016040528085815250611be1565b9050809a50505050505050505050505b92915050565b611be9611fe4565b6000826000015184600001510190508360000151811015611c72576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f616464206f766572666c6f77206465746563746564000000000000000000000081525060200191505060405180910390fd5b60405180602001604052808281525091505092915050565b611c92611fe4565b611c9a611fc5565b821115611cf2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260368152602001806120456036913960400191505060405180910390fd5b604051806020016040528069d3c21bcecceda100000084028152509050919050565b600069d3c21bcecceda1000000826000015181611d2d57fe5b049050919050565b60008083118290611de1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b83811015611da6578082015181840152602081019050611d8b565b50505050905090810190601f168015611dd35780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b506000838581611ded57fe5b049050809150509392505050565b611e03611fe4565b600082600001511415611e7e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f63616e277420646976696465206279203000000000000000000000000000000081525060200191505060405180910390fd5b600069d3c21bcecceda10000008460000151029050836000015169d3c21bcecceda10000008281611eab57fe5b0414611f1f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f6f766572666c6f7720617420646976696465000000000000000000000000000081525060200191505060405180910390fd5b604051806020016040528084600001518381611f3757fe5b0481525091505092915050565b611f4c611fe4565b604051806020016040528069d3c21bcecceda100000080856000015181611f6f57fe5b04028152509050919050565b611f83611fe4565b604051806020016040528069d3c21bcecceda100000080856000015181611fa657fe5b04028460000151038152509050919050565b600064e8d4a51000905090565b60007601357c299a88ea76a58924d52ce4f26a85af186c2b9e74905090565b604051806020016040528060008152509056fe61646a7573746d656e74207370656564206d75737420626520736d616c6c6572207468616e20314f776e61626c653a206e6577206f776e657220697320746865207a65726f206164647265737363616e277420637265617465206669786964697479206e756d626572206c6172676572207468616e206d61784e657746697865642829676173207072696365206d696e696d756d20666c6f6f72206d7573742062652067726561746572207468616e207a65726f536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f777461726765742064656e73697479206d75737420626520736d616c6c6572207468616e2031a265627a7a72315820e95735aa2f9366d4316f1a91bb17840699df40d20c507f53b5ac4e6e8a2fe08f64736f6c634300050d0032";

    public static final String FUNC_ADJUSTMENTSPEED = "adjustmentSpeed";

    public static final String FUNC_GASPRICEMINIMUM = "gasPriceMinimum";

    public static final String FUNC_GASPRICEMINIMUMFLOOR = "gasPriceMinimumFloor";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_TARGETDENSITY = "targetDensity";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETADJUSTMENTSPEED = "setAdjustmentSpeed";

    public static final String FUNC_SETTARGETDENSITY = "setTargetDensity";

    public static final String FUNC_SETGASPRICEMINIMUMFLOOR = "setGasPriceMinimumFloor";

    public static final String FUNC_GETGASPRICEMINIMUM = "getGasPriceMinimum";

    public static final String FUNC_UPDATEGASPRICEMINIMUM = "updateGasPriceMinimum";

    public static final String FUNC_GETUPDATEDGASPRICEMINIMUM = "getUpdatedGasPriceMinimum";

    public static final Event ADJUSTMENTSPEEDSET_EVENT = new Event("AdjustmentSpeedSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event GASPRICEMINIMUMFLOORSET_EVENT = new Event("GasPriceMinimumFloorSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event GASPRICEMINIMUMUPDATED_EVENT = new Event("GasPriceMinimumUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event TARGETDENSITYSET_EVENT = new Event("TargetDensitySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected GasPriceMinimum(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GasPriceMinimum(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GasPriceMinimum(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GasPriceMinimum(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AdjustmentSpeedSetEventResponse> getAdjustmentSpeedSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ADJUSTMENTSPEEDSET_EVENT, transactionReceipt);
        ArrayList<AdjustmentSpeedSetEventResponse> responses = new ArrayList<AdjustmentSpeedSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AdjustmentSpeedSetEventResponse typedResponse = new AdjustmentSpeedSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.adjustmentSpeed = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AdjustmentSpeedSetEventResponse> adjustmentSpeedSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AdjustmentSpeedSetEventResponse>() {
            @Override
            public AdjustmentSpeedSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ADJUSTMENTSPEEDSET_EVENT, log);
                AdjustmentSpeedSetEventResponse typedResponse = new AdjustmentSpeedSetEventResponse();
                typedResponse.log = log;
                typedResponse.adjustmentSpeed = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AdjustmentSpeedSetEventResponse> adjustmentSpeedSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADJUSTMENTSPEEDSET_EVENT));
        return adjustmentSpeedSetEventFlowable(filter);
    }

    public List<GasPriceMinimumFloorSetEventResponse> getGasPriceMinimumFloorSetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(GASPRICEMINIMUMFLOORSET_EVENT, transactionReceipt);
        ArrayList<GasPriceMinimumFloorSetEventResponse> responses = new ArrayList<GasPriceMinimumFloorSetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            GasPriceMinimumFloorSetEventResponse typedResponse = new GasPriceMinimumFloorSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.gasPriceMinimumFloor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GasPriceMinimumFloorSetEventResponse> gasPriceMinimumFloorSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, GasPriceMinimumFloorSetEventResponse>() {
            @Override
            public GasPriceMinimumFloorSetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(GASPRICEMINIMUMFLOORSET_EVENT, log);
                GasPriceMinimumFloorSetEventResponse typedResponse = new GasPriceMinimumFloorSetEventResponse();
                typedResponse.log = log;
                typedResponse.gasPriceMinimumFloor = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GasPriceMinimumFloorSetEventResponse> gasPriceMinimumFloorSetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GASPRICEMINIMUMFLOORSET_EVENT));
        return gasPriceMinimumFloorSetEventFlowable(filter);
    }

    public List<GasPriceMinimumUpdatedEventResponse> getGasPriceMinimumUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(GASPRICEMINIMUMUPDATED_EVENT, transactionReceipt);
        ArrayList<GasPriceMinimumUpdatedEventResponse> responses = new ArrayList<GasPriceMinimumUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            GasPriceMinimumUpdatedEventResponse typedResponse = new GasPriceMinimumUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.gasPriceMinimum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GasPriceMinimumUpdatedEventResponse> gasPriceMinimumUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, GasPriceMinimumUpdatedEventResponse>() {
            @Override
            public GasPriceMinimumUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(GASPRICEMINIMUMUPDATED_EVENT, log);
                GasPriceMinimumUpdatedEventResponse typedResponse = new GasPriceMinimumUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.gasPriceMinimum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GasPriceMinimumUpdatedEventResponse> gasPriceMinimumUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GASPRICEMINIMUMUPDATED_EVENT));
        return gasPriceMinimumUpdatedEventFlowable(filter);
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

    public List<TargetDensitySetEventResponse> getTargetDensitySetEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TARGETDENSITYSET_EVENT, transactionReceipt);
        ArrayList<TargetDensitySetEventResponse> responses = new ArrayList<TargetDensitySetEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TargetDensitySetEventResponse typedResponse = new TargetDensitySetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.targetDensity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TargetDensitySetEventResponse> targetDensitySetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TargetDensitySetEventResponse>() {
            @Override
            public TargetDensitySetEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TARGETDENSITYSET_EVENT, log);
                TargetDensitySetEventResponse typedResponse = new TargetDensitySetEventResponse();
                typedResponse.log = log;
                typedResponse.targetDensity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TargetDensitySetEventResponse> targetDensitySetEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TARGETDENSITYSET_EVENT));
        return targetDensitySetEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> adjustmentSpeed() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADJUSTMENTSPEED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> gasPriceMinimum() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GASPRICEMINIMUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> gasPriceMinimumFloor() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GASPRICEMINIMUMFLOOR, 
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

    public RemoteFunctionCall<BigInteger> targetDensity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TARGETDENSITY, 
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String _registryAddress, BigInteger _gasPriceMinimumFloor, BigInteger _targetDensity, BigInteger _adjustmentSpeed) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(_registryAddress),
                new Uint256(_gasPriceMinimumFloor),
                new Uint256(_targetDensity),
                new Uint256(_adjustmentSpeed)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAdjustmentSpeed(BigInteger _adjustmentSpeed) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETADJUSTMENTSPEED, 
                Arrays.<Type>asList(new Uint256(_adjustmentSpeed)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTargetDensity(BigInteger _targetDensity) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTARGETDENSITY, 
                Arrays.<Type>asList(new Uint256(_targetDensity)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setGasPriceMinimumFloor(BigInteger _gasPriceMinimumFloor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETGASPRICEMINIMUMFLOOR, 
                Arrays.<Type>asList(new Uint256(_gasPriceMinimumFloor)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getGasPriceMinimum(String tokenAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETGASPRICEMINIMUM, 
                Arrays.<Type>asList(new Address(tokenAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateGasPriceMinimum(BigInteger blockGasTotal, BigInteger blockGasLimit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEGASPRICEMINIMUM, 
                Arrays.<Type>asList(new Uint256(blockGasTotal),
                new Uint256(blockGasLimit)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getUpdatedGasPriceMinimum(BigInteger blockGasTotal, BigInteger blockGasLimit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUPDATEDGASPRICEMINIMUM, 
                Arrays.<Type>asList(new Uint256(blockGasTotal),
                new Uint256(blockGasLimit)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static GasPriceMinimum load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GasPriceMinimum(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GasPriceMinimum load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GasPriceMinimum(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GasPriceMinimum load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GasPriceMinimum(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GasPriceMinimum load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GasPriceMinimum(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GasPriceMinimum> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GasPriceMinimum.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GasPriceMinimum> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GasPriceMinimum.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<GasPriceMinimum> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GasPriceMinimum.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GasPriceMinimum> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GasPriceMinimum.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class AdjustmentSpeedSetEventResponse extends BaseEventResponse {
        public BigInteger adjustmentSpeed;
    }

    public static class GasPriceMinimumFloorSetEventResponse extends BaseEventResponse {
        public BigInteger gasPriceMinimumFloor;
    }

    public static class GasPriceMinimumUpdatedEventResponse extends BaseEventResponse {
        public BigInteger gasPriceMinimum;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }

    public static class TargetDensitySetEventResponse extends BaseEventResponse {
        public BigInteger targetDensity;
    }
}
