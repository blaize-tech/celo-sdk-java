package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.DowntimeSlasher;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;

/**
 * Contract handling slashing for Validator downtime using intervals.
 * TODO add docs, utils methods
 */
public class DowntimeSlasherWrapper extends BaseWrapper<DowntimeSlasher> {

    public DowntimeSlasherWrapper(DowntimeSlasher contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static DowntimeSlasherWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        DowntimeSlasher contract = DowntimeSlasher.load(contractAddress, web3j, transactionManager, gasProvider);
        return new DowntimeSlasherWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<DowntimeSlasher> deploy() {
        return DowntimeSlasher.deploy(web3j, transactionManager, gasProvider);
    }

    public List<DowntimeSlasher.SlashableDowntimeSetEventResponse> getSlashableDowntimeSetEvents(TransactionReceipt transactionReceipt) {
        return contract.getSlashableDowntimeSetEvents(transactionReceipt);
    }

    public RemoteFunctionCall<BigInteger> slashableDowntime() {
        return contract.slashableDowntime();
    }

    public RemoteFunctionCall<TransactionReceipt> setBitmapForInterval(BigInteger startBlock, BigInteger endBlock) {
        return contract.setBitmapForInterval(startBlock, endBlock);
    }

    public RemoteFunctionCall<Boolean> wasDownForInterval(BigInteger startBlock, BigInteger endBlock, BigInteger signerIndex) {
        return contract.wasDownForInterval(startBlock, endBlock, signerIndex);
    }

    public RemoteFunctionCall<Boolean> isBitmapSetForInterval(BigInteger startBlock, BigInteger endBlock) {
        return contract.isBitmapSetForInterval(startBlock, endBlock);
    }

    public RemoteFunctionCall<Boolean> wasDownForIntervals(List<BigInteger> startBlocks, List<BigInteger> endBlocks, List<BigInteger> signerIndices) {
        return contract.wasDownForIntervals(startBlocks, endBlocks, signerIndices);
    }

    public RemoteFunctionCall<TransactionReceipt> slash(List<BigInteger> startBlocks, List<BigInteger> endBlocks, List<BigInteger> signerIndices, BigInteger groupMembershipHistoryIndex, List<String> validatorElectionLessers, List<String> validatorElectionGreaters, List<BigInteger> validatorElectionIndices, List<String> groupElectionLessers, List<String> groupElectionGreaters, List<BigInteger> groupElectionIndices) {
        return contract.slash(startBlocks, endBlocks, signerIndices, groupMembershipHistoryIndex, validatorElectionLessers, validatorElectionGreaters, validatorElectionIndices, groupElectionLessers, groupElectionGreaters, groupElectionIndices);
    }
}
