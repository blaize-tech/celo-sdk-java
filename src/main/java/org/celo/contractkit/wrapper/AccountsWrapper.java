package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Accounts;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

/**
 * Contract for handling deposits needed for voting.
 * TODO add docs, utils methods
 */
public class AccountsWrapper extends BaseWrapper<Accounts> {

    public AccountsWrapper(Accounts contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static AccountsWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        Accounts contract = Accounts.load(contractAddress, web3j, transactionManager, gasProvider);
        return new AccountsWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<Accounts> deploy() {
        return Accounts.deploy(web3j, transactionManager, gasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createAccount() {
        return contract.createAccount();
    }

    public RemoteFunctionCall<TransactionReceipt> setName(String name) {
        return contract.setName(name);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountDataEncryptionKey(byte[] dataEncryptionKey) {
        return contract.setAccountDataEncryptionKey(dataEncryptionKey);
    }

    public RemoteFunctionCall<TransactionReceipt> setMetadataURL(String metadataURL) {
        return contract.setMetadataURL(metadataURL);
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

    public RemoteFunctionCall<String> validatorSignerToAccount(String signer) {
        return contract.validatorSignerToAccount(signer);
    }

    public RemoteFunctionCall<String> voteSignerToAccount(String signer) {
        return contract.voteSignerToAccount(signer);
    }

    public RemoteFunctionCall<String> signerToAccount(String signer) {
        return contract.signerToAccount(signer);
    }

    public RemoteFunctionCall<String> getVoteSigner(String account) {
        return contract.getVoteSigner(account);
    }

    public RemoteFunctionCall<String> getValidatorSigner(String account) {
        return contract.getValidatorSigner(account);
    }

    public RemoteFunctionCall<String> getAttestationSigner(String account) {
        return contract.getAttestationSigner(account);
    }

    public RemoteFunctionCall<Boolean> hasAuthorizedAttestationSigner(String account) {
        return contract.hasAuthorizedAttestationSigner(account);
    }

    public RemoteFunctionCall<String> getName(String account) {
        return contract.getName(account);
    }

    public RemoteFunctionCall<String> getMetadataURL(String account) {
        return contract.getMetadataURL(account);
    }

    public RemoteFunctionCall<byte[]> getDataEncryptionKey(String account) {
        return contract.getDataEncryptionKey(account);
    }

    public RemoteFunctionCall<String> getWalletAddress(String account) {
        return contract.getWalletAddress(account);
    }

    public RemoteFunctionCall<Boolean> isAccount(String account) {
        return contract.isAccount(account);
    }

    public RemoteFunctionCall<Boolean> isAuthorizedSigner(String signer) {
        return contract.isAuthorizedSigner(signer);
    }

    public RemoteFunctionCall<TransactionReceipt> setWalletAddress(String address) {
        return contract.setWalletAddress(address, BigInteger.ZERO, "0x0".getBytes(), "0x0".getBytes());
    }
}
