package me.phqsh.ns4j.enums;

public enum NationShards {
    //nation shards
    ADMIRABLE("admirable"),
    ADMIRABLES("admirables"),
    ANIMAL("animal"),
    ANIMAL_TRAIT("animaltrait"),
    ISSUES_ANSWERED("answered"),
    CAPITAL("capital"),
    CATEGORY("category"),
    CRIME("crime"),
    CURRENCY("currency"),
    DBID("dbid"),
    DEATHS("deaths"),
    DEMONYM("demonym"),
    DEMONYM_PLURAL("demonym2plural"),
    DISPATCHES("dispatches"),
    DISPATCH_LISTS("dispatchlist"),
    ENDORSEMENT("endorsements"),
    FACTBOOKS("factbooks"),
    FACTBOOKLIST("factbooklist"),
    FIRSTLOGIN("firstlogin"),
    FLAG("flag"),
    FOUNDED("founded"),
    FOUNDED_TIME("foundedtime"),
    FREEDOM("freedom"),
    FULL_NAME("fullname"),
    GAVOTE("gavote"),
    GDP("gdp"),
    BUDGET("govt"),
    GOVERNMENT("govtdesc"),
    GOVERNMENT_PRIORITY("govtpriority"),
    HAPPENINGS("happenings"),
    INCOME("income"),
    INDUSTRY("industrydesc"),
    INFLUENCE("influence"),
    LAST_ACTIVITY("lastactivity"),
    LAST_LOGIN("lastlogin"),
    LEADER("leader"),
    LEGISLATION("legislation"),
    MAJOR_INDUSTRY("majorindustry"),
    MOTTO("motto"),
    NAME("name"),
    NOTABLE("notable"),
    NOTABLES("notables"),
    POLICIES("policies"),
    POOREST("poorest"),
    POPULATION("population"),
    PUBLIC_SECTOR("publicsector"),
    REGION_CENSUS("rcensus"),
    RELIGION("religion"),
    RICHEST("richest"),
    SCVOTE("scvote"),
    SECTORS("sectors"),
    SENSIBILITIES("sensibilities"),
    TAX("tax"),
    CAN_RECRUIT("tgcanrecruit"),
    CAN_COMPAIGN("tgcancompaign"),
    TYPE("type"),
    WA("wa"),
    WA_BADGES("wabadges"),
    WORLD_CENSUS("wcensus"),
    ZOMBIE("zombie");

    private final String id;

    NationShards(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
