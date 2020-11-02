# celo-sdk-java

## Introduction

celo-sdk-java, originally adapted from Ethereum web3j, is a Java library for working with the Celo Blockchain and Celo Core Contracts.

## Features

- Connect to a node
- Access web3 object to interact with node's Json RPC API
- Send Transaction with celo's extra fields: (feeCurrency)
- Simple interface to interact with CELO and cUSD
- Simple interface to interact with Celo Core contracts
- Utilities

## Getting Started

### Prerequisites
Java 8  
Gradle 6

### Install
Install from repositories:  
maven  
```
<dependency>
  <groupId>org.celo</groupId>
  <artifactId>contractkit</artifactId>
  <version>0.0.1</version>
</dependency>
```
Gradle
```
compile 'org.celo:contractkit:0.0.1'
```

Install manually
If you want to generate the jar and import manually.
```
git clone https://github.com/celo/celo-sdk-java.git
./gradlew clean build -xtest
./gradlew publishToMavenLocal
```

#### Testing

Most of tests uses Ganache testnet. 

To start devchain run `./scripts/start_devchain.sh` script.

To reset devchain to the initial state run `./scripts/reset_devchain.sh` script. It's recommend to run script before run all tests. 

```
./scripts/reset_devchain.sh
./scripts/start_devchain.sh

./gradlew clean test
```

#### Publishing to JFrog Bintray
You can read more about in this [manual](https://medium.com/@ankit9673/publishing-your-android-java-library-to-jcenter-89a2beba7e6b)

1. Create an Account on [JFrog Bintray](https://bintray.com/)
2. Create maven repository (name: celo)
3. Build and upload library

```
./gradlew bintrayUpload -Dbintray.user=<YOUR_USER_NAME> -Dbintray.key=<YOUR_API_KEY>
```

The package will be created with these parameters
```
Repository: celo
Package: com.celo:contractkit
```

4 - After publish to the Bintray library can be published to the JCenter

Follow these [manual](https://www.jfrog.com/confluence/display/BT/Central+Repositories)

### Initializing the ContractKit

To start working with ContractKit you need a `kit` instance and a valid net to connect with. In this example will use `alfajores` (you can read more about it [here](../../getting-started/alfajores-testnet.md))

```java
import org.celo.contractkit.ContractKit; 

ContractKit contractKit = ContractKit.build(new HttpService("https://alfajores-forno.celo-testnet.org"));
```

#### Initialize the ContractKit with your own node

If you are hosting your own node you can connect our ContractKit to it.

Same as `Web3` we support `WebSockets`, `RPC` and connecting via `IPC`.
For this last one you will have to initialize the `kit` with an instance of `Web3` that has a **valid** `IPC Provider`

```java
import org.celo.contractkit.ContractKit; 
import org.web3j.protocol.Web3j;
import org.web3j.protocol.ipc.UnixIpcService;

Web3j web3j = Web3j.build(new UnixIpcService("/path/to/socketfile"));
ContractKit contractKit = new ContractKit(web3j);
```

## ContractKit Usage

The following are some examples of the capabilities of the `ContractKit`, assuming it is already connected to a node. If you aren't connected, [here is a refresher.](../walkthroughs/hellocontracts.md#deploy-to-alfajores)

### Setting Default Tx Options

`kit` allows you to set default transaction options:

```java
Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));

ContractKitOptions config = new ContractKitOptions.Builder()
    .setFeeCurrency(CeloContract.GoldToken)
    .setGasPrice(BigInteger.valueOf(21_000))
    .build();
ContractKit contractKit = new ContractKit(web3j, config);
```

Multiple accounts can be added to the kit wallet. The first added account will be used by default.
```java
Credentials credentials = Credentials.create(somePrivateKey);
contractKit.addAccount(credentials);

or

contractKit.addAccount(somePrivateKey);
```

To change default account to sign transactions 
```java
contractKit.setDefaultAccount(publicKey);
```

### Getting the Total Balance

This method from the `kit` will return the CELO, locked CELO, cUSD and total balance of the address

```java
AccountBalance balance = contractKit.getTotalBalance(myAddress);
```

### Deploy a contract

Deploying a contract with the default account already set. 

You can verify the deployment on the [Alfajores block explorer here](https://alfajores-blockscout.celo-testnet.org/). Wait for the receipt and log it to get the transaction details.

```java
Web3j web3j = Web3j.build(new HttpService(ContractKit.ALFAJORES_TESTNET));

ContractKit contractKit = new ContractKit(web3j);
contractKit.addAccount(PRIVATE_KEY);

GoldToken deployedGoldenToken = contractKit.contracts.getGoldToken().deploy().send();

TransactionReceipt receipt = deployedGoldenToken.getTransactionReceipt().get();
String hash = receipt.getTransactionHash();
```

### Buying all the CELO I can, with the cUSD in my account

```java
ExchangeWrapper exchange = contractKit.contracts.getExchange();
StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
GoldTokenWrapper goldToken = contractKit.contracts.getGoldToken();

BigInteger cUsdBalance = stableToken.balanceOf(myAddress).send();

TransactionReceipt approveTx = goldToken.approve(exchange.getContractAddress(), cUsdBalance).send();
String approveTxHash = approveTx.getTransactionHash();

BigInteger goldAmount = exchange.getBuyTokenAmount(cUsdBalance, false).send();
TransactionReceipt sellTx = exchange.exchange(cUsdBalance, goldAmount, true).send();
String sellTxHash = sellTx.getTransactionHash();
```

## Celo Core Contracts. Wrappers / Registry

### Interacting with CELO & cUSD

celo-blockchain has two initial coins: CELO and cUSD (stableToken).
Both implement the ERC20 standard, and to interact with them is as simple as:

```java
GoldTokenWrapper goldtoken = contractKit.contracts.getGoldToken();
BigInteger goldBalance = goldtoken.balanceOf(someAddress).send();
```

To send funds:

```java
BigInteger oneGold = Convert.toWei(BigDecimal.ONE, Convert.Unit.ETHER).toBigInteger();
TransactionReceipt tx = goldToken.transfer(someAddress, oneGold).send();
String hash = tx.getTransactionHash();
```

To interact with cUSD, is the same but with a different contract:
```java
StableTokenWrapper stableToken = contractKit.contracts.getStableToken();
```

### Interacting with Other Celo Contracts

Apart from GoldToken and StableToken, there are many core contracts.

For the moment, we have contract wrappers for:

- Accounts
- Attestations
- BlockchainParameters
- DobleSigningSlasher
- DowntimeSlasher
- Election
- Escrow
- Exchange (Uniswap kind exchange between Gold and Stable tokens)
- GasPriceMinimum
- GoldToken
- Gobernance
- LockedGold
- Reserve
- SortedOracles
- Validators
- StableToken

### A Note About Contract Addresses

Celo Core Contracts addresses, can be obtained by looking at the `Registry` contract.
That's actually how `kit` obtain them.

We expose the registry api, which can be accessed by:

```java
String goldTokenAddress = contractKit.contracts.addressFor(CeloContract.GoldToken);
```

### Sending Custom Transactions

Celo transaction object is not the same as Ethereum's. There are three new fields present:

- feeCurrency (address of the ERC20 contract to use to pay for gas and the gateway fee)
- gatewayFeeRecipient (coinbase address of the full serving the light client's trasactions)
- gatewayFee (value paid to the gateway fee recipient, denominated in the fee currency)

This means that using `web3.eth.sendTransaction` or `myContract.methods.transfer().send()` should be **avoided**.

Instead, `kit` provides an utility method to send transaction in both scenarios. **If you use contract wrappers, there is no need to use this.**

For a raw transaction:
```java
CeloRawTransaction tx = CeloRawTransaction.createCeloTransaction(
                BigInteger.ZERO,
                GAS_PRICE,
                GAS_LIMIT,
                someAddress,
                oneGwei,
                contractKit.contracts.addressFor(CeloContract.StableToken),
                null,
                null
        );
EthSendTransaction receipt = contractKit.sendTransaction(tx);
assertNotNull(receipt.getTransactionHash());
```