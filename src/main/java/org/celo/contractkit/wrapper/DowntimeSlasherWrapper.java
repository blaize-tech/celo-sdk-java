package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.DowntimeSlasher;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;

/**
 * Contract handling slashing for Validator downtime using intervals.
 * TODO add docs, utils methods
 */
public class DowntimeSlasherWrapper extends BaseWrapper<DowntimeSlasher> {

    public DowntimeSlasherWrapper(DowntimeSlasher contract) {
        super(contract);
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
