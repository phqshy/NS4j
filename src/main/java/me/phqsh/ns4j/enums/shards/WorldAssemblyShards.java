package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum WorldAssemblyShards implements Shards {
    NUMBER_NATIONS("numnations"),
    NUMBER_DELEGATES("numdelegates"),
    DELEGATES("delegates"),
    MEMBERS("members"),
    HAPPENINGS("happenings"),
    PROPOSALS("proposals"),
    RESOLUTION("resolution"),
    VOTERS("voters"),
    DELEGATE_LOG("dellog"),
    LAST_RESOLUTION("lastresolution"),
    DELEGATE_VOTES("delvotes");

    private final String id;

    WorldAssemblyShards(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
