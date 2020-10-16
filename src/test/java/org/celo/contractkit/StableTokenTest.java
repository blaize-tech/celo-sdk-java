package org.celo.contractkit;

import org.celo.contractkit.wrapper.StableTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static org.celo.contractkit.TestData.*;
import static org.celo.contractkit.TestUtils.ONE_GWEI;
import static org.junit.Assert.*;

public class StableTokenTest {

    ContractKit contractKit;
    StableTokenWrapper stableToken;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        contractKit = new ContractKit(web3j);
        contractKit.addAccount(PRIVATE_KEY_1);
        stableToken = contractKit.contracts.getStableToken();
    }

    @Test
    public void testGetContractAddress() throws Exception {
        assertEquals(BigInteger.valueOf(18), stableToken.decimals().send());
        assertEquals("Celo Dollar", stableToken.name().send());
        assertEquals("cUSD", stableToken.symbol().send());
        assertNotNull(stableToken.getInflationParameters().send());
        assertEquals("0xaa963fc97281d9632d96700ab62a4d1340f9a28a", stableToken.owner().send());
        TestUtils.assertIsPositive(stableToken.totalSupply().send());
        TestUtils.assertIsPositive(stableToken.balanceOf(PUBLIC_KEY_1).send());
    }

    @Test
    public void testTransfer() throws Exception {
        BigInteger initialBalance = stableToken.balanceOf(PUBLIC_KEY_2).send();

        TransactionReceipt tx = stableToken.transfer(PUBLIC_KEY_2, ONE_GWEI).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());

        BigInteger finalBalance = stableToken.balanceOf(PUBLIC_KEY_2).send();
        assertEquals(initialBalance.add(ONE_GWEI), finalBalance);
    }

    @Test
    public void testTransferFrom() throws Exception {
        BigInteger initialBalance = stableToken.balanceOf(PUBLIC_KEY_2).send();

        TransactionReceipt increaseTxHash = stableToken.increaseAllowance(contractKit.getAddress(), ONE_GWEI).send();
        assertTrue(increaseTxHash.isStatusOK());
        assertNotNull(increaseTxHash.getTransactionHash());

        TransactionReceipt tx = stableToken.transferFrom(PUBLIC_KEY_1, PUBLIC_KEY_2, ONE_GWEI).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());

        BigInteger finalBalance = stableToken.balanceOf(PUBLIC_KEY_2).send();
        assertEquals(finalBalance, initialBalance.add(ONE_GWEI));
    }
}
