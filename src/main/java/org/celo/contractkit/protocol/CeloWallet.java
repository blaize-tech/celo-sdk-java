package org.celo.contractkit.protocol;

import org.web3j.crypto.Credentials;

import java.util.HashMap;

public class CeloWallet {
    private final HashMap<String, Credentials> wallet = new HashMap<>();
    private Credentials defaultAccount;

    public void addKey(String privateKey) {
        addKey(Credentials.create(privateKey.toLowerCase()));
    }

    public void addKey(Credentials credentials) {
        if (wallet.isEmpty()) {
            defaultAccount = credentials;
        }
        wallet.put(credentials.getAddress(), credentials);
    }

    public Credentials getKey(String publicKey) {
        if (publicKey == null || publicKey.isEmpty()) {
            return null;
        }
        return wallet.get(publicKey.toLowerCase());
    }

    public void setDefaultAccount(String publicKey) {
        Credentials credentials = wallet.get(publicKey);
        if (credentials != null) {
            defaultAccount = credentials;
        }
    }

    public Credentials getDefaultAccount() {
        return defaultAccount;
    }
}
