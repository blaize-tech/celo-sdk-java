package org.celo.contractkit;

import org.celo.contractkit.contract.Registry;
import org.celo.contractkit.wrapper.GovernanceWrapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.assertEquals;

public class GovernanceTest {

    final BigInteger PROPOSAL_ID = BigInteger.valueOf(1);
    final BigInteger MIN_DEPOSIT = Convert.toWei(BigDecimal.valueOf(1), Convert.Unit.ETHER).toBigInteger();

    ContractKit contractKit;
    GovernanceWrapper governance;
    List<String> accounts;
    Registry addressRegistry;
    GovernanceWrapper.Proposal proposal;

    Map<String, String> repoints = new HashMap<String, String>() {{
        put("Random","0x0000000000000000000000000000000000000001");
        put("Escrow", "0x0000000000000000000000000000000000000002");
    }};

    void proposeFn(String proposer) throws Exception {
        this.governance.propose(this.proposal, "URL"/*, {'value': self.min_deposit}*/).send();
    }

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);
        governance = contractKit.contracts.getGovernance();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
        addressRegistry = contractKit.contracts.addressRegistry.getRegistryContract();

        List<GovernanceWrapper.Transaction> transactions = repoints.entrySet().stream().map(transaction ->
                new GovernanceWrapper.Transaction(
                        BigInteger.ZERO,
                        "",
                        new byte[]{},
                        "0x000000000000000000000000000000000000ce10",
                        addressRegistry.setAddressFor(transaction.getKey(), transaction.getValue()).encodeFunctionCall()
                )).collect(Collectors.toList());
        proposal = new GovernanceWrapper.Proposal(transactions);
    }

    @Test
    public void testGetConfig() throws Exception {
        GovernanceWrapper.Config config = governance.getConfig();

        assertEquals(BigInteger.valueOf(5), config.concurrentProposals);
        assertEquals(BigInteger.valueOf(30), config.dequeueFrequency);
        assertEquals(MIN_DEPOSIT, config.minDeposit);
        assertEquals(BigInteger.valueOf(1_000), config.queueExpiry);

        assertEquals(BigInteger.valueOf(100), config.stageDurationsApproval);
        assertEquals(BigInteger.valueOf(100), config.stageDurationsExecution);
        assertEquals(BigInteger.valueOf(100), config.stageDurationsReferendum);
    }

    @Test
    @Ignore("Too small deposit")
    public void testPropose() throws Exception {
        proposeFn(accounts.get(0));

        GovernanceWrapper.ProposalRecord proposalRecord = governance.getProposalRecord(PROPOSAL_ID);

        assertEquals(accounts.get(0), proposalRecord.metadata.proposer);
        assertEquals(3, proposalRecord.metadata.transactionCount);
        assertEquals(proposal, proposalRecord.proposal);
        assertEquals(GovernanceWrapper.ProposalStage.Queued, proposalRecord.stage);
    }
}
