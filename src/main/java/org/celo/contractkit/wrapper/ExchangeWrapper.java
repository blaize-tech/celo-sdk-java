package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Exchange;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

/**
 * Contract that allows to exchange StableToken for GoldToken and vice versa
 * using a Constant Product Market Maker Model
 */
public class ExchangeWrapper extends BaseWrapper<Exchange> {
    public ExchangeWrapper(Exchange contract) {
        super(contract);
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
     * @dev Returns the amount of buyToken a user would get for sellAmount of sellToken
     * @param sellAmount The amount of sellToken the user is selling to the exchange
     * @param sellGold `true` if gold is the sell token
     * @return The corresponding buyToken amount.
     */
    public RemoteFunctionCall<BigInteger> getBuyTokenAmount(BigInteger sellAmount, Boolean sellGold) {
        return contract.getBuyTokenAmount(sellAmount, sellGold);
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
