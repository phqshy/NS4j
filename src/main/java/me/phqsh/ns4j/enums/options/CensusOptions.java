package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum CensusOptions implements Options {
    SCALE("scale"),
    MODE("mode"),
    HISTORY_FROM("from"),
    HISTORY_TO("to");

    private final String id;

    CensusOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
