package org.celo.contractkit.protocol;

import org.celo.contractkit.contract.GasPriceMinimum;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CeloGasProvider extends StaticGasProvider {

    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(10_000_000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(100_000_000);

    private final GasPriceMinimum gasPriceMinimum;

    private String feeCurrency;
    private double gasPriceSuggestionMultiplier;

    public CeloGasProvider(GasPriceMinimum gasPriceMinimum, String feeCurrency) {
        super(GAS_PRICE, GAS_LIMIT);

        this.gasPriceMinimum = gasPriceMinimum;
        this.feeCurrency = feeCurrency;
        //TODO add params
        this.gasPriceSuggestionMultiplier = 5;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public void setGasPriceSuggestionMultiplier(double gasPriceSuggestionMultiplier) {
        this.gasPriceSuggestionMultiplier = gasPriceSuggestionMultiplier;
    }

    public BigInteger getGasPriceMinimum(String feeCurrency, BigInteger defaultGasPrice) throws Exception {
        if (gasPriceMinimum != null && feeCurrency != null && !feeCurrency.isEmpty()) {
            BigInteger minimalGasPrice = gasPriceMinimum.getGasPriceMinimum(feeCurrency).send();
            return BigDecimal.valueOf(minimalGasPrice.doubleValue() * gasPriceSuggestionMultiplier).toBigInteger();
        }
        return defaultGasPrice;
    }

    @Override
    public BigInteger getGasPrice(String contractFunc) {
        return getGasPrice();
    }

    @Override
    public BigInteger getGasPrice() {
        try {
            return getGasPriceMinimum(feeCurrency, GAS_PRICE);
        } catch (Exception e) {
            return GAS_PRICE;
        }
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        return GAS_LIMIT;
    }

    @Override
    public BigInteger getGasLimit() {
        return GAS_LIMIT;
    }
}
