package io.github.zhengyhn.cryptotool.hashing;

import org.junit.Test;

import java.security.MessageDigest;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class Sha1Test {
    private Sha1 sha1 = new Sha1();

    @Test
    public void testSimpleStringShouldEncodeSuccessfully() throws Exception {
        MessageDigest systemSha1 = MessageDigest.getInstance("SHA-1");
        String[] strList = new String[]{
                "abc",
                "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq",
                "a", "01234567012345670123456701234567"
        };
        for (String str: strList) {
            String ret = sha1.digest(str);
            byte[] expectBytes = systemSha1.digest(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b: expectBytes) {
                stringBuilder.append(Integer.toHexString(b & 0xff));
            }
            assertTrue(ret.equals(stringBuilder.toString()));
        }
    }

    @Test
    public void testRandomTextShouldDigestSuccess() throws Exception {
        MessageDigest systemSha1 = MessageDigest.getInstance("SHA-1");
        final int SIZE = 100;
        for (int i = 0; i < SIZE; ++i) {
            int len = new Random().nextInt(1000) + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; ++j) {
                sb.append(new Random().nextInt(26) + (int)'a');
            }
            String string = sb.toString();
            String ret = sha1.digest(string);
            byte[] expectBytes = systemSha1.digest(string.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b: expectBytes) {
                stringBuilder.append(Integer.toHexString(b & 0xff));
            }
            assertTrue(stringBuilder.toString().equals(ret));
        }
    }

    @Test
    public void testChineseShouldDigestSuccess() throws Exception {
        MessageDigest systemSha1 = MessageDigest.getInstance("SHA-1");
        String[] strList = new String[]{
//                "我",
//                "我爱祖国",
//                "我爱我的祖国",
                "哈哈谢谢"
        };
        for (String str: strList) {
            String ret = sha1.digest(str);
            byte[] expectBytes = systemSha1.digest(str.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b: expectBytes) {
                stringBuilder.append(Integer.toHexString(b & 0xff));
            }
            assertTrue(stringBuilder.toString().equals(ret));
        }
    }
}