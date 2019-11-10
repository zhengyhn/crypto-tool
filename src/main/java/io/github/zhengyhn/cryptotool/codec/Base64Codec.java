package io.github.zhengyhn.cryptotool.codec;

import java.nio.charset.StandardCharsets;

public class Base64Codec implements ICodec {
    private static final char[] encodeTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static byte[] decodeTable;

    static {
        decodeTable = new byte[128];
        for (int i = 0; i < encodeTable.length; ++i) {
            decodeTable[encodeTable[i]] = (byte)i;
        }
    }

    @Override
    public String encode(String text) {
        byte[] bytes = this.string2Bytes(text);
        if (bytes == null || bytes.length <= 0) {
            return "";
        }
        final int totalBit = 8;
        final int totalEncodeBit = 6;
        int length = bytes.length * totalBit / totalEncodeBit;
        length = (length - 1) - (length - 1) % 4 + 4;
        char[] encoded = new char[length];
        int i = 0;
        int j = 0;
        byte currentByte = 0;
        int maskBit = totalEncodeBit;
        while (i < bytes.length) {
            byte b = bytes[i];
            currentByte += (byte)((b >>> (totalBit - maskBit)) & 0x3f);
            encoded[j++] = encodeTable[currentByte];
            maskBit = totalEncodeBit - (totalBit - maskBit);
            currentByte = (byte)((b << maskBit) & 0x3f);
            if (maskBit == 0) {
                encoded[j++] = encodeTable[currentByte];
                currentByte = 0;
                maskBit = totalEncodeBit;
            }
            ++i;
        }
        while (j < encoded.length) {
            if (currentByte != 0) {
                encoded[j++] = encodeTable[currentByte];
                currentByte = 0;
            } else {
                encoded[j++] = '=';
            }
        }
        return new String(encoded);
    }

    @Override
    public String decode(String text) {
        if (text == null || text.length() <= 0) {
            return "";
        }
        byte[] bytes = new byte[text.length()];
        int i = 0;
        int length = text.length();
        for (char c: text.toCharArray()) {
            if (c == '=') {
                length = i;
                break;
            }
            bytes[i++] = decodeTable[c];
        }
        byte[] decoded = new byte[length * 6 / 8];
        final int totalBit = 8;
        final int totalEncodeBit = 6;
        byte currentByte = 0;
        int maskBit = totalEncodeBit;
        int j = 0;
        i = 0;
        while (j < decoded.length) {
            currentByte = (byte)(bytes[i] << (totalBit - maskBit));
            ++i;
            maskBit = totalEncodeBit - (totalBit - maskBit);
            currentByte += (byte)(bytes[i] >>> maskBit);
            decoded[j++] = currentByte;
            if (maskBit == 0) {
                maskBit = totalEncodeBit;
                ++i;
            }
        }
        return new String(decoded);
    }

    private byte[] string2Bytes(String text) {
        byte[] bytes = null;
        if (text == null) {
            return bytes;
        }
        try {
            bytes = text.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
