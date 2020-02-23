package io.github.zhengyhn.cryptotool.codec;

import org.junit.Test;

import java.io.IOException;
import java.util.Base64;

import static org.junit.Assert.*;

public class Base64CodecTest {
    @Test
    public void testEncodeEmptyTextShouldReturnEmptyString() {
        String text = "";
        String result = new Base64Codec().encode(text);
        assertEquals(result, "");
    }

    @Test
    public void testEncodeNullShouldReturnEmptyString() {
        String text = null;
        String result = new Base64Codec().encode(text);
        assertEquals(result, "");
    }

    @Test
    public void testEncodeNormalTextShouldSuccess() {
        String text = "Man";
        String result = new Base64Codec().encode(text);
        assertEquals(result, "TWFu");
    }

    @Test
    public void testEncodeTextPaddingOneCharShouldSuccess() {
        String text = "Ma";
        String result = new Base64Codec().encode(text);
        assertEquals(result, "TWE=");
    }

    @Test
    public void testEncodeTextPaddingTwoCharShouldSuccess() {
        String text = "M";
        String result = new Base64Codec().encode(text);
        assertEquals(result, "TQ==");
    }

    @Test
    public void testEncodeRandomTextShouldReturnResultTheSameAsSunImplementation() {
        String text = "sldfjlsdfjslfjlsdfjoewirunvc,nxvo293420-;ajlvgufe9w";
        String result = new Base64Codec().encode(text);
        String sunResult = new String(Base64.getEncoder().encode(text.getBytes()));
        assertEquals(result, sunResult);
    }

    @Test
    public void testDecodeEmptyTextShouldReturnEmptyString() {
        String text = "";
        String result = new Base64Codec().decode(text);
        assertEquals(result, "");
    }

    @Test
    public void testDecodeNullShouldReturnEmptyString() {
        String text = null;
        String result = new Base64Codec().decode(text);
        assertEquals(result, "");
    }

    @Test
    public void testDecodeNormalTextShouldSuccess() {
        String text = "TWFu";
        String result = new Base64Codec().decode(text);
        assertEquals(result, "Man");
    }

    @Test
    public void testDecodeTextPaddingOneCharShouldSuccess() {
        String text = "TWE=";
        String result = new Base64Codec().decode(text);
        assertEquals(result, "Ma");
    }

    @Test
    public void testDecodeTextPaddingTwoCharShouldSuccess() {
        String text = "TQ==";
        String result = new Base64Codec().decode(text);
        assertEquals(result, "M");
    }

    @Test
    public void testDecodeRandomTextShouldReturnResultTheSameAsSunImplementation() {
        String text = new Base64Codec().encode("sldfjlsdfjslfjlsdfjoewirunvc,nxvo293420-;ajlvgufe9w");
        String result = new Base64Codec().decode(text);
        String sunResult = "";
        try {
             sunResult = new String(Base64.getDecoder().decode(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(result, sunResult);
    }
}