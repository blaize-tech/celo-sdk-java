package org.celo.contractkit;

import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WrapperCacheTest {
    WrapperCache wrapperCache;
    List<String> accounts;

    BigDecimal toEther(BigInteger value) {
        return Convert.fromWei(new BigDecimal(value), Convert.Unit.ETHER);
    }

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        ContractKit contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);

        wrapperCache = contractKit.contracts;
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testGetContractAddress() throws Exception {
        GoldTokenWrapper goldToken = wrapperCache.getGoldToken();

        assertEquals(BigInteger.valueOf(18), goldToken.decimals().send());
        assertEquals("Celo Gold", goldToken.name().send());
        assertEquals("cGLD", goldToken.symbol().send());
        assertNotNull(goldToken.totalSupply().send());
        assertEquals(1, toEther(goldToken.balanceOf(accounts.get(0))).compareTo(BigDecimal.valueOf(0.1)));
    }
}
