package org.celo.contractkit.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

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
public class Escrow extends Contract {
    public static final String BINARY = "0x60806040526001600081905550600061001c6100c060201b60201c565b905080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3506100c8565b600033905090565b612dce806100d76000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c806374a8f103116100a25780638f80c33e116100715780638f80c33e146105a6578063a91ee0dc1461061e578063c4d66de814610662578063e1d9a080146106a6578063f2fde38b146107345761010b565b806374a8f103146104945780637b103999146104f05780638da5cb5b1461053a5780638f32d59b146105845761010b565b80635b57b65b116100de5780635b57b65b1461027b578063680d782c146102fe578063702cb75d146103e6578063715018a61461048a5761010b565b8063158ef93e1461011057806318d46532146101325780633e68d5d7146101cb57806354255be014610248575b600080fd5b610118610778565b604051808215151515815260200191505060405180910390f35b6101746004803603602081101561014857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061078b565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156101b757808201518184015260208101905061019c565b505050509050019250505060405180910390f35b61022e600480360360808110156101e157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff1690602001909291908035906020019092919080359060200190929190505050610858565b604051808215151515815260200191505060405180910390f35b61025061108d565b6040518085815260200184815260200183815260200182815260200194505050505060405180910390f35b6102a76004803603602081101561029157600080fd5b81019080803590602001909291905050506110b4565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156102ea5780820151818401526020810190506102cf565b505050509050019250505060405180910390f35b6103406004803603602081101561031457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611155565b604051808a81526020018973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001878152602001868152602001858152602001848152602001838152602001828152602001995050505050505050505060405180910390f35b610470600480360360c08110156103fc57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506111e3565b604051808215151515815260200191505060405180910390f35b610492611a4f565b005b6104d6600480360360208110156104aa57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611b8a565b604051808215151515815260200191505060405180910390f35b6104f8612055565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61054261207b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61058c6120a5565b604051808215151515815260200191505060405180910390f35b6105dc600480360360408110156105bc57600080fd5b810190808035906020019092919080359060200190929190505050612104565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6106606004803603602081101561063457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061214f565b005b6106a46004803603602081101561067857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506122f3565b005b6106f2600480360360408110156106bc57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506123a5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6107766004803603602081101561074a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506123f0565b005b600160149054906101000a900460ff1681565b6060600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561084c57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610802575b50505050509050919050565b600060016000808282540192505081905550600080549050600073__Signatures____________________________6396ef41a1338888886040518563ffffffff1660e01b8152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018460ff1660ff16815260200183815260200182815260200194505050505060206040518083038186803b15801561090b57600080fd5b505af415801561091f573d6000803e3d6000fd5b505050506040513d602081101561093557600080fd5b810190808051906020019092919050505090508673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16146109cc576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602d815260200180612d17602d913960400191505060405180910390fd5b6109d4612b6f565b600360008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060405180610120016040529081600082015481526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160038201548152602001600482015481526020016005820154815260200160068201548152602001600782015481526020016008820154815250509050600073ffffffffffffffffffffffffffffffffffffffff16816040015173ffffffffffffffffffffffffffffffffffffffff1614158015610b58575060008160600151115b610bca576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f496e76616c69642077697468647261772076616c75652e00000000000000000081525060200191505060405180910390fd5b6000816000015150602060ff161115610e18576000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd92723360405160200180807f4174746573746174696f6e730000000000000000000000000000000000000000815250600c019050604051602081830303815290604052805190602001206040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610c9857600080fd5b505afa158015610cac573d6000803e3d6000fd5b505050506040513d6020811015610cc257600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1663596abea58460000151336040518363ffffffff1660e01b8152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050604080518083038186803b158015610d5f57600080fd5b505afa158015610d73573d6000803e3d6000fd5b505050506040513d6040811015610d8957600080fd5b8101908080519060200190929190805190602001909291905050505063ffffffff1690508261010001518167ffffffffffffffff161015610e15576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526048815260200180612ccf6048913960600191505060405180910390fd5b50505b610e2188612476565b806040015173ffffffffffffffffffffffffffffffffffffffff1663a9059cbb3383606001516040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b158015610eb057600080fd5b505af1158015610ec4573d6000803e3d6000fd5b505050506040513d6020811015610eda57600080fd5b8101908080519060200190929190505050610f5d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f5472616e73666572206e6f74207375636365737366756c2e000000000000000081525060200191505060405180910390fd5b806040015173ffffffffffffffffffffffffffffffffffffffff16816020015173ffffffffffffffffffffffffffffffffffffffff1682600001517fab4f92d461fdbd1af5db2375223d65edb43bcb99129b19ab4954004883e5202584606001518c604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a46001935050506000548114611084576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f7265656e7472616e742063616c6c00000000000000000000000000000000000081525060200191505060405180910390fd5b50949350505050565b60008060008060018060016000839350829250819150809050935093509350935090919293565b60606004600083815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561114957602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116110ff575b50505050509050919050565b60036020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040154908060050154908060060154908060070154908060080154905089565b600060016000808282540192505081905550600080549050600073ffffffffffffffffffffffffffffffffffffffff168773ffffffffffffffffffffffffffffffffffffffff16141580156112385750600086115b80156112445750600085115b6112b6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f496e76616c6964207472616e7366657220696e707574732e000000000000000081525060200191505060405180910390fd5b6000602060ff16111580156112cc575060008314155b15611322576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526043815260200180612c8c6043913960600191505060405180910390fd5b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd92723360405160200180807f4174746573746174696f6e730000000000000000000000000000000000000000815250600c019050604051602081830303815290604052805190602001206040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b1580156113dd57600080fd5b505afa1580156113f1573d6000803e3d6000fd5b505050506040513d602081101561140757600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff16637796a6846040518163ffffffff1660e01b815260040160206040518083038186803b15801561146057600080fd5b505afa158015611474573d6000803e3d6000fd5b505050506040513d602081101561148a57600080fd5b81019080805190602001909291905050508411156114f3576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526021815260200180612d446021913960400191505060405180910390fd5b60006115aa6001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208890806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061288f90919063ffffffff16565b905060006116376001600460008e81526020019081526020016000208990806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061288f90919063ffffffff16565b90506000600360008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060008160060154146116f6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f7061796d656e74496420616c726561647920757365640000000000000000000081525060200191505060405180910390fd5b8b8160000181905550338160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508a8160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508981600301819055508281600401819055508181600501819055504281600601819055508881600701819055508681600801819055508a73ffffffffffffffffffffffffffffffffffffffff166323b872dd33308d6040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b15801561187657600080fd5b505af115801561188a573d6000803e3d6000fd5b505050506040513d60208110156118a057600080fd5b8101908080519060200190929190505050611923576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f5472616e7366657220756e7375636365737366756c2e0000000000000000000081525060200191505060405180910390fd5b8a73ffffffffffffffffffffffffffffffffffffffff168c3373ffffffffffffffffffffffffffffffffffffffff167f0fc2463e82c3b8a7868e75b68a76a144816d772687e5b09f45c02db37eedf4f68d8c8c604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a460019550505050506000548114611a44576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f7265656e7472616e742063616c6c00000000000000000000000000000000000081525060200191505060405180910390fd5b509695505050505050565b611a576120a5565b611ac9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a36000600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b600060016000808282540192505081905550600080549050611baa612b6f565b600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060405180610120016040529081600082015481526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600382015481526020016004820154815260200160058201548152602001600682015481526020016007820154815260200160088201548152505090503373ffffffffffffffffffffffffffffffffffffffff16816020015173ffffffffffffffffffffffffffffffffffffffff1614611d71576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526035815260200180612d656035913960400191505060405180910390fd5b611d8c8160e001518260c001516128d990919063ffffffff16565b421015611de4576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a815260200180612c62602a913960400191505060405180910390fd5b611ded84612476565b806040015173ffffffffffffffffffffffffffffffffffffffff1663a9059cbb3383606001516040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b158015611e7c57600080fd5b505af1158015611e90573d6000803e3d6000fd5b505050506040513d6020811015611ea657600080fd5b8101908080519060200190929190505050611f29576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f5472616e73666572206e6f74207375636365737366756c2e000000000000000081525060200191505060405180910390fd5b806040015173ffffffffffffffffffffffffffffffffffffffff16816020015173ffffffffffffffffffffffffffffffffffffffff1682600001517f6c464fad8039e6f09ec3a57a29f132cf2573d166833256960e2407eefff8f592846060015188604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a46001925050600054811461204f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f7265656e7472616e742063616c6c00000000000000000000000000000000000081525060200191505060405180910390fd5b50919050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166120e8612961565b73ffffffffffffffffffffffffffffffffffffffff1614905090565b6004602052816000526040600020818154811061211d57fe5b906000526020600020016000915091509054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6121576120a5565b6121c9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141561226c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f43616e6e6f7420726567697374657220746865206e756c6c206164647265737381525060200191505060405180910390fd5b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff167f27fe5f0c1c3b1ed427cc63d0f05759ffdecf9aec9e18d31ef366fc8a6cb5dc3b60405160405180910390a250565b600160149054906101000a900460ff1615612376576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601c8152602001807f636f6e747261637420616c726561647920696e697469616c697a65640000000081525060200191505060405180910390fd5b60018060146101000a81548160ff02191690831515021790555061239933612969565b6123a28161214f565b50565b600560205281600052604060002081815481106123be57fe5b906000526020600020016000915091509054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6123f86120a5565b61246a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b61247381612969565b50565b6000600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090506000600460008360000154815260200190815260200160002090506000600560008460010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508260050154600360008460018680549050038154811061255757fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050181905550816001838054905003815481106125d657fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168284600501548154811061261157fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506126716001838054905061288f90919063ffffffff16565b828161267d9190612bea565b508260040154600360008360018580549050038154811061269a57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401819055508060018280549050038154811061271957fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168184600401548154811061275457fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506127b46001828054905061288f90919063ffffffff16565b81816127c09190612bea565b50600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000808201600090556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600382016000905560048201600090556005820160009055600682016000905560078201600090556008820160009055505050505050565b60006128d183836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250612aaf565b905092915050565b600080828401905083811015612957576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b600033905090565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156129ef576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526026815260200180612c3c6026913960400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000838311158290612b5c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b83811015612b21578082015181840152602081019050612b06565b50505050905090810190601f168015612b4e5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5060008385039050809150509392505050565b60405180610120016040528060008019168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016000815260200160008152602001600081526020016000815260200160008152602001600081525090565b815481835581811115612c1157818360005260206000209182019101612c109190612c16565b5b505050565b612c3891905b80821115612c34576000816000905550600101612c1c565b5090565b9056fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f20616464726573735472616e73616374696f6e206e6f742072656465656d61626c6520666f722073656e646572207965742e496e76616c6964207072697661637920696e707574733a2043616e27742072657175697265206174746573746174696f6e73206966206e6f206964656e74696669657254686973206163636f756e7420646f6573206e6f74206861766520656e6f756768206174746573746174696f6e7320746f2077697468647261772074686973207061796d656e742e4661696c656420746f2070726f7665206f776e657273686970206f6620746865207769746864726177206b65796d696e4174746573746174696f6e73206c6172676572207468616e206c696d69744f6e6c792073656e646572206f66207061796d656e742063616e20617474656d707420746f207265766f6b65207061796d656e742ea265627a7a7231582099b5e8fe3c458593606dc2e18f61efa0ed911e4099cdbfb35355c5d5201607f664736f6c634300050d0032";

    public static final String FUNC_ESCROWEDPAYMENTS = "escrowedPayments";

    public static final String FUNC_INITIALIZED = "initialized";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RECEIVEDPAYMENTIDS = "receivedPaymentIds";

    public static final String FUNC_REGISTRY = "registry";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SENTPAYMENTIDS = "sentPaymentIds";

    public static final String FUNC_SETREGISTRY = "setRegistry";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETVERSIONNUMBER = "getVersionNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_REVOKE = "revoke";

    public static final String FUNC_GETRECEIVEDPAYMENTIDS = "getReceivedPaymentIds";

    public static final String FUNC_GETSENTPAYMENTIDS = "getSentPaymentIds";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REGISTRYSET_EVENT = new Event("RegistrySet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event REVOCATION_EVENT = new Event("Revocation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWAL_EVENT = new Event("Withdrawal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    public List<RevocationEventResponse> getRevocationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REVOCATION_EVENT, transactionReceipt);
        ArrayList<RevocationEventResponse> responses = new ArrayList<RevocationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RevocationEventResponse typedResponse = new RevocationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.by = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RevocationEventResponse> revocationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RevocationEventResponse>() {
            @Override
            public RevocationEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REVOCATION_EVENT, log);
                RevocationEventResponse typedResponse = new RevocationEventResponse();
                typedResponse.log = log;
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.by = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RevocationEventResponse> revocationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVOCATION_EVENT));
        return revocationEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.minAttestations = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.minAttestations = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<WithdrawalEventResponse> getWithdrawalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWAL_EVENT, transactionReceipt);
        ArrayList<WithdrawalEventResponse> responses = new ArrayList<WithdrawalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawalEventResponse typedResponse = new WithdrawalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.identifier = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.token = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.paymentId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawalEventResponse> withdrawalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWAL_EVENT));
        return withdrawalEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple9<byte[], String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> escrowedPayments(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ESCROWEDPAYMENTS, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple9<byte[], String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple9<byte[], String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<byte[], String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<byte[], String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
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

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> receivedPaymentIds(byte[] param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RECEIVEDPAYMENTIDS, 
                Arrays.<Type>asList(new Bytes32(param0),
                new Uint256(param1)),
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

    public RemoteFunctionCall<String> sentPaymentIds(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SENTPAYMENTIDS, 
                Arrays.<Type>asList(new Address(param0),
                new Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(registryAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(byte[] identifier, String token, BigInteger value, BigInteger expirySeconds, String paymentId, BigInteger minAttestations) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Bytes32(identifier),
                new Address(token),
                new Uint256(value),
                new Uint256(expirySeconds),
                new Address(paymentId),
                new Uint256(minAttestations)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String paymentId, BigInteger v, byte[] r, byte[] s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new Address(paymentId),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revoke(String paymentId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new Address(paymentId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getReceivedPaymentIds(byte[] identifier) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRECEIVEDPAYMENTIDS, 
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

    public RemoteFunctionCall<List> getSentPaymentIds(String sender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSENTPAYMENTIDS, 
                Arrays.<Type>asList(new Address(sender)),
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

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Escrow.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Escrow.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Escrow.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Escrow.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RegistrySetEventResponse extends BaseEventResponse {
        public String registryAddress;
    }

    public static class RevocationEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String by;

        public String token;

        public BigInteger value;

        public String paymentId;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public byte[] identifier;

        public String token;

        public BigInteger value;

        public String paymentId;

        public BigInteger minAttestations;
    }

    public static class WithdrawalEventResponse extends BaseEventResponse {
        public byte[] identifier;

        public String to;

        public String token;

        public BigInteger value;

        public String paymentId;
    }
}
