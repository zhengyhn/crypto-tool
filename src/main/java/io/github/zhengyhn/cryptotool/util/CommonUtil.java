package io.github.zhengyhn.cryptotool.util;

import java.util.Arrays;

public class CommonUtil {
    public static String toBinaryString(byte[] bytes) {
        String[] bits = new String[bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            bits[i] = Integer.toBinaryString(bytes[i] & 0xff);
        }
        return String.join(" ", bits);
    }
}
