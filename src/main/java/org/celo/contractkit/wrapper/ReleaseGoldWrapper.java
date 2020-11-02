package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.ReleaseGold;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;

public class ReleaseGoldWrapper extends BaseWrapper<ReleaseGold> {

    public ReleaseGoldWrapper(ReleaseGold contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static ReleaseGoldWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        ReleaseGold contract = ReleaseGold.load(contractAddress, web3j, transactionManager, gasProvider);
        return new ReleaseGoldWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<ReleaseGold> deploy() {
        return ReleaseGold.deploy(web3j, transactionManager, gasProvider);
    }

    public RemoteFunctionCall<BigInteger> EXPIRATION_TIME() {
        return contract.EXPIRATION_TIME();
    }

    public RemoteFunctionCall<String> beneficiary() {
        return contract.beneficiary();
    }

    public RemoteFunctionCall<Boolean> canValidate() {
        return contract.canValidate();
    }

    public RemoteFunctionCall<Boolean> canVote() {
        return contract.canVote();
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<Boolean> liquidityProvisionMet() {
        return contract.liquidityProvisionMet();
    }

    public RemoteFunctionCall<BigInteger> maxDistribution() {
        return contract.maxDistribution();
    }

    public RemoteFunctionCall<String> owner() {
        return contract.owner();
    }

    public RemoteFunctionCall<String> refundAddress() {
        return contract.refundAddress();
    }

    public RemoteFunctionCall<String> registry() {
        return contract.registry();
    }

    public RemoteFunctionCall<String> releaseOwner() {
        return contract.releaseOwner();
    }

    public RemoteFunctionCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> releaseSchedule() {
        return contract.releaseSchedule();
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        return contract.renounceOwnership();
    }

    public RemoteFunctionCall<Tuple4<Boolean, Boolean, BigInteger, BigInteger>> revocationInfo() {
        return contract.revocationInfo();
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistry(String registryAddress) {
        return contract.setRegistry(registryAddress);
    }

    public RemoteFunctionCall<BigInteger> totalWithdrawn() {
        return contract.totalWithdrawn();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        return contract.transfer(to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(BigInteger releaseStartTime, BigInteger releaseCliffTime, BigInteger numReleasePeriods, BigInteger releasePeriod, BigInteger amountReleasedPerPeriod, Boolean revocable, String _beneficiary, String _releaseOwner, String _refundAddress, Boolean subjectToLiquidityProvision, BigInteger initialDistributionRatio, Boolean _canValidate, Boolean _canVote, String registryAddress) {
        return contract.initialize(releaseStartTime, releaseCliffTime, numReleasePeriods, releasePeriod, amountReleasedPerPeriod, revocable, _beneficiary, _releaseOwner, _refundAddress, subjectToLiquidityProvision, initialDistributionRatio, _canValidate, _canVote, registryAddress);
    }

    public RemoteFunctionCall<Boolean> isRevoked() {
        return contract.isRevoked();
    }

    public RemoteFunctionCall<TransactionReceipt> setLiquidityProvision() {
        return contract.setLiquidityProvision();
    }

    public RemoteFunctionCall<TransactionReceipt> setCanExpire(Boolean _canExpire) {
        return contract.setCanExpire(_canExpire);
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxDistribution(BigInteger distributionRatio) {
        return contract.setMaxDistribution(distributionRatio);
    }

    public RemoteFunctionCall<TransactionReceipt> setBeneficiary(String newBeneficiary) {
        return contract.setBeneficiary(newBeneficiary);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger amount) {
        return contract.withdraw(amount);
    }

    public RemoteFunctionCall<TransactionReceipt> refundAndFinalize() {
        return contract.refundAndFinalize();
    }

    public RemoteFunctionCall<TransactionReceipt> revoke() {
        return contract.revoke();
    }

    public RemoteFunctionCall<TransactionReceipt> expire() {
        return contract.expire();
    }

    public RemoteFunctionCall<BigInteger> getTotalBalance() {
        return contract.getTotalBalance();
    }

    public RemoteFunctionCall<BigInteger> getRemainingTotalBalance() {
        return contract.getRemainingTotalBalance();
    }

    public RemoteFunctionCall<BigInteger> getRemainingUnlockedBalance() {
        return contract.getRemainingUnlockedBalance();
    }

    public RemoteFunctionCall<BigInteger> getRemainingLockedBalance() {
        return contract.getRemainingLockedBalance();
    }

    public RemoteFunctionCall<BigInteger> getCurrentReleasedTotalAmount() {
        return contract.getCurrentReleasedTotalAmount();
    }

    public RemoteFunctionCall<TransactionReceipt> lockGold(BigInteger value) {
        return contract.lockGold(value);
    }

    public RemoteFunctionCall<TransactionReceipt> unlockGold(BigInteger value) {
        return contract.unlockGold(value);
    }

    public RemoteFunctionCall<TransactionReceipt> relockGold(BigInteger index, BigInteger value) {
        return contract.relockGold(index, value);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawLockedGold(BigInteger index) {
        return contract.withdrawLockedGold(index);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeVoteSigner(String signer, BigInteger v, byte[] r, byte[] s) {
        return contract.authorizeVoteSigner(signer, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeValidatorSigner(String signer, BigInteger v, byte[] r, byte[] s) {
        return contract.authorizeValidatorSigner(signer, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeValidatorSignerWithPublicKey(String signer, BigInteger v, byte[] r, byte[] s, byte[] ecdsaPublicKey) {
        return contract.authorizeValidatorSignerWithPublicKey(signer, v, r, s, ecdsaPublicKey);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeValidatorSignerWithKeys(String signer, BigInteger v, byte[] r, byte[] s, byte[] ecdsaPublicKey, byte[] blsPublicKey, byte[] blsPop) {
        return contract.authorizeValidatorSignerWithKeys(signer, v, r, s, ecdsaPublicKey, blsPublicKey, blsPop);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeAttestationSigner(String signer, BigInteger v, byte[] r, byte[] s) {
        return contract.authorizeAttestationSigner(signer, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccount(String name, byte[] dataEncryptionKey, String walletAddress, BigInteger v, byte[] r, byte[] s) {
        return contract.setAccount(name, dataEncryptionKey, walletAddress, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> createAccount() {
        return contract.createAccount();
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountName(String name) {
        return contract.setAccountName(name);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountWalletAddress(String walletAddress, BigInteger v, byte[] r, byte[] s) {
        return contract.setAccountWalletAddress(walletAddress, v, r, s);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountDataEncryptionKey(byte[] dataEncryptionKey) {
        return contract.setAccountDataEncryptionKey(dataEncryptionKey);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountMetadataURL(String metadataURL) {
        return contract.setAccountMetadataURL(metadataURL);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeActive(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        return contract.revokeActive(group, value, lesser, greater, index);
    }

    public RemoteFunctionCall<TransactionReceipt> revokePending(String group, BigInteger value, String lesser, String greater, BigInteger index) {
        return contract.revokePending(group, value, lesser, greater, index);
    }
}
