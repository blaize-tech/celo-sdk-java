package org.celo.contractkit;

import org.celo.contractkit.contract.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.HashMap;
import java.util.Map;

import static org.celo.contractkit.Utils.soliditySha3;

public class AddressRegistry {
    public static final String REGISTRY_CONTRACT_ADDRESS = "0x000000000000000000000000000000000000ce10";
    public static final String NULL_ADDRESS = "0x0000000000000000000000000000000000000000";

    private static final Logger logger = LoggerFactory.getLogger(AddressRegistry.class);

    private final Map<CeloContract, String> cache;
    private final Registry registryContract;

    public AddressRegistry(Web3j web3j, TransactionManager transactionManager) {
        cache = new HashMap<>();
        cache.put(CeloContract.Registry, REGISTRY_CONTRACT_ADDRESS);
        registryContract = Registry.load(REGISTRY_CONTRACT_ADDRESS, web3j, transactionManager, new DefaultGasProvider());
    }

    /**
     * Get the address for a `CeloContract`
     */
    public String addressFor(CeloContract contract) {
        if (cache.containsKey(contract)) {
            return cache.get(contract);
        } else {
            String proxyStrippedContract = contract.toString().replace("Proxy", "");
            byte[] hash = soliditySha3(proxyStrippedContract);
            logger.info("Fetching address from Registry for {}", contract);
            try {
                String address = registryContract.getAddressFor(hash).send();
                if (address == null || address.equals(NULL_ADDRESS)) {
                    //TODO add own exception
                    throw new Error("Failed to get address for " + contract + "from the Registry");
                }
                cache.put(contract, address);
                return address;
            } catch (Exception e) {
                throw new Error("Failed to get address for " + contract + "from the Registry", e);
            }
        }
    }

    /**
     * Get the address for all possible `CeloContract`
     */
    public Map<CeloContract, String> allAddresses() {
        for (CeloContract contract : CeloContract.values()) {
            this.addressFor(contract);
        }
        return cache;
    }
}
