package org.celo.contractkit;

import org.celo.contractkit.contract.LockedGold;
import org.celo.contractkit.wrapper.LockedGoldWrapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static org.celo.contractkit.TestData.PRIVATE_KEY_1;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LockedGoldTest {
    static final BigInteger lockedValue = BigInteger.valueOf(120938732980L);

    ContractKit contractKit;
    LockedGoldWrapper lockedGold;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        Credentials credentials = Credentials.create(PRIVATE_KEY_1);
        contractKit = new ContractKit(web3j, credentials);
        lockedGold = contractKit.contracts.getLockedGold();
    }

    @Test
    @Ignore
    public void testLockGold() throws Exception {
        TransactionReceipt tx = lockedGold.lock(lockedValue).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());
    }

    @Test
    @Ignore
    public void testUnlockGold() throws Exception {
        TransactionReceipt tx = lockedGold.unlock(lockedValue).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());
    }
}
