package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum RegionShards implements Shards {
    BANNER("banner"),
    BANNER_AUTHOR("bannerby"),
    CENSUS("census"),
    CENSUS_RANK("censusranks"),
    DBID("dbid"),
    DELEGATE("delegate"),
    DELEGATE_PERMS("delegateauth"),
    DELEGATE_ENDORSEMENTS("delegatevotes"),
    DISPATCHES("dispatches"),
    EMBASSIES("embassies"),
    EMBASSY_RMB("embassyrmb"),
    FACTBOOK("factbook"),
    FLAG("flag"),
    FOUNDED("founded"),
    FOUNDED_TIME("foundedtime"),
    FOUNDER("founder"),
    FOUNDER_PERMS("founderauth"),
    GA_VOTE("gavote"),
    HAPPENINGS("happenings"),
    HISTORY("history"),
    LAST_UPDATE("lastupdate"),
    MESSAGES("messages"),
    NAME("name"),
    NATIONS("nations"),
    NUMBER_NATIONS("numnations"),
    NUMBER_WA_NATIONS("numwanations"),
    OFFICERS("officers"),
    POLL("poll"),
    INFLUENCE("power"),
    SC_VOTE("scvote"),
    TAGS("tags"),
    WA_BADGES("wabadges"),
    ZOMBIE("zombie");

    private final String id;

    RegionShards(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
