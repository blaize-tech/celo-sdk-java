package org.celo.contractkit;

import org.celo.contractkit.wrapper.AccountsWrapper;
import org.celo.contractkit.wrapper.LockedGoldWrapper;
import org.celo.contractkit.wrapper.ValidatorsWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.assertTrue;

public class ValidatorsTest {

    ContractKit contractKit;
    ValidatorsWrapper validators;
    AccountsWrapper accountsWrapper;
    LockedGoldWrapper lockedGold;

    BigInteger minLockedGoldValue = Convert.toWei(BigDecimal.valueOf(10_000), Convert.Unit.ETHER).toBigInteger();

    String blsPublicKey = "0x4fa3f67fc913878b068d1fa1cdddc54913d3bf988dbe5a36a20fa888f20d4894c408a6773f3d7bde11154f2a3076b700d345a42fd25a0e5e83f4db5586ac7979ac2053cd95d8f2efd3e959571ceccaa743e02cf4be3f5d7aaddb0b06fc9aff00";
    String blsPoP = "0xcdb77255037eb68897cd487fdd85388cbda448f617f874449d4b11588b0b7ad8ddc20d9bb450b513bb35664ea3923900";
    List<String> accounts;

    void registerAccountWithLockedGold(String account, BigInteger value) throws Exception {
        if (!(accountsWrapper.isAccount(account).send())) {
            String data = accountsWrapper.createAccount().encodeFunctionCall();
            contractKit.sendTransaction(CeloContract.Accounts, data, account);
        }
        String lockData = lockedGold.lock(value != null ? value : minLockedGoldValue).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.LockedGold, lockData, account);
    }

    void setupGroup(String groupAccount, int members) throws Exception {
        registerAccountWithLockedGold(
                groupAccount,
                minLockedGoldValue.multiply(BigInteger.valueOf(members))
        );

        String data = validators.registerValidatorGroup(BigDecimal.valueOf(0.1).toBigInteger()).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.Validators, data, groupAccount);
    }

    void setupValidator(String validatorAccount) throws Exception {
        registerAccountWithLockedGold(validatorAccount, null);

        Credentials credentials = contractKit.transactionManager.getCredentials();
        byte[] publicKey = credentials.getEcKeyPair().getPublicKey().toByteArray();

        String data = validators.registerValidator(publicKey, blsPublicKey, blsPoP).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.Validators, data, validatorAccount);
    }

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        for (String key: DERIV_PRIVATE_KEYS) {
            contractKit.addAccount(key);
        }

        validators = contractKit.contracts.getValidators();
        accountsWrapper = contractKit.contracts.getAccounts();
        lockedGold = contractKit.contracts.getLockedGold();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testRegisterValidatorGroup() throws Exception {
        String groupAccount = accounts.get(0);
        setupGroup(groupAccount, 1);

        assertTrue(validators.isValidatorGroup(groupAccount).send());
    }

    @Test
    public void testRegisterValidator() throws Exception {
        String validatorAccount = accounts.get(1);
        setupValidator(validatorAccount);

        assertTrue(validators.isValidator(validatorAccount).send());
    }

    @Test
    public void testAddMember() throws Exception {
        String groupAccount = accounts.get(0);
        String validatorAccount = accounts.get(1);

        setupGroup(groupAccount, 1);
        setupValidator(validatorAccount);

        validators.affiliate(groupAccount).send();
        validators.addMember(validatorAccount).send();

        int isMember = validators.getValidatorGroup(groupAccount).send().component1().indexOf(groupAccount);
        assertTrue(isMember > 0);
    }
}
