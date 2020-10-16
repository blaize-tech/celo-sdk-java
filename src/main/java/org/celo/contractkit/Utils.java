package org.celo.contractkit;

import org.web3j.crypto.Hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class Utils {

    public static String PHONE_SALT_SEPARATOR = "__";
    public static Pattern E164_REGEX = Pattern.compile("\\+[1-9][0-9]{1,14}");

    /**
     * Hashes solidity values to a sha3 hash using keccak 256
     * TODO check web3j methods
     */
    public static byte[] soliditySha3(String value) {
        return Hash.sha3(value.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getPhoneHash(String phoneNumber, String salt) {
        if (phoneNumber == null || !E164_REGEX.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Attempting to hash a non-e164 number");
        }
        String value = "tel://" + (salt != null ? phoneNumber + PHONE_SALT_SEPARATOR + salt : phoneNumber);
        return soliditySha3(value);
    }

    public static class Fixidity {
        public static final BigInteger DIGITS = BigInteger.valueOf(24);
        public static final BigInteger FIXED1 =  new BigInteger("1000000000000000000000000");

        public static BigInteger toFixed(BigInteger n) {
            return FIXED1.multiply(n);
        }

        // Keeps the decimal portion
        public static BigInteger fromFixed(BigInteger f) {
            return f.divide(FIXED1);
        }

        // Returns an integer
        public static BigInteger fixedToInt(BigInteger f) {
            return f.divide(FIXED1);
        }

        public static BigInteger multiply(BigInteger a, BigInteger b) {
            return a.multiply(b).divide(FIXED1);
        }
    }
}
