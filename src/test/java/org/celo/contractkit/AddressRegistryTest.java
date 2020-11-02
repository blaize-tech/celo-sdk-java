package org.celo.contractkit;

import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static org.junit.Assert.assertEquals;

public class AddressRegistryTest {

    WrapperCache contracts;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        ContractKit contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contracts = contractKit.contracts;
    }

    @Test
    public void testGetContractAddress() {
        String address = contracts.addressFor(CeloContract.GoldToken);

        System.out.println(address);
        assertEquals(42, address.length());
    }
}
