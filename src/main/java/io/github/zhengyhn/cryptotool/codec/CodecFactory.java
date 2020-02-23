package io.github.zhengyhn.cryptotool.codec;

public class CodecFactory {
    private ICodec base64Codec = new Base64Codec();
    private volatile static CodecFactory instance;

    private CodecFactory() {}

    public static CodecFactory getInstance() {
       if (instance == null) {
           synchronized (CodecFactory.class) {
               if (instance == null) {
                   instance = new CodecFactory();
               }
           }
       }
       return instance;
    }

    public ICodec getCodecImpl(String name) {
        CodecAlgorithm algorithm = CodecAlgorithm.getInstance(name);
        switch (algorithm) {
            case BASE64:
                return base64Codec;
            default:
                return null;
        }
    }
}
