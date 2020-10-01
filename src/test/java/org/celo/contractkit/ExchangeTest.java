package org.celo.contractkit;

import org.celo.contractkit.wrapper.ExchangeWrapper;
import org.celo.contractkit.wrapper.GoldTokenWrapper;
import org.celo.contractkit.wrapper.StableTokenWrapper;
import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

import static org.celo.contractkit.TestData.PRIVATE_KEY_2;
import static org.celo.contractkit.TestUtils.ONE_GWEI;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExchangeTest {
    ContractKit contractKit;
    ExchangeWrapper exchange;

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));
        Credentials credentials = Credentials.create(PRIVATE_KEY_2);
        contractKit = new ContractKit(web3j, credentials);
        exchange = contractKit.contracts.getExchange();
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
}
