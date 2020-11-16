package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Exchange;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

/**
 * Contract that allows to exchange StableToken for GoldToken and vice versa
 * using a Constant Product Market Maker Model
 */
public class ExchangeWrapper extends BaseWrapper<Exchange> {

    public ExchangeWrapper(Exchange contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static ExchangeWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        Exchange contract = Exchange.load(contractAddress, web3j, transactionManager, gasProvider);
        return new ExchangeWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<Exchange> deploy() {
        return Exchange.deploy(web3j, transactionManager, gasProvider);
    }

    /**
     * Query last bucket update
     * @return The timestamp of the last time exchange buckets were updated.
     */
    public RemoteFunctionCall<BigInteger> lastBucketUpdate() {
        return contract.lastBucketUpdate();
    }

    /**
     * Query minimum reports parameter
     * @return The minimum number of fresh reports that need to be
     * present in the oracle to update buckets
     * commit to the gold bucket
     */
    public RemoteFunctionCall<BigInteger> minimumReports() {
        return contract.minimumReports();
    }

    /**
     * Query reserve fraction parameter
     * @return Current fraction to commit to the gold bucket
     */
    public RemoteFunctionCall<BigInteger> reserveFraction() {
        return contract.reserveFraction();
    }

    /**
     * Query spread parameter
     * @return Current spread charged on exchanges
     */
    public RemoteFunctionCall<BigInteger> spread() {
        return contract.spread();
    }

    /**
     * Query update frequency parameter
     * @return The time period that needs to elapse between bucket
     * updates
     */
    public RemoteFunctionCall<BigInteger> updateFrequency() {
        return contract.updateFrequency();
    }

    /**
     * Exchanges sellAmount of sellToken in exchange for at least minBuyAmount of buyToken
     * Requires the sellAmount to have been approved to the exchange
     * @param sellAmount The amount of sellToken the user is selling to the exchange
     * @param minBuyAmount The minimum amount of buyToken the user has to receive for this
     * transaction to succeed
     * @param sellGold `true` if gold is the sell token
     * @return The amount of buyToken that was transfered
     */
    public RemoteFunctionCall<TransactionReceipt> exchange(BigInteger sellAmount, BigInteger minBuyAmount, Boolean sellGold) {
        return contract.exchange(sellAmount, minBuyAmount, sellGold);
    }

    /**
     * Exchanges amount of CELO in exchange for at least minUsdAmount of cUsd
     * Requires the amount to have been approved to the exchange
     * @param amount The amount of CELO the user is selling to the exchange
     * @param minUSDAmount The minimum amount of cUsd the user has to receive for this
     * transaction to succeed
     */
    public RemoteFunctionCall<TransactionReceipt> sellGold(BigInteger amount, BigInteger minUSDAmount) {
        return this.exchange(amount, minUSDAmount, true);
    }

    /**
     * Exchanges amount of cUsd in exchange for at least minGoldAmount of CELO
     * Requires the amount to have been approved to the exchange
     * @param amount The amount of cUsd the user is selling to the exchange
     * @param minGoldAmount The minimum amount of CELO the user has to receive for this
     * transaction to succeed
     */
    public RemoteFunctionCall<TransactionReceipt> sellDollar(BigInteger amount, BigInteger minGoldAmount) {
        return this.exchange(amount, minGoldAmount, false);
    }

    /**
     * @dev Returns the amount of buyToken a user would get for sellAmount of sellToken
     * @param sellAmount The amount of sellToken the user is selling to the exchange
     * @param sellGold `true` if gold is the sell token
     * @return The corresponding buyToken amount.
     */
    public RemoteFunctionCall<BigInteger> getBuyTokenAmount(BigInteger sellAmount, Boolean sellGold) {
        return contract.getBuyTokenAmount(sellAmount, sellGold);
    }

    /**
     * Returns the amount of CELO a user would get for sellAmount of cUsd
     * @param sellAmount The amount of cUsd the user is selling to the exchange
     * @return The corresponding CELO amount.
     */
    public RemoteFunctionCall<BigInteger> quoteUsdSell(BigInteger sellAmount) {
        return this.getBuyTokenAmount(sellAmount, false);
    }

    /**
     * Returns the amount of cUsd a user would get for sellAmount of CELO
     * @param sellAmount The amount of CELO the user is selling to the exchange
     * @return The corresponding cUsd amount.
     */
    public RemoteFunctionCall<BigInteger> quoteGoldSell(BigInteger sellAmount) {
        return this.getBuyTokenAmount(sellAmount, true);
    }

    /**
     * Returns the amount of CELO a user would need to exchange to receive buyAmount of
     * cUsd.
     * @param buyAmount The amount of cUsd the user would like to purchase.
     * @return The corresponding CELO amount.
     */
    public RemoteFunctionCall<BigInteger> quoteUsdBuy(BigInteger buyAmount) {
        return this.getBuyTokenAmount(buyAmount, false);
    }

    /**
     * Returns the amount of cUsd a user would need to exchange to receive buyAmount of
     * CELO.
     * @param buyAmount The amount of CELO the user would like to purchase.
     * @return The corresponding cUsd amount.
     */
    public RemoteFunctionCall<BigInteger> quoteGoldBuy(BigInteger buyAmount) {
        return this.getBuyTokenAmount(buyAmount, true);
    }

    /**
     * Returns the amount of sellToken a user would need to exchange to receive buyAmount of
     * buyToken.
     * @param buyAmount The amount of buyToken the user would like to purchase.
     * @param sellGold `true` if gold is the sell token
     * @return The corresponding sellToken amount.
     */
    public RemoteFunctionCall<BigInteger> getSellTokenAmount(BigInteger buyAmount, Boolean sellGold) {
        return contract.getSellTokenAmount(buyAmount, sellGold);
    }

    /**
     * Returns the buy token and sell token bucket sizes, in order. The ratio of
     * the two also represents the exchange rate between the two.
     * @param sellGold `true` if gold is the sell token
     * @return [buyTokenBucket, sellTokenBucket]
     */
    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getBuyAndSellBuckets(Boolean sellGold) {
        return contract.getBuyAndSellBuckets(sellGold);
    }

    public BigInteger getExchangeRate(BigInteger buyAmount, Boolean sellGold) throws Exception {
        BigInteger takerAmount = contract.getBuyTokenAmount(buyAmount, sellGold).send();
        return valueToFrac(buyAmount, takerAmount);  // Number of sellTokens received for one buyToken
    }

    /**
     * Returns the exchange rate for cUsd estimated at the buyAmount
     * @param buyAmount The amount of cUsd in wei to estimate the exchange rate at
     * @return The exchange rate (number of CELO received for one cUsd)
     */
    public BigInteger getUsdExchangeRate(BigInteger buyAmount) throws Exception {
        return getExchangeRate(buyAmount, false);
    }

    /**
     * Returns the exchange rate for CELO estimated at the buyAmount
     * @param buyAmount The amount of CELO in wei to estimate the exchange rate at
     * @return The exchange rate (number of cUsd received for one CELO)
     */
    public BigInteger getGoldExchangeRate(BigInteger buyAmount) throws Exception {
        return getExchangeRate(buyAmount, true);
    }
}
