package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum Buckets implements Options {
    LAW("law"),
    CHANGE("change"),
    DISPATCH("dispatch"),
    RMB("rmb"),
    EMBASSY("embassy"),
    EJECT("eject"),
    ADMIN("admin"),
    MOVE("move"),
    FOUNDING("founding"),
    CTE("cte"),
    VOTE("vote"),
    RESOLUTION("resolution"),
    MEMBER("member"),
    ENDORSEMENT("endo");

    private final String id;

    Buckets(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
