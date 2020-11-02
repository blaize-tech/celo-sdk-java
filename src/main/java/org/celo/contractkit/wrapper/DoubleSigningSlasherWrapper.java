package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.DoubleSigningSlasher;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
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

    public DoubleSigningSlasherWrapper(DoubleSigningSlasher contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static DoubleSigningSlasherWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        DoubleSigningSlasher contract = DoubleSigningSlasher.load(contractAddress, web3j, transactionManager, gasProvider);
        return new DoubleSigningSlasherWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<DoubleSigningSlasher> deploy() {
        return DoubleSigningSlasher.deploy(web3j, transactionManager, gasProvider);
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
