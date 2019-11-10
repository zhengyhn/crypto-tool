package io.github.zhengyhn.cryptotool.codec;

public interface ICodec {
    String encode(String text);
    String decode(String text);
}
