package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Validators;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;
import java.util.List;

public class ValidatorsWrapper extends BaseWrapper<Validators> {
    public ValidatorsWrapper(Validators contract) {
        super(contract);
    }

    public RemoteFunctionCall<Boolean> checkProofOfPossession(String sender, byte[] blsKey, byte[] blsPop) {
        return contract.checkProofOfPossession(sender, blsKey, blsPop);
    }

    public RemoteFunctionCall<BigInteger> commissionUpdateDelay() {
        return contract.commissionUpdateDelay();
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

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> groupLockedGoldRequirements() {
        return contract.groupLockedGoldRequirements();
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

    public RemoteFunctionCall<BigInteger> maxGroupSize() {
        return contract.maxGroupSize();
    }

    public RemoteFunctionCall<BigInteger> membershipHistoryLength() {
        return contract.membershipHistoryLength();
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

    public RemoteFunctionCall<BigInteger> slashingMultiplierResetPeriod() {
        return contract.slashingMultiplierResetPeriod();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> validatorLockedGoldRequirements() {
        return contract.validatorLockedGoldRequirements();
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

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress, BigInteger groupRequirementValue, BigInteger groupRequirementDuration, BigInteger validatorRequirementValue, BigInteger validatorRequirementDuration, BigInteger validatorScoreExponent, BigInteger validatorScoreAdjustmentSpeed, BigInteger _membershipHistoryLength, BigInteger _slashingMultiplierResetPeriod, BigInteger _maxGroupSize, BigInteger _commissionUpdateDelay) {
        return contract.initialize(registryAddress, groupRequirementValue, groupRequirementDuration, validatorRequirementValue, validatorRequirementDuration, validatorScoreExponent, validatorScoreAdjustmentSpeed, _membershipHistoryLength, _slashingMultiplierResetPeriod, _maxGroupSize, _commissionUpdateDelay);
    }

    public RemoteFunctionCall<TransactionReceipt> setCommissionUpdateDelay(BigInteger delay) {
        return contract.setCommissionUpdateDelay(delay);
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxGroupSize(BigInteger size) {
        return contract.setMaxGroupSize(size);
    }

    public RemoteFunctionCall<TransactionReceipt> setMembershipHistoryLength(BigInteger length) {
        return contract.setMembershipHistoryLength(length);
    }

    public RemoteFunctionCall<TransactionReceipt> setValidatorScoreParameters(BigInteger exponent, BigInteger adjustmentSpeed) {
        return contract.setValidatorScoreParameters(exponent, adjustmentSpeed);
    }

    public RemoteFunctionCall<BigInteger> getMaxGroupSize() {
        return contract.getMaxGroupSize();
    }

    public RemoteFunctionCall<BigInteger> getCommissionUpdateDelay() {
        return contract.getCommissionUpdateDelay();
    }

    public RemoteFunctionCall<TransactionReceipt> setGroupLockedGoldRequirements(BigInteger value, BigInteger duration) {
        return contract.setGroupLockedGoldRequirements(value, duration);
    }

    public RemoteFunctionCall<TransactionReceipt> setValidatorLockedGoldRequirements(BigInteger value, BigInteger duration) {
        return contract.setValidatorLockedGoldRequirements(value, duration);
    }

    public RemoteFunctionCall<TransactionReceipt> registerValidator(byte[] ecdsaPublicKey, byte[] blsPublicKey, byte[] blsPop) {
        return contract.registerValidator(ecdsaPublicKey, blsPublicKey, blsPop);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getValidatorScoreParameters() {
        return contract.getValidatorScoreParameters();
    }

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<String>, BigInteger, BigInteger>> getMembershipHistory(String account) {
        return contract.getMembershipHistory(account);
    }

    public RemoteFunctionCall<BigInteger> calculateEpochScore(BigInteger uptime) {
        return contract.calculateEpochScore(uptime);
    }

    public RemoteFunctionCall<BigInteger> calculateGroupEpochScore(List<BigInteger> uptimes) {
        return contract.calculateGroupEpochScore(uptimes);
    }

    public RemoteFunctionCall<TransactionReceipt> updateValidatorScoreFromSigner(String signer, BigInteger uptime) {
        return contract.updateValidatorScoreFromSigner(signer, uptime);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeEpochPaymentsFromSigner(String signer, BigInteger maxPayment) {
        return contract.distributeEpochPaymentsFromSigner(signer, maxPayment);
    }

    public RemoteFunctionCall<TransactionReceipt> deregisterValidator(BigInteger index) {
        return contract.deregisterValidator(index);
    }

    public RemoteFunctionCall<TransactionReceipt> affiliate(String group) {
        return contract.affiliate(group);
    }

    public RemoteFunctionCall<TransactionReceipt> deaffiliate() {
        return contract.deaffiliate();
    }

    public RemoteFunctionCall<TransactionReceipt> updateBlsPublicKey(byte[] blsPublicKey, byte[] blsPop) {
        return contract.updateBlsPublicKey(blsPublicKey, blsPop);
    }

    public RemoteFunctionCall<TransactionReceipt> updateEcdsaPublicKey(String account, String signer, byte[] ecdsaPublicKey) {
        return contract.updateEcdsaPublicKey(account, signer, ecdsaPublicKey);
    }

    public RemoteFunctionCall<TransactionReceipt> updatePublicKeys(String account, String signer, byte[] ecdsaPublicKey, byte[] blsPublicKey, byte[] blsPop) {
        return contract.updatePublicKeys(account, signer, ecdsaPublicKey, blsPublicKey, blsPop);
    }

    public RemoteFunctionCall<TransactionReceipt> registerValidatorGroup(BigInteger commission) {
        return contract.registerValidatorGroup(commission);
    }

    public RemoteFunctionCall<TransactionReceipt> deregisterValidatorGroup(BigInteger index) {
        return contract.deregisterValidatorGroup(index);
    }

    public RemoteFunctionCall<TransactionReceipt> addMember(String validator) {
        return contract.addMember(validator);
    }

    public RemoteFunctionCall<TransactionReceipt> addFirstMember(String validator, String lesser, String greater) {
        return contract.addFirstMember(validator, lesser, greater);
    }

    public RemoteFunctionCall<TransactionReceipt> removeMember(String validator) {
        return contract.removeMember(validator);
    }

    public RemoteFunctionCall<TransactionReceipt> reorderMember(String validator, String lesserMember, String greaterMember) {
        return contract.reorderMember(validator, lesserMember, greaterMember);
    }

    public RemoteFunctionCall<TransactionReceipt> setNextCommissionUpdate(BigInteger commission) {
        return contract.setNextCommissionUpdate(commission);
    }

    public RemoteFunctionCall<TransactionReceipt> updateCommission() {
        return contract.updateCommission();
    }

    public RemoteFunctionCall<BigInteger> getAccountLockedGoldRequirement(String account) {
        return contract.getAccountLockedGoldRequirement(account);
    }

    public RemoteFunctionCall<Boolean> meetsAccountLockedGoldRequirements(String account) {
        return contract.meetsAccountLockedGoldRequirements(account);
    }

    public RemoteFunctionCall<byte[]> getValidatorBlsPublicKeyFromSigner(String signer) {
        return contract.getValidatorBlsPublicKeyFromSigner(signer);
    }

    public RemoteFunctionCall<Tuple5<byte[], byte[], String, BigInteger, String>> getValidator(String account) {
        return contract.getValidator(account);
    }

    public RemoteFunctionCall<Tuple7<List<String>, BigInteger, BigInteger, BigInteger, List<BigInteger>, BigInteger, BigInteger>> getValidatorGroup(String account) {
        return contract.getValidatorGroup(account);
    }

    public RemoteFunctionCall<BigInteger> getGroupNumMembers(String account) {
        return contract.getGroupNumMembers(account);
    }

    public RemoteFunctionCall<List> getTopGroupValidators(String account, BigInteger n) {
        return contract.getTopGroupValidators(account, n);
    }

    public RemoteFunctionCall<List> getGroupsNumMembers(List<String> accounts) {
        return contract.getGroupsNumMembers(accounts);
    }

    public RemoteFunctionCall<BigInteger> getNumRegisteredValidators() {
        return contract.getNumRegisteredValidators();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getValidatorLockedGoldRequirements() {
        return contract.getValidatorLockedGoldRequirements();
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getGroupLockedGoldRequirements() {
        return contract.getGroupLockedGoldRequirements();
    }

    public RemoteFunctionCall<List> getRegisteredValidators() {
        return contract.getRegisteredValidators();
    }

    public RemoteFunctionCall<List> getRegisteredValidatorSigners() {
        return contract.getRegisteredValidatorSigners();
    }

    public RemoteFunctionCall<List> getRegisteredValidatorGroups() {
        return contract.getRegisteredValidatorGroups();
    }

    public RemoteFunctionCall<Boolean> isValidatorGroup(String account) {
        return contract.isValidatorGroup(account);
    }

    public RemoteFunctionCall<Boolean> isValidator(String account) {
        return contract.isValidator(account);
    }

    public RemoteFunctionCall<String> getMembershipInLastEpochFromSigner(String signer) {
        return contract.getMembershipInLastEpochFromSigner(signer);
    }

    public RemoteFunctionCall<String> getMembershipInLastEpoch(String account) {
        return contract.getMembershipInLastEpoch(account);
    }

    public RemoteFunctionCall<TransactionReceipt> forceDeaffiliateIfValidator(String validatorAccount) {
        return contract.forceDeaffiliateIfValidator(validatorAccount);
    }

    public RemoteFunctionCall<TransactionReceipt> setSlashingMultiplierResetPeriod(BigInteger value) {
        return contract.setSlashingMultiplierResetPeriod(value);
    }

    public RemoteFunctionCall<TransactionReceipt> resetSlashingMultiplier() {
        return contract.resetSlashingMultiplier();
    }

    public RemoteFunctionCall<TransactionReceipt> halveSlashingMultiplier(String account) {
        return contract.halveSlashingMultiplier(account);
    }

    public RemoteFunctionCall<BigInteger> getValidatorGroupSlashingMultiplier(String account) {
        return contract.getValidatorGroupSlashingMultiplier(account);
    }

    public RemoteFunctionCall<String> groupMembershipInEpoch(String account, BigInteger epochNumber, BigInteger index) {
        return contract.groupMembershipInEpoch(account, epochNumber, index);
    }
}
