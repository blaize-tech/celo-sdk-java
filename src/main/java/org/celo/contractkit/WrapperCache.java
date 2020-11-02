package org.celo.contractkit;

import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.celo.contractkit.wrapper.*;
import org.web3j.protocol.Web3j;

import java.util.HashMap;
import java.util.Map;

public class WrapperCache {
    public final AddressRegistry addressRegistry;

    private final Map<CeloContract, BaseWrapper<?>> cache;
    private final Web3j web3j;
    private final CeloGasProvider gasProvider;
    private final CeloTransactionManager transactionManager;

    public WrapperCache(Web3j web3j, CeloTransactionManager transactionManager) {
        this.cache = new HashMap<>();
        this.web3j = web3j;
        this.transactionManager = transactionManager;
        this.addressRegistry = new AddressRegistry(web3j, transactionManager);
        this.gasProvider = new CeloGasProvider(getGasPriceMinimum().getContract(), transactionManager.getFeeCurrency());
    }

    public void setFeeCurrency(String feeCurrency) {
        gasProvider.setFeeCurrency(feeCurrency);
    }

    public String addressFor(CeloContract contract) {
        return addressRegistry.addressFor(contract);
    }

    public AccountsWrapper getAccounts() {
        if (cache.containsKey(CeloContract.Accounts)) {
            return (AccountsWrapper) cache.get(CeloContract.Accounts);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Accounts);
            AccountsWrapper wrapper = AccountsWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Accounts, wrapper);
            return wrapper;
        }
    }

    public AttestationsWrapper getAttestations() {
        if (cache.containsKey(CeloContract.Attestations)) {
            return (AttestationsWrapper) cache.get(CeloContract.Attestations);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Attestations);
            AttestationsWrapper wrapper = AttestationsWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Attestations, wrapper);
            return wrapper;
        }
    }

    public BlockchainParametersWrapper getBlockchainParameters() {
        if (cache.containsKey(CeloContract.BlockchainParameters)) {
            return (BlockchainParametersWrapper) cache.get(CeloContract.BlockchainParameters);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.BlockchainParameters);
            BlockchainParametersWrapper wrapper = BlockchainParametersWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.BlockchainParameters, wrapper);
            return wrapper;
        }
    }

    public DoubleSigningSlasherWrapper getDoubleSigningSlasher() {
        if (cache.containsKey(CeloContract.DoubleSigningSlasher)) {
            return (DoubleSigningSlasherWrapper) cache.get(CeloContract.DoubleSigningSlasher);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.DoubleSigningSlasher);
            DoubleSigningSlasherWrapper wrapper = DoubleSigningSlasherWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.DoubleSigningSlasher, wrapper);
            return wrapper;
        }
    }

    public DowntimeSlasherWrapper getDowntimeSlasher() {
        if (cache.containsKey(CeloContract.DowntimeSlasher)) {
            return (DowntimeSlasherWrapper) cache.get(CeloContract.DowntimeSlasher);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.DowntimeSlasher);
            DowntimeSlasherWrapper wrapper = DowntimeSlasherWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.DowntimeSlasher, wrapper);
            return wrapper;
        }
    }

    public ElectionWrapper getElection() {
        if (cache.containsKey(CeloContract.Election)) {
            return (ElectionWrapper) cache.get(CeloContract.Election);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Election);
            ElectionWrapper wrapper = ElectionWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Election, wrapper);
            return wrapper;
        }
    }

    public ExchangeWrapper getExchange() {
        if (cache.containsKey(CeloContract.Exchange)) {
            return (ExchangeWrapper) cache.get(CeloContract.Exchange);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Exchange);
            ExchangeWrapper wrapper = ExchangeWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Exchange, wrapper);
            return wrapper;
        }
    }

    public GasPriceMinimumWrapper getGasPriceMinimum() {
        if (cache.containsKey(CeloContract.GasPriceMinimum)) {
            return (GasPriceMinimumWrapper) cache.get(CeloContract.GasPriceMinimum);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.GasPriceMinimum);
            GasPriceMinimumWrapper wrapper = GasPriceMinimumWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.GasPriceMinimum, wrapper);
            return wrapper;
        }
    }

    public GoldTokenWrapper getGoldToken() {
        if (cache.containsKey(CeloContract.GoldToken)) {
            return (GoldTokenWrapper) cache.get(CeloContract.GoldToken);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.GoldToken);
            GoldTokenWrapper wrapper = GoldTokenWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.GoldToken, wrapper);
            return wrapper;
        }
    }

    public GovernanceWrapper getGovernance() {
        if (cache.containsKey(CeloContract.Governance)) {
            return (GovernanceWrapper) cache.get(CeloContract.Governance);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Governance);
            GovernanceWrapper wrapper = GovernanceWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Governance, wrapper);
            return wrapper;
        }
    }

    public LockedGoldWrapper getLockedGold() {
        if (cache.containsKey(CeloContract.LockedGold)) {
            return (LockedGoldWrapper) cache.get(CeloContract.LockedGold);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.LockedGold);
            LockedGoldWrapper wrapper = LockedGoldWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.LockedGold, wrapper);
            return wrapper;
        }
    }

    public ReserveWrapper getReserve() {
        if (cache.containsKey(CeloContract.Reserve)) {
            return (ReserveWrapper) cache.get(CeloContract.Reserve);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Reserve);
            ReserveWrapper wrapper = ReserveWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Reserve, wrapper);
            return wrapper;
        }
    }

    public SortedOraclesWrapper getSortedOracles() {
        if (cache.containsKey(CeloContract.SortedOracles)) {
            return (SortedOraclesWrapper) cache.get(CeloContract.SortedOracles);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.SortedOracles);
            SortedOraclesWrapper wrapper = SortedOraclesWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.SortedOracles, wrapper);
            return wrapper;
        }
    }

    public ValidatorsWrapper getValidators() {
        if (cache.containsKey(CeloContract.Validators)) {
            return (ValidatorsWrapper) cache.get(CeloContract.Validators);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.Validators);
            ValidatorsWrapper wrapper = ValidatorsWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.Validators, wrapper);
            return wrapper;
        }
    }

    public StableTokenWrapper getStableToken() {
        if (cache.containsKey(CeloContract.StableToken)) {
            return (StableTokenWrapper) cache.get(CeloContract.StableToken);
        } else {
            String contractAddress = addressRegistry.addressFor(CeloContract.StableToken);
            StableTokenWrapper wrapper = StableTokenWrapper.load(contractAddress, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.StableToken, wrapper);
            return wrapper;
        }
    }

    public MultiSigWrapper getMultiSig(String address) {
        if (cache.containsKey(CeloContract.MultiSig)) {
            return (MultiSigWrapper) cache.get(CeloContract.MultiSig);
        } else {
            MultiSigWrapper wrapper = MultiSigWrapper.load(address, web3j, transactionManager, gasProvider);
            cache.put(CeloContract.MultiSig, wrapper);
            return wrapper;
        }
    }
}
