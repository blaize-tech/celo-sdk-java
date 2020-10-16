package org.celo.contractkit;

import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.*;

public class TestUtils {
    public static final BigInteger ONE_GWEI = Convert.toWei(BigDecimal.ONE, Convert.Unit.GWEI).toBigInteger();

    public static void assertIsPositive(BigInteger val) {
        assertNotNull(val);
        assertTrue("Value must be positive", val.compareTo(BigInteger.ZERO) > 0);
    }

    public static BigInteger random(int min, int max) {
        return BigInteger.valueOf(new Random().nextInt((max - min) + 1) + min);
    }
}
