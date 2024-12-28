package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum HappeningsOptions implements Options {
    VIEW("view"),
    FILTER("filter"),
    LIMIT("limit"),
    SINCE_ID("sinceid"),
    BEFORE_ID("beforeid"),
    SINCE_TIME("sincetime"),
    BEFORE_TIME("beforetime");

    private final String id;

    HappeningsOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
