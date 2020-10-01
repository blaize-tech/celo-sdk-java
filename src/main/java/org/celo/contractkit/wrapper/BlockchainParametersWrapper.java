package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.BlockchainParameters;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

/**
 * Network parameters that are configurable by governance.
 * TODO add docs, utils methods
 */
public class BlockchainParametersWrapper extends BaseWrapper<BlockchainParameters> {

    public BlockchainParametersWrapper(BlockchainParameters contract) {
        super(contract);
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
