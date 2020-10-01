package org.celo.contractkit;

import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.celo.contractkit.TestData.PUBLIC_KEY_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WrapperCacheTest {
    WrapperCache wrapperCache;

    BigDecimal toEther(BigInteger value) {
        return Convert.fromWei(new BigDecimal(value), Convert.Unit.ETHER);
    }

    @Before
    public void initialize() {
        ContractKit contractKit = ContractKit.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        wrapperCache = contractKit.contracts;
    }

    @Test
    public void testGetContractAddress() throws Exception {
        GoldTokenWrapper goldToken = wrapperCache.getGoldToken();

        assertEquals(BigInteger.valueOf(18), goldToken.decimals().send());
        assertEquals("Celo Gold", goldToken.name().send());
        assertEquals("cGLD", goldToken.symbol().send());
        assertNotNull(goldToken.totalSupply().send());
        assertEquals(1, toEther(goldToken.balanceOf(PUBLIC_KEY_1)).compareTo(BigDecimal.valueOf(0.1)));
    }
}
