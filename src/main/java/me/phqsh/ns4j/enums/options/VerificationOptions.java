package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum VerificationOptions implements Options {
    NATION("nation"),
    CHECKSUM("checksum");
    private final String id;

    VerificationOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
