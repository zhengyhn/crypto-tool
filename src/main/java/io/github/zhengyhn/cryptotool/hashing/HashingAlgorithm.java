package io.github.zhengyhn.cryptotool.hashing;

import java.util.HashMap;
import java.util.Map;

public enum HashingAlgorithm {
    SHA1("sha1"),
    ;

    private String name;
    private static Map<String, HashingAlgorithm> values = new HashMap<>();
    static {
        for (HashingAlgorithm algorithm: HashingAlgorithm.values()) {
            values.put(algorithm.getName(), algorithm);
        }
    }

    HashingAlgorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HashingAlgorithm getInstance(String name) {
        return values.get(name);
    }
}
