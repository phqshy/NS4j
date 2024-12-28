package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum RmbOptions implements Options {
    LIMIT("limit"),
    OFFSET("offset"),
    FROM_ID("fromid");
    private final String id;

    RmbOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
