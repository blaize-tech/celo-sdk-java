package org.celo.contractkit.protocol;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Bytes;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 'nonce',
 * 'gasPrice',
 * 'gas',
 * 'feeCurrency',           -> CELO field
 * 'gatewayFeeRecipient',   -> CELO field
 * 'gatewayFee',            -> CELO field
 * 'to',
 * 'value',
 * 'data',
 * 'chainId',
 */
public class CeloTransactionEncoder extends TransactionEncoder {

    private static RlpString encodeAddress(String address) {
        // an empty to address (contract creation) should not be encoded as a numeric 0 value
        if (address != null && address.length() > 0) {
            // addresses that start with zeros should be encoded with the zeros included, not
            // as numeric values
           return RlpString.create(Numeric.hexStringToByteArray(address));
        } else {
            return RlpString.create("");
        }
    }

    private static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static List<RlpType> asRlpValues(
            CeloRawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<RlpType> result = new ArrayList<>();

        result.add(RlpString.create(rawTransaction.getNonce()));
        result.add(RlpString.create(rawTransaction.getGasPrice()));
        result.add(RlpString.create(rawTransaction.getGasLimit()));

        // CELO custom fields
        result.add(CeloTransactionEncoder.encodeAddress(rawTransaction.getFeeCurrency()));
        result.add(CeloTransactionEncoder.encodeAddress(rawTransaction.getGatewayFeeRecipient()));
        result.add(RlpString.create(rawTransaction.getGatewayFee() != null ? rawTransaction.getGatewayFee() : BigInteger.ZERO));

        result.add(CeloTransactionEncoder.encodeAddress(rawTransaction.getTo()));
        result.add(RlpString.create(rawTransaction.getValue()));

        // value field will already be hex encoded, so we need to convert into binary first
        byte[] data = Numeric.hexStringToByteArray(rawTransaction.getData());
        result.add(RlpString.create(data));

        if (signatureData != null) {
            result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getV())));
            result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getR())));
            result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getS())));
        }

        return result;
    }

    private static byte[] encode(CeloRawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<RlpType> values = asRlpValues(rawTransaction, signatureData);
        RlpList rlpList = new RlpList(values);
        return RlpEncoder.encode(rlpList);
    }

    public static byte[] encode(CeloRawTransaction rawTransaction, long chainId) {
        Sign.SignatureData signatureData =
                new Sign.SignatureData(longToBytes(chainId), new byte[] {}, new byte[] {});
        return encode(rawTransaction, signatureData);
    }

    public static byte[] signMessage(CeloRawTransaction rawTransaction, Credentials credentials) {
        byte[] encodedTransaction = encode(rawTransaction);
        Sign.SignatureData signatureData =
                Sign.signMessage(encodedTransaction, credentials.getEcKeyPair());

        return encode(rawTransaction, signatureData);
    }
    public static byte[] signMessage(
            CeloRawTransaction rawTransaction, long chainId, Credentials credentials) {
        byte[] encodedTransaction = encode(rawTransaction, chainId);
        Sign.SignatureData signatureData =
                Sign.signMessage(encodedTransaction, credentials.getEcKeyPair());

        Sign.SignatureData eip155SignatureData = createEip155SignatureData(signatureData, chainId);
        return encode(rawTransaction, eip155SignatureData);
    }
}
