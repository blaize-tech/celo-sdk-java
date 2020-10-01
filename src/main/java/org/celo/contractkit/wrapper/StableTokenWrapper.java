package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.StableToken;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

public class StableTokenWrapper extends BaseWrapper<StableToken> {
    public StableTokenWrapper(StableToken contract) {
        super(contract);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        return contract.checkProofOfPossession(sender, blsKey, blsPop);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> fractionMulExp(BigInteger aNumerator, BigInteger aDenominator, BigInteger bNumerator, BigInteger bDenominator, BigInteger exponent, BigInteger _decimals) {
        return contract.fractionMulExp(aNumerator, aDenominator, bNumerator, bDenominator, exponent, _decimals);
    }

    public RemoteFunctionCall<BigInteger> getBlockNumberFromHeader(byte[] header) {
        return contract.getBlockNumberFromHeader(header);
    }

    public RemoteFunctionCall<BigInteger> getEpochNumber() {
        return contract.getEpochNumber();
    }

    public RemoteFunctionCall<BigInteger> getEpochNumberOfBlock(BigInteger blockNumber) {
        return contract.getEpochNumberOfBlock(blockNumber);
    }

    public RemoteFunctionCall<BigInteger> getEpochSize() {
        return contract.getEpochSize();
    }

    public RemoteFunctionCall<byte[]> getParentSealBitmap(BigInteger blockNumber) {
        return contract.getParentSealBitmap(blockNumber);
    }

    public RemoteFunctionCall<byte[]> getVerifiedSealBitmapFromHeader(byte[] header) {
        return contract.getVerifiedSealBitmapFromHeader(header);
    }

    public RemoteFunctionCall<byte[]> hashHeader(byte[] header) {
        return contract.hashHeader(header);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<BigInteger> minQuorumSize(BigInteger blockNumber) {
        return contract.minQuorumSize(blockNumber);
    }

    public RemoteFunctionCall<BigInteger> minQuorumSizeInCurrentSet() {
        return contract.minQuorumSizeInCurrentSet();
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInCurrentSet() {
        return contract.numberValidatorsInCurrentSet();
    }

    public RemoteFunctionCall<BigInteger> numberValidatorsInSet(BigInteger blockNumber) {
        return contract.numberValidatorsInSet(blockNumber);
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

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<String> validatorSignerAddressFromCurrentSet(BigInteger index) {
        return contract.validatorSignerAddressFromCurrentSet(index);
    }

    public RemoteFunctionCall<String> validatorSignerAddressFromSet(BigInteger index, BigInteger blockNumber) {
        return contract.validatorSignerAddressFromSet(index, blockNumber);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getVersionNumber() {
        return contract.getVersionNumber();
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String _name, String _symbol, BigInteger _decimals, String registryAddress, BigInteger inflationRate, BigInteger inflationFactorUpdatePeriod, List<String> initialBalanceAddresses, List<BigInteger> initialBalanceValues) {
        return contract.initialize(_name, _symbol, _decimals, registryAddress, inflationRate, inflationFactorUpdatePeriod, initialBalanceAddresses, initialBalanceValues);
    }

    public RemoteFunctionCall<TransactionReceipt> setInflationParameters(BigInteger rate, BigInteger updatePeriod) {
        return contract.setInflationParameters(rate, updatePeriod);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger value) {
        return contract.increaseAllowance(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger value) {
        return contract.decreaseAllowance(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        return contract.approve(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String to, BigInteger value) {
        return contract.mint(to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> transferWithComment(String to, BigInteger value, String comment) {
        return contract.transferWithComment(to, value, comment);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger value) {
        return contract.burn(value);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger value) {
        return contract.transferFrom(from, to, value);
    }

    public RemoteFunctionCall<String> name() {
        return contract.name();
    }

    public RemoteFunctionCall<String> symbol() {
        return contract.symbol();
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        return contract.decimals();
    }

    public RemoteFunctionCall<BigInteger> allowance(String accountOwner, String spender) {
        return contract.allowance(accountOwner, spender);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String accountOwner) {
        return contract.balanceOf(accountOwner);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        return contract.totalSupply();
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getInflationParameters() {
        return contract.getInflationParameters();
    }

    public RemoteFunctionCall<BigInteger> valueToUnits(BigInteger value) {
        return contract.valueToUnits(value);
    }

    public RemoteFunctionCall<BigInteger> unitsToValue(BigInteger units) {
        return contract.unitsToValue(units);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        return contract.transfer(to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> debitGasFees(String from, BigInteger value) {
        return contract.debitGasFees(from, value);
    }

    public RemoteFunctionCall<TransactionReceipt> creditGasFees(String from, String feeRecipient, String gatewayFeeRecipient, String communityFund, BigInteger refund, BigInteger tipTxFee, BigInteger gatewayFee, BigInteger baseTxFee) {
        return contract.creditGasFees(from, feeRecipient, gatewayFeeRecipient, communityFund, refund, tipTxFee, gatewayFee, baseTxFee);
    }
}
