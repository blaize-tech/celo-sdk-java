package org.celo.contractkit;

import org.celo.contractkit.wrapper.SortedOraclesWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.celo.contractkit.ContractKitOptions.GANACHE_OPTIONS;
import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.celo.contractkit.TestData.ORACLES;
import static org.celo.contractkit.TestUtils.random;
import static org.junit.Assert.*;

public class SortedOraclesTest {

    static String ORACLE = "0xb2fd4d29c1390b71b8795ae81196bfd60293adf99f9d32a0aff06288fcdac55f";
    static String ORACLE_ADDRESS = "0x7457d5E02197480Db681D3fdF256c7acA21bDc12";

    ContractKit contractKit;
    SortedOraclesWrapper sortedOracles;

    String stableTokenAddress;
    String[] stableTokenOracles;
    List<String> accounts;

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, GANACHE_OPTIONS);
        for (String key: DERIV_PRIVATE_KEYS) {
            contractKit.addAccount(key);
        }

        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
        sortedOracles = contractKit.contracts.getSortedOracles();
        stableTokenAddress = contractKit.contracts.addressFor(CeloContract.StableToken);
        stableTokenOracles = ORACLES;
    }

    void reportAsOracles(int[] rates, String[] oracles) throws Exception {
        List<BigInteger> localRates = rates != null
                ? Arrays.stream(rates).boxed().map(BigInteger::valueOf).collect(Collectors.toList())
                : Stream.generate(() -> random(1, 10)).limit(oracles.length).collect(Collectors.toList());

        for (int i = 0; i < localRates.size(); i++) {
            String data = sortedOracles.report(stableTokenAddress, localRates.get(i), oracles[i]).encodeFunctionCall();
            EthSendTransaction tx = contractKit.sendTransaction(CeloContract.SortedOracles, data, oracles[i]);
            assertNotNull(tx.getTransactionHash());
        }
    }

    @Test
    public void testShouldBeAbleToReport() throws Exception {
        List<SortedOraclesWrapper.OracleRate> initialRates = sortedOracles.getRates(stableTokenAddress);
        assertFalse(initialRates.isEmpty());

        String data = sortedOracles.report(stableTokenAddress, BigInteger.valueOf(16), ORACLE_ADDRESS).encodeFunctionCall();
        EthSendTransaction tx = contractKit.sendTransaction(CeloContract.SortedOracles, data, ORACLE_ADDRESS);
        assertNotNull(tx.getTransactionHash());

        List<SortedOraclesWrapper.OracleRate> resultingRates = sortedOracles.getRates(stableTokenAddress);
        assertFalse(resultingRates.isEmpty());
        assertNotEquals(initialRates.get(0).rate, resultingRates.get(0).rate);
    }

    @Test
    public void testPassesCorrectLesserAndGreaterKeys() throws Exception {
        BigInteger value = BigInteger.valueOf(16);
        int[] rates = new int[]{ 15, 20, 17 };

        reportAsOracles(rates, stableTokenOracles);

        String expectedLesserKey = stableTokenOracles[0];
        String expectedGreaterKey = stableTokenOracles[2];

        SortedOraclesWrapper.Keys keys = sortedOracles.findLesserAndGreaterKeys(stableTokenAddress, value, ORACLE_ADDRESS);
        assertEquals(expectedGreaterKey.toLowerCase(), keys.greaterKey.toLowerCase());
        assertEquals(expectedLesserKey.toLowerCase(), keys.lesserKey.toLowerCase());
    }
}
