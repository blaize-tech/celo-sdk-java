package org.celo.contractkit;

import org.celo.contractkit.wrapper.AttestationsWrapper;
import org.junit.Before;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static org.celo.contractkit.TestData.PRIVATE_KEY_2;

public class AttestationTest {

    ContractKit contractKit;
    AttestationsWrapper attestations;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        Credentials credentials = Credentials.create(PRIVATE_KEY_2);
        contractKit = new ContractKit(web3j, credentials);
        attestations = contractKit.contracts.getAttestations();
    }
}
