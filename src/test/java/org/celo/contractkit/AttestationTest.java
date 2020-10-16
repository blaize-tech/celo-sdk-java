package org.celo.contractkit;

import org.celo.contractkit.wrapper.AttestationsWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.*;

public class AttestationTest {

    final String PHONE_NUMBER = "+15555555555";
    final byte[] IDENTIFIER = Utils.getPhoneHash(PHONE_NUMBER, null);

    ContractKit contractKit;
    AttestationsWrapper attestations;
    List<String> accounts;

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);
        attestations = contractKit.contracts.getAttestations();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testNoCompletions() throws Exception {
        AttestationsWrapper.AttestationStat mockResult = new AttestationsWrapper.AttestationStat(0, 3);
        AttestationsWrapper spyAttestations = Mockito.spy(attestations);
        Mockito.doReturn(mockResult).when(spyAttestations).getAttestationStat(IDENTIFIER, accounts.get(0));

        AttestationsWrapper.AttestationsStatus result = spyAttestations.getVerifiedStatus(IDENTIFIER, accounts.get(0));
        assertFalse(result.isVerified);
        assertEquals(3, result.numAttestationsRemaining);
    }

    @Test
    public void testNotEnoughCompletions() throws Exception {
        AttestationsWrapper.AttestationStat mockResult = new AttestationsWrapper.AttestationStat(2, 6);
        AttestationsWrapper spyAttestations = Mockito.spy(attestations);
        Mockito.doReturn(mockResult).when(spyAttestations).getAttestationStat(IDENTIFIER, accounts.get(0));

        AttestationsWrapper.AttestationsStatus result = spyAttestations.getVerifiedStatus(IDENTIFIER, accounts.get(0));
        assertFalse(result.isVerified);
        assertEquals(1, result.numAttestationsRemaining);
    }

    @Test
    public void testFractionTooLow() throws Exception {
        AttestationsWrapper.AttestationStat mockResult = new AttestationsWrapper.AttestationStat(3, 30);
        AttestationsWrapper spyAttestations = Mockito.spy(attestations);
        Mockito.doReturn(mockResult).when(spyAttestations).getAttestationStat(IDENTIFIER, accounts.get(0));

        AttestationsWrapper.AttestationsStatus result = spyAttestations.getVerifiedStatus(IDENTIFIER, accounts.get(0));
        assertFalse(result.isVerified);
    }

    @Test
    public void testFractionPassThreshold() throws Exception {
        AttestationsWrapper.AttestationStat mockResult = new AttestationsWrapper.AttestationStat(3, 9);
        AttestationsWrapper spyAttestations = Mockito.spy(attestations);
        Mockito.doReturn(mockResult).when(spyAttestations).getAttestationStat(IDENTIFIER, accounts.get(0));

        AttestationsWrapper.AttestationsStatus result = spyAttestations.getVerifiedStatus(IDENTIFIER, accounts.get(0));
        assertTrue(result.isVerified);
    }
}
