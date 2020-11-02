package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.BlockchainParameters;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

/**
 * Network parameters that are configurable by governance.
 * TODO add docs, utils methods
 */
public class BlockchainParametersWrapper extends BaseWrapper<BlockchainParameters> {

    public BlockchainParametersWrapper(BlockchainParameters contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static BlockchainParametersWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        BlockchainParameters contract = BlockchainParameters.load(contractAddress, web3j, transactionManager, gasProvider);
        return new BlockchainParametersWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<BlockchainParameters> deploy() {
        return BlockchainParameters.deploy(web3j, transactionManager, gasProvider);
    }

    public RemoteFunctionCall<BigInteger> blockGasLimit() {
        return contract.blockGasLimit();
    }

    public RemoteFunctionCall<TransactionReceipt> setMinimumClientVersion(BigInteger major, BigInteger minor, BigInteger patch) {
        return contract.setMinimumClientVersion(major, minor, patch);
    }

    public RemoteFunctionCall<TransactionReceipt> setBlockGasLimit(BigInteger gasLimit) {
        return contract.setBlockGasLimit(gasLimit);
    }

    public RemoteFunctionCall<TransactionReceipt> setIntrinsicGasForAlternativeFeeCurrency(BigInteger gas) {
        return contract.setIntrinsicGasForAlternativeFeeCurrency(gas);
    }
}
