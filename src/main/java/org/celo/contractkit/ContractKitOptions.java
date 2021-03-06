package org.celo.contractkit;

import java.math.BigInteger;

public class ContractKitOptions {
    private static final double DEFAULT_GAS_INFLATION_FACTOR = 1.3;
    private static final double DEFAULT_GAS_PRICE_SUGGESTION_MULTIPLIER = 5;
    /** Gas price is 0 means the node will compute gasPrice on its own */
    private static final BigInteger DEFAULT_PRICE = BigInteger.ZERO;
    public static final long CHAIN_ID = 44787;
    public static final long GANACHE_CHAIN_ID = 1337;

    public double gasInflationFactor;
    // TODO: remove once cUSD gasPrice is available on minimumClientVersion node rpc
    public double gasPriceSuggestionMultiplier;
    public BigInteger gasPrice;
    public CeloContract feeCurrency;
    public String from;
    public long chainId;

    public ContractKitOptions(double gasInflationFactor, double gasPriceSuggestionMultiplier, BigInteger gasPrice, CeloContract feeCurrency, String from, long chainId) {
        this.gasInflationFactor = gasInflationFactor;
        this.gasPriceSuggestionMultiplier = gasPriceSuggestionMultiplier;
        this.gasPrice = gasPrice;
        this.feeCurrency = feeCurrency;
        this.from = from;
        this.chainId = chainId;
    }

    public static class Builder {
        private double gasInflationFactor = DEFAULT_GAS_INFLATION_FACTOR;
        private double gasPriceSuggestionMultiplier = DEFAULT_GAS_PRICE_SUGGESTION_MULTIPLIER;
        private BigInteger gasPrice = DEFAULT_PRICE;
        private CeloContract feeCurrency = CeloContract.StableToken;
        private String from;
        private long chainId = CHAIN_ID;

        public Builder setGasInflationFactor(double gasInflationFactor) {
            this.gasInflationFactor = gasInflationFactor;
            return this;
        }

        public Builder setGasPriceSuggestionMultiplier(double gasPriceSuggestionMultiplier) {
            this.gasPriceSuggestionMultiplier = gasPriceSuggestionMultiplier;
            return this;
        }

        public Builder setGasPrice(BigInteger gasPrice) {
            this.gasPrice = gasPrice;
            return this;
        }

        public Builder setFeeCurrency(CeloContract feeCurrency) {
            this.feeCurrency = feeCurrency != CeloContract.GoldToken ? feeCurrency : null;
            return this;
        }

        public Builder setFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder setChainId(long chainId) {
            this.chainId = chainId;
            return this;
        }

        public ContractKitOptions build() {
            return new ContractKitOptions(gasInflationFactor, gasPriceSuggestionMultiplier, gasPrice, feeCurrency, from, chainId);
        }
    }

    public static final ContractKitOptions DEFAULT = new Builder()
            .setGasInflationFactor(DEFAULT_GAS_INFLATION_FACTOR)
            .setGasPriceSuggestionMultiplier(DEFAULT_GAS_PRICE_SUGGESTION_MULTIPLIER)
            .setGasPrice(DEFAULT_PRICE)
            .setFeeCurrency(CeloContract.StableToken)
            .setChainId(CHAIN_ID)
            .build();

    public static final ContractKitOptions GANACHE_OPTIONS = new Builder()
            .setGasInflationFactor(DEFAULT_GAS_INFLATION_FACTOR)
            .setGasPriceSuggestionMultiplier(DEFAULT_GAS_PRICE_SUGGESTION_MULTIPLIER)
            .setGasPrice(DEFAULT_PRICE)
            .setFeeCurrency(CeloContract.StableToken)
            .setChainId(GANACHE_CHAIN_ID)
            .build();
}
