package org.celo.contractkit;

import org.celo.contractkit.wrapper.ExchangeWrapper;
import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.celo.contractkit.wrapper.StableTokenWrapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.celo.contractkit.TestUtils.ONE_GWEI;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExchangeTest {
    ContractKit contractKit;
    ExchangeWrapper exchange;
    List<String> accounts;

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);
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
        BigInteger quoteUsdSell = exchange.getBuyTokenAmount(ONE_GWEI, false).send();
        TestUtils.assertIsPositive(quoteUsdSell);

        BigInteger quoteGoldSell = exchange.getBuyTokenAmount(ONE_GWEI, true).send();
        TestUtils.assertIsPositive(quoteGoldSell);

        BigInteger quoteUsdBuy = exchange.getSellTokenAmount(ONE_GWEI, false).send();
        TestUtils.assertIsPositive(quoteUsdBuy);

        BigInteger quoteGoldBuy = exchange.getSellTokenAmount(ONE_GWEI, true).send();
        TestUtils.assertIsPositive(quoteGoldBuy);
    }

    @Test
    public void testSellDollar() throws Exception {
        BigInteger goldAmount = exchange.getBuyTokenAmount(ONE_GWEI, false).send();

        StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
        TransactionReceipt approveTx = stableToken.approve(exchange.getContract().getContractAddress(), ONE_GWEI).send();
        assertTrue(approveTx.isStatusOK());
        assertNotNull(approveTx.getTransactionHash());

        TransactionReceipt sellTx = exchange.exchange(ONE_GWEI, goldAmount, false).send();
        assertTrue(sellTx.isStatusOK());
        assertNotNull(sellTx.getTransactionHash());
    }

    @Test
    public void testSellGold() throws Exception {
        BigInteger usdAmount = exchange.getBuyTokenAmount(ONE_GWEI, true).send();

        GoldTokenWrapper goldToken = contractKit.contracts.getGoldToken();
        TransactionReceipt approveTx = goldToken.approve(exchange.getContract().getContractAddress(), ONE_GWEI).send();
        assertTrue(approveTx.isStatusOK());
        assertNotNull(approveTx.getTransactionHash());

        TransactionReceipt sellTx = exchange.exchange(ONE_GWEI, usdAmount, true).send();
        assertTrue(sellTx.isStatusOK());
        assertNotNull(sellTx.getTransactionHash());
    }

    @Test
    @Ignore("Only manual run")
    public void testBuyingAllTheCELOICan() throws Exception {
        ExchangeWrapper exchange = contractKit.contracts.getExchange();
        StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
        GoldTokenWrapper goldToken = contractKit.contracts.getGoldToken();

        BigInteger cUsdBalance = stableToken.balanceOf(accounts.get(0)).send();
        //BigInteger cUsdBalance = ONE_ETHER;

        TransactionReceipt approveTx = goldToken.approve(exchange.getContractAddress(), cUsdBalance).send();
        String approveTxHash = approveTx.getTransactionHash();
        assertNotNull(approveTxHash);

        BigInteger goldAmount = exchange.getBuyTokenAmount(cUsdBalance, false).send();
        TransactionReceipt sellTx = exchange.exchange(cUsdBalance, goldAmount, true).send();
        String sellTxHash = sellTx.getTransactionHash();
        assertNotNull(sellTxHash);
    }
}
