package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Reserve;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

public class ReserveWrapper extends BaseWrapper<Reserve> {
    public ReserveWrapper(Reserve contract) {
        super(contract);
    }

    public RemoteFunctionCall<byte[]> assetAllocationSymbols(BigInteger param0) {
        return contract.assetAllocationSymbols(param0);
    }

    public RemoteFunctionCall<BigInteger> assetAllocationWeights(byte[] param0) {
        return contract.assetAllocationWeights(param0);
    }

    public RemoteFunctionCall<BigInteger> frozenReserveGoldDays() {
        return contract.frozenReserveGoldDays();
    }

    public RemoteFunctionCall<BigInteger> frozenReserveGoldStartBalance() {
        return contract.frozenReserveGoldStartBalance();
    }

    public RemoteFunctionCall<BigInteger> frozenReserveGoldStartDay() {
        return contract.frozenReserveGoldStartDay();
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOtherReserveAddress(String param0) {
        return contract.isOtherReserveAddress(param0);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<Boolean> isSpender(String param0) {
        return contract.isSpender(param0);
    }

    public RemoteFunctionCall<Boolean> isToken(String param0) {
        return contract.isToken(param0);
    }

    public RemoteFunctionCall<BigInteger> lastSpendingDay() {
        return contract.lastSpendingDay();
    }

    public RemoteFunctionCall<String> otherReserveAddresses(BigInteger param0) {
        return contract.otherReserveAddresses(param0);
    }

    public RemoteFunctionCall<String> owner() {
        return contract.owner();
    }

    public RemoteFunctionCall<String> registry() {
        return contract.registry();
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        return contract.renounceOwnership();
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistry(String registryAddress) {
        return contract.setRegistry(registryAddress);
    }

    public RemoteFunctionCall<BigInteger> spendingLimit() {
        return contract.spendingLimit();
    }

    public RemoteFunctionCall<BigInteger> tobinTax() {
        return contract.tobinTax();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> tobinTaxCache() {
        return contract.tobinTaxCache();
    }

    public RemoteFunctionCall<BigInteger> tobinTaxReserveRatio() {
        return contract.tobinTaxReserveRatio();
    }

    public RemoteFunctionCall<BigInteger> tobinTaxStalenessThreshold() {
        return contract.tobinTaxStalenessThreshold();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        return contract.getVersionNumber();
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger _tobinTaxStalenessThreshold, BigInteger _spendingRatio, BigInteger _frozenGold, BigInteger _frozenDays, List<byte[]> _assetAllocationSymbols, List<BigInteger> _assetAllocationWeights, BigInteger _tobinTax, BigInteger _tobinTaxReserveRatio) {
        return contract.initialize(registryAddress, _tobinTaxStalenessThreshold, _spendingRatio, _frozenGold, _frozenDays, _assetAllocationSymbols, _assetAllocationWeights, _tobinTax, _tobinTaxReserveRatio);
    }

    public RemoteFunctionCall<TransactionReceipt> setTobinTaxStalenessThreshold(BigInteger value) {
        return contract.setTobinTaxStalenessThreshold(value);
    }

    public RemoteFunctionCall<TransactionReceipt> setTobinTax(BigInteger value) {
        return contract.setTobinTax(value);
    }

    public RemoteFunctionCall<TransactionReceipt> setTobinTaxReserveRatio(BigInteger value) {
        return contract.setTobinTaxReserveRatio(value);
    }

    public RemoteFunctionCall<TransactionReceipt> setDailySpendingRatio(BigInteger ratio) {
        return contract.setDailySpendingRatio(ratio);
    }

    public RemoteFunctionCall<BigInteger> getDailySpendingRatio() {
        return contract.getDailySpendingRatio();
    }

    public RemoteFunctionCall<TransactionReceipt> setFrozenGold(BigInteger frozenGold, BigInteger frozenDays) {
        return contract.setFrozenGold(frozenGold, frozenDays);
    }

    public RemoteFunctionCall<TransactionReceipt> setAssetAllocations(List<byte[]> symbols, List<BigInteger> weights) {
        return contract.setAssetAllocations(symbols, weights);
    }

    public RemoteFunctionCall<TransactionReceipt> addToken(String token) {
        return contract.addToken(token);
    }

    public RemoteFunctionCall<TransactionReceipt> removeToken(String token, BigInteger index) {
        return contract.removeToken(token, index);
    }

    public RemoteFunctionCall<TransactionReceipt> addOtherReserveAddress(String reserveAddress) {
        return contract.addOtherReserveAddress(reserveAddress);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOtherReserveAddress(String reserveAddress, BigInteger index) {
        return contract.removeOtherReserveAddress(reserveAddress, index);
    }

    public RemoteFunctionCall<TransactionReceipt> addSpender(String spender) {
        return contract.addSpender(spender);
    }

    public RemoteFunctionCall<TransactionReceipt> removeSpender(String spender) {
        return contract.removeSpender(spender);
    }

    public RemoteFunctionCall<TransactionReceipt> transferGold(String to, BigInteger value) {
        return contract.transferGold(to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> transferExchangeGold(String to, BigInteger value) {
        return contract.transferExchangeGold(to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> getOrComputeTobinTax() {
        return contract.getOrComputeTobinTax();
    }

    public RemoteFunctionCall<List> getTokens() {
        return contract.getTokens();
    }

    public RemoteFunctionCall<List> getOtherReserveAddresses() {
        return contract.getOtherReserveAddresses();
    }

    public RemoteFunctionCall<List> getAssetAllocationSymbols() {
        return contract.getAssetAllocationSymbols();
    }

    public RemoteFunctionCall<List> getAssetAllocationWeights() {
        return contract.getAssetAllocationWeights();
    }

    public RemoteFunctionCall<BigInteger> getUnfrozenBalance() {
        return contract.getUnfrozenBalance();
    }

    public RemoteFunctionCall<BigInteger> getReserveGoldBalance() {
        return contract.getReserveGoldBalance();
    }

    public RemoteFunctionCall<BigInteger> getOtherReserveAddressesGoldBalance() {
        return contract.getOtherReserveAddressesGoldBalance();
    }

    public RemoteFunctionCall<BigInteger> getUnfrozenReserveGoldBalance() {
        return contract.getUnfrozenReserveGoldBalance();
    }

    public RemoteFunctionCall<BigInteger> getFrozenReserveGoldBalance() {
        return contract.getFrozenReserveGoldBalance();
    }

    public RemoteFunctionCall<BigInteger> getReserveRatio() {
        return contract.getReserveRatio();
    }
}
