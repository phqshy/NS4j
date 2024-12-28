package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum WorldShards implements Shards {
    BANNER("banner"),
    CENSUS("census"),
    CENSUS_DESC("censusdesc"),
    CENSUS_ID("censusid"),
    CENSUS_NAME("censusname"),
    CENSUS_RANKS("censusranks"),
    CENSUS_TITLE("censustitle"),
    DISPATCH("dispatch"),
    DISPATCH_LIST("dispatchlist"),
    FACTION("faction"),
    FACTIONS("factions"),
    FEATURED_REGION("featuredregion"),
    HAPPENINGS("happenings"),
    LAST_EVENT_ID("lasteventid"),
    NATIONS("nations"),
    NEW_NATIONS("newnations"),
    NEW_NATIONS_DETAILED("newnationdetails"),
    NUMBER_NATIONS("numnations"),
    NUMBER_REGIONS("numregions"),
    POLL("poll"),
    REGIONS("regions"),
    REGIONS_BY_TAG("regionsbytag"),
    TELEGRAM_QUEUE("telegramqueue");

    private final String id;

    WorldShards(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
