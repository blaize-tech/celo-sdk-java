package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.DoubleSigningSlasher;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;

/**
 * Contract handling slashing for Validator double-signing
 * TODO add docs, utils methods
 */
public class DoubleSigningSlasherWrapper extends BaseWrapper<DoubleSigningSlasher> {

    public DoubleSigningSlasherWrapper(DoubleSigningSlasher contract) {
        super(contract);
    }

    public RemoteFunctionCall<BigInteger> getBlockNumberFromHeader(byte[] header) {
        return contract.getBlockNumberFromHeader(header);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> slashingIncentives() {
        return contract.slashingIncentives();
    }

    public RemoteFunctionCall<TransactionReceipt> slash(String signer, BigInteger index, byte[] headerA, byte[] headerB, BigInteger groupMembershipHistoryIndex, List<String> validatorElectionLessers, List<String> validatorElectionGreaters, List<BigInteger> validatorElectionIndices, List<String> groupElectionLessers, List<String> groupElectionGreaters, List<BigInteger> groupElectionIndices) {
        return contract.slash(signer, index, headerA, headerB, groupMembershipHistoryIndex, validatorElectionLessers, validatorElectionGreaters, validatorElectionIndices, groupElectionLessers, groupElectionGreaters, groupElectionIndices);
    }
}
