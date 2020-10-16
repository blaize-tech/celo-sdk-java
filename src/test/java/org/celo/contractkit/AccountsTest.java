package org.celo.contractkit;

import org.celo.contractkit.wrapper.AccountsWrapper;
import org.celo.contractkit.wrapper.LockedGoldWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.assertTrue;

public class AccountsTest {

    BigInteger minLockedGoldValue = Convert.toWei(BigDecimal.valueOf(10_000), Convert.Unit.ETHER).toBigInteger();

    ContractKit contractKit;
    AccountsWrapper accountsInstance;
    List<String> accounts;
    LockedGoldWrapper lockedGold;

    void registerAccountWithLockedGold(String account, BigInteger value) throws Exception {
        if (!(accountsInstance.isAccount(account).send())) {
            String data = accountsInstance.createAccount().encodeFunctionCall();
            contractKit.sendTransaction(CeloContract.Accounts, data, account);
        }
        String lockData = lockedGold.lock(value != null ? value : minLockedGoldValue).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.LockedGold, lockData, account);
    }

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[1]);
        accountsInstance = contractKit.contracts.getAccounts();
        lockedGold = contractKit.contracts.getLockedGold();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testIsAccount() throws Exception {
        registerAccountWithLockedGold(contractKit.getAddress(), null);

        Boolean isAccount = accountsInstance.isAccount(contractKit.getAddress()).send();
        assertTrue(isAccount);
    }
}
