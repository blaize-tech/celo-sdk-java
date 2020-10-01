package org.celo.contractkit;

import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class TestUtils {
    public static final BigInteger ONE_GWEI = Convert.toWei(BigDecimal.ONE, Convert.Unit.GWEI).toBigInteger();

    public static void assertIsPositive(BigInteger val) {
        assertNotNull(val);
        assertTrue("Value must be positive", val.compareTo(BigInteger.ZERO) > 0);
    }
}
