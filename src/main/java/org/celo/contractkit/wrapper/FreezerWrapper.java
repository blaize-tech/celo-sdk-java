package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Freezer;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class FreezerWrapper extends BaseWrapper<Freezer> {
    public FreezerWrapper(Freezer contract) {
        super(contract);
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
