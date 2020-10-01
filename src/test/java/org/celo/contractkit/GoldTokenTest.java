package org.celo.contractkit;

import org.celo.contractkit.contract.GoldToken;
import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static org.celo.contractkit.TestData.*;
import static org.celo.contractkit.TestUtils.ONE_GWEI;
import static org.junit.Assert.*;

public class GoldTokenTest {

    ContractKit contractKit;
    GoldTokenWrapper goldToken;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        Credentials credentials = Credentials.create(PRIVATE_KEY_1);
        contractKit = new ContractKit(web3j, credentials);
        goldToken = contractKit.contracts.getGoldToken();
    }

    @Test
    public void testGetContractAddress() throws Exception {
        assertEquals(BigInteger.valueOf(18), goldToken.decimals().send());
        assertEquals("Celo Gold", goldToken.name().send());
        assertEquals("cGLD", goldToken.symbol().send());
        assertEquals("0xaa963fc97281d9632d96700ab62a4d1340f9a28a", goldToken.owner().send());
        TestUtils.assertIsPositive(goldToken.totalSupply().send());
        TestUtils.assertIsPositive(goldToken.balanceOf(PUBLIC_KEY_1));
    }

    @Test
    public void testTransfer() throws Exception {
        BigInteger initialBalance = goldToken.balanceOf(PUBLIC_KEY_2);

        TransactionReceipt tx = goldToken.transfer(PUBLIC_KEY_2, ONE_GWEI).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());

        BigInteger finalBalance = goldToken.balanceOf(PUBLIC_KEY_2);
        assertEquals(initialBalance.add(ONE_GWEI), finalBalance);
    }

    @Test
    public void testTransferFrom() throws Exception {
        BigInteger initialBalance = goldToken.balanceOf(PUBLIC_KEY_2);

        TransactionReceipt increaseTxHash = goldToken.increaseAllowance(contractKit.credentials.getAddress(), ONE_GWEI).send();
        assertTrue(increaseTxHash.isStatusOK());
        assertNotNull(increaseTxHash.getTransactionHash());

        TransactionReceipt tx = goldToken.transferFrom(PUBLIC_KEY_1, PUBLIC_KEY_2, ONE_GWEI).send();
        assertTrue(tx.isStatusOK());
        assertNotNull(tx.getTransactionHash());

        BigInteger finalBalance = goldToken.balanceOf(PUBLIC_KEY_2);
        assertEquals(initialBalance.add(ONE_GWEI), finalBalance);
    }
}
