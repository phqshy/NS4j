package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum WorldAssemblyShards implements Shards {
    DELEGATES("delegates"),
    DELEGATE_LOG("dellog"),
    DELEGATE_VOTES("delvotes"),
    HAPPENINGS("happenings"),
    LAST_RESOLUTION("lastresolution"),
    MEMBERS("members"),
    NUMBER_DELEGATES("numdelegates"),
    NUMBER_NATIONS("numnations"),
    PROPOSALS("proposals"),
    RESOLUTION("resolution"),
    VOTERS("voters");

    private final String id;

    WorldAssemblyShards(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
