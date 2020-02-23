package io.github.zhengyhn.cryptotool.hashing;

import io.github.zhengyhn.cryptotool.codec.Base64Codec;
import io.github.zhengyhn.cryptotool.codec.CodecAlgorithm;
import io.github.zhengyhn.cryptotool.codec.ICodec;

public class HashingFactory {
    private IHashing sha1 = new Sha1();
    private volatile static HashingFactory instance;

    private HashingFactory() {}

    public static HashingFactory getInstance() {
       if (instance == null) {
           synchronized (HashingFactory.class) {
               if (instance == null) {
                   instance = new HashingFactory();
               }
           }
       }
       return instance;
    }

    public IHashing getCodecImpl(String name) {
        HashingAlgorithm algorithm = HashingAlgorithm.getInstance(name);
        switch (algorithm) {
            case SHA1:
                return sha1;
            default:
                return null;
        }
    }
}
