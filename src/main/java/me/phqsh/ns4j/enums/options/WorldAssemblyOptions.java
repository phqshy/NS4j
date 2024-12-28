package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum WorldAssemblyOptions implements Options {
    RESOLUTION_ID("resolution");

    private String id;

    WorldAssemblyOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
