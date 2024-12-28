package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum CardShards implements Shards {
    INFO("info"),
    MARKETS("markets"),
    OWNERS("owners"),
    TRADES("trades");

    private final String id;

    CardShards(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
