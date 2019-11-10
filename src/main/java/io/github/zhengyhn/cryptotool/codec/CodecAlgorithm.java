package io.github.zhengyhn.cryptotool.codec;

import java.util.HashMap;
import java.util.Map;

public enum CodecAlgorithm {
    BASE64("base64"),
    ;

    private String name;
    private static Map<String, CodecAlgorithm> values = new HashMap<>();
    static {
        for (CodecAlgorithm algorithm: CodecAlgorithm.values()) {
            values.put(algorithm.getName(), algorithm);
        }
    }

    CodecAlgorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CodecAlgorithm getInstance(String name) {
        return values.get(name);
    }
}
