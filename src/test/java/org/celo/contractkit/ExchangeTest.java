package org.celo.contractkit;

import org.celo.contractkit.wrapper.ExchangeWrapper;
import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.celo.contractkit.wrapper.StableTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.celo.contractkit.TestUtils.ONE_ETHER;
import static org.celo.contractkit.TestUtils.ONE_GWEI;
import static org.junit.Assert.*;

public class ExchangeTest {
    ContractKit contractKit;
    ExchangeWrapper exchange;
    List<String> accounts;

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[6]);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[1]);
        exchange = contractKit.contracts.getExchange();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();
    }

    @Test
    public void testCheckBuckets() throws Exception {
        Tuple2<BigInteger, BigInteger> amountOfcUsd = exchange.getBuyAndSellBuckets(true).send();

        TestUtils.assertIsPositive(amountOfcUsd.component1());
        TestUtils.assertIsPositive(amountOfcUsd.component2());
    }

    @Test
    public void testBuySellQuotes() throws Exception {
        BigInteger quoteUsdSell = exchange.quoteUsdSell(ONE_GWEI).send();
        TestUtils.assertIsPositive(quoteUsdSell);

        BigInteger quoteGoldSell = exchange.quoteGoldSell(ONE_GWEI).send();
        TestUtils.assertIsPositive(quoteGoldSell);

        BigInteger quoteUsdBuy = exchange.quoteUsdBuy(ONE_GWEI).send();
        TestUtils.assertIsPositive(quoteUsdBuy);

        BigInteger quoteGoldBuy = exchange.quoteGoldBuy(ONE_GWEI).send();
        TestUtils.assertIsPositive(quoteGoldBuy);
    }

    @Test
    public void testSellDollar() throws Exception {
        BigInteger goldAmount = exchange.quoteUsdSell(ONE_GWEI).send();

        StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
        // Prepare some test cUSD balance
        String transferTxData = stableToken.transfer(accounts.get(6), ONE_GWEI).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.StableToken, transferTxData, accounts.get(1));

        TransactionReceipt approveTx = stableToken.approve(exchange.getContractAddress(), ONE_GWEI).send();
        assertTrue(approveTx.isStatusOK());
        assertNotNull(approveTx.getTransactionHash());

        TransactionReceipt sellTx = exchange.sellDollar(ONE_GWEI, goldAmount).send();
        assertTrue(sellTx.isStatusOK());
        assertNotNull(sellTx.getTransactionHash());
    }

    @Test
    public void testSellGold() throws Exception {
        BigInteger usdAmount = exchange.quoteGoldSell(ONE_GWEI).send();

        GoldTokenWrapper goldToken = contractKit.contracts.getGoldToken();
        TransactionReceipt approveTx = goldToken.approve(exchange.getContractAddress(), ONE_GWEI).send();
        assertTrue(approveTx.isStatusOK());
        assertNotNull(approveTx.getTransactionHash());

        TransactionReceipt sellTx = exchange.sellGold(ONE_GWEI, usdAmount).send();
        assertTrue(sellTx.isStatusOK());
        assertNotNull(sellTx.getTransactionHash());
    }

    @Test
    public void testBuyingAllTheCELOICan() throws Exception {
        String account = accounts.get(6);

        ExchangeWrapper exchange = contractKit.contracts.getExchange();
        StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
        GoldTokenWrapper goldToken = contractKit.contracts.getGoldToken();

        // Prepare some test cUSD balance
        String transferTxData = stableToken.transfer(account, ONE_ETHER).encodeFunctionCall();
        contractKit.sendTransaction(CeloContract.StableToken, transferTxData, accounts.get(1));

        // Check initial balances
        BigInteger cUsdBalance = stableToken.balanceOf(account).send();
        assertEquals(ONE_ETHER, cUsdBalance);
        BigInteger goldTokenBalance = goldToken.balanceOf(account);
        TestUtils.assertIsPositive(goldTokenBalance);

        // Approve amount
        TransactionReceipt approveTx = stableToken.approve(exchange.getContractAddress(), cUsdBalance).send();
        String approveTxHash = approveTx.getTransactionHash();
        assertNotNull(approveTxHash);

        // Get quote and sell dollar
        BigInteger goldAmount = exchange.quoteUsdSell(cUsdBalance).send();
        TransactionReceipt sellTx = exchange.sellDollar(cUsdBalance, goldAmount).send();
        String sellTxHash = sellTx.getTransactionHash();
        assertNotNull(sellTxHash);

        // Check final balance after exchange
        BigInteger finalCUsdBalance = stableToken.balanceOf(account).send();
        assertEquals(BigInteger.ZERO, finalCUsdBalance); // All used used
        BigInteger finalGoldBalance = goldToken.balanceOf(account);
        assertEquals(1, finalGoldBalance.compareTo(goldTokenBalance));
    }
}