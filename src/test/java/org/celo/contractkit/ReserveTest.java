package org.celo.contractkit;

import org.celo.contractkit.wrapper.MultiSigWrapper;
import org.celo.contractkit.wrapper.ReserveWrapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.celo.contractkit.TestData.DERIV_PRIVATE_KEYS;
import static org.junit.Assert.assertTrue;

public class ReserveTest {

    ContractKit contractKit;
    ReserveWrapper reserve;
    MultiSigWrapper reserveSpenderMultiSig;
    List<String> accounts;
    String otherReserveAddress;
    String otherSpender;

    @Before
    public void initialize() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        contractKit = new ContractKit(web3j, ContractKitOptions.GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);

        reserve = contractKit.contracts.getReserve();
        accounts = contractKit.web3j.ethAccounts().send().getAccounts();

        otherReserveAddress = accounts.get(9);
        otherSpender = accounts.get(7);

        EthTransaction transaction = contractKit.web3j.ethGetTransactionByBlockNumberAndIndex(DefaultBlockParameterName.LATEST, BigInteger.valueOf(0)).send();
        EthGetTransactionReceipt receipt = contractKit.web3j.ethGetTransactionReceipt(transaction.getTransaction().get().getHash()).send();
        List<String> spenders = reserve.getSpenders(receipt.getTransactionReceipt().get());
        String multiSigAddress = spenders.size() > 0 ? spenders.get(spenders.size() - 1) : "";
        reserveSpenderMultiSig = contractKit.contracts.getMultiSig(multiSigAddress);
    }

    @Test
    @Ignore("TODO events processing")
    public void testIsSpender() throws Exception {
        Boolean isSpender = reserve.isSpender(reserveSpenderMultiSig.getAddress()).send();
        assertTrue(isSpender);
    }
}
