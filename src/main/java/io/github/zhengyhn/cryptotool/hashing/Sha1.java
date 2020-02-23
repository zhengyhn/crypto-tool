package io.github.zhengyhn.cryptotool.hashing;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sha1 implements IHashing {
    public byte[] hash(byte[] originalBytes) {
        long bits = originalBytes.length * 8;
        int newLength = ((originalBytes.length / 64) + 1) * 64;
        if ((newLength - originalBytes.length) < 9) {
            newLength += 64;
        }
        byte[] bytes = new byte[newLength];
        System.arraycopy(originalBytes, 0, bytes, 0, originalBytes.length);
        bytes[originalBytes.length] = (byte) 0x80;
        for (int i = bytes.length - 8; i < bytes.length; ++i) {
            bytes[i] = (byte) ((bits >>> (56 - (i - (bytes.length - 8)) * 8)) & 0xff);
        }
        int[] hash = new int[]{0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0};
        int[] words = new int[80];
        for (int i = 0; i < bytes.length; ) {
            for (int t = 0; t < 16; ++t) {
                words[t] = (bytes[i] << 24) | ((bytes[i + 1] << 16) & 0xff0000) +
                        ((bytes[i + 2] << 8) & 0xff00) + (bytes[i + 3] & 0xff);
                i += 4;
            }
            for (int t = 16; t < 80; ++t) {
                words[t] = circularLeftShift(1, words[t - 3] ^ words[t - 8] ^ words[t - 14] ^ words[t - 16]);
            }
            int[] newHash = Arrays.copyOf(hash, hash.length);
            for (int t = 0; t < 80; ++t) {
                int temp = circularLeftShift(5, newHash[0]) + func(t, newHash[1], newHash[2], newHash[3]) +
                        newHash[4] + words[t] + key(t);
                newHash[4] = newHash[3];
                newHash[3] = newHash[2];
                newHash[2] = circularLeftShift(30, newHash[1]);
                newHash[1] = newHash[0];
                newHash[0] = temp;
            }
            for (int j = 0; j < hash.length; ++j) {
                hash[j] += newHash[j];
            }
        }
        byte[] result = new byte[hash.length * 4];
        int i = 0;
        for (int h : hash) {
            result[i++] = (byte) ((h >>> 24) & 0xff);
            result[i++] = (byte) ((h >>> 16) & 0xff);
            result[i++] = (byte) ((h >>> 8) & 0xff);
            result[i++] = (byte) (h & 0xff);
        }
        return result;
    }

    @Override
    public String digest(String text) {
        byte[] result = hash(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : result) {
            stringBuilder.append(Integer.toHexString(b & 0xff));
        }
        return stringBuilder.toString();
    }

    private int circularLeftShift(int n, int x) {
        return (x << n) | (x >>> (32 - n));
    }

    private int func(int t, int b, int c, int d) {
        if (t >= 0 && t < 20) {
            return (b & c) | ((~b) & d);
        } else if (t < 40) {
            return b ^ c ^ d;
        } else if (t < 60) {
            return (b & c) | (b & d) | (c & d);
        } else {
            return b ^ c ^ d;
        }
    }

    private int key(int t) {
        if (t >= 0 && t < 20) {
            return 0x5A827999;
        } else if (t < 40) {
            return 0x6ED9EBA1;
        } else if (t < 60) {
            return 0x8F1BBCDC;
        } else {
            return 0xCA62C1D6;
        }
    }
}
