package org.celo.contractkit;

import org.web3j.crypto.Hash;

import java.nio.charset.StandardCharsets;

public class Utils {
    /**
     * Hashes solidity values to a sha3 hash using keccak 256
     * TODO check web3j methods
     */
    public static byte[] soliditySha3(String value) {
        return Hash.sha3(value.getBytes(StandardCharsets.UTF_8));
    }
}
