package org.celo.contractkit;

import org.celo.contractkit.wrapper.AccountsWrapper;
import org.celo.contractkit.wrapper.LockedGoldWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.ContractKitOptions.GANACHE_OPTIONS;
import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LockedGoldTest {

    BigInteger minLockedGoldValue = Convert.toWei(BigDecimal.valueOf(10_000), Convert.Unit.ETHER).toBigInteger();

    ContractKit contractKit;
    LockedGoldWrapper lockedGold;
    AccountsWrapper accountsInstance;
    List<String> accounts;

    void registerAccount(String account) throws Exception {
        if (!(accountsInstance.isAccount(account).send())) {
            String data = accountsInstance.createAccount().encodeFunctionCall();
            contractKit.sendTransaction(CeloContract.Accounts, data, account);
        }
    }

    @Before
    public void initialize() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);
        accountsInstance = contractKit.contracts.getAccounts();
        lockedGold = contractKit.contracts.getLockedGold();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();

        this.registerAccount(accounts.get(0));
    }

    @Test
    public void testLockGold() throws Exception {
        TransactionReceipt tx = lockedGold.lock(minLockedGoldValue).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());
    }

    @Test
    public void testUnlockGold() throws Exception {
        TransactionReceipt tx = lockedGold.unlock(minLockedGoldValue).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());
    }
}
