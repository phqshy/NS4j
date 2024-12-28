package me.phqsh.ns4j.enums.options;

import me.phqsh.ns4j.enums.Options;

public enum DispatchFilterOptions implements Options {
    AUTHOR("dispatchauthor"),
    CATEGORY("dispatchcategory"),
    SORT("dispatchsort");

    private final String id;

    DispatchFilterOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
