package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Freezer;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class FreezerWrapper extends BaseWrapper<Freezer> {

    public FreezerWrapper(Freezer contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static FreezerWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        Freezer contract = Freezer.load(contractAddress, web3j, transactionManager, gasProvider);
        return new FreezerWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<Freezer> deploy() {
        return Freezer.deploy(web3j, transactionManager, gasProvider);
    }

    public RemoteFunctionCall<Boolean> isFrozen(String param0) {
        return contract.isFrozen(param0);
    }

    public RemoteFunctionCall<TransactionReceipt> freeze(String target) {
        return contract.freeze(target);
    }

    public RemoteFunctionCall<TransactionReceipt> unfreeze(String target) {
        return contract.unfreeze(target);
    }
}
