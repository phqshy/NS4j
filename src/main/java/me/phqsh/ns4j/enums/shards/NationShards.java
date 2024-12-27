package me.phqsh.ns4j.enums.shards;

import me.phqsh.ns4j.enums.Shards;

public enum NationShards implements Shards {
    ADMIRABLE("admirable"),
    ADMIRABLES("admirables"),
    ALL("all"),
    ANIMAL("animal"),
    ANIMAL_TRAIT("animaltrait"),
    BUDGET("govt"),
    CAN_COMPAIGN("tgcancompaign"),
    CAN_RECRUIT("tgcanrecruit"),
    CAPITAL("capital"),
    CATEGORY("category"),
    CENSUS("census"),
    CRIME("crime"),
    CURRENCY("currency"),
    DBID("dbid"),
    DEATHS("deaths"),
    DEMONYM("demonym"),
    DEMONYM_PLURAL("demonym2plural"),
    DISPATCHES("dispatches"),
    DISPATCH_LISTS("dispatchlist"),
    ENDORSEMENT("endorsements"),
    FACTBOOKLIST("factbooklist"),
    FACTBOOKS("factbooks"),
    FIRSTLOGIN("firstlogin"),
    FLAG("flag"),
    FOUNDED("founded"),
    FOUNDED_TIME("foundedtime"),
    FREEDOM("freedom"),
    FULL_NAME("fullname"),
    GAVOTE("gavote"),
    GDP("gdp"),
    GOVERNMENT("govtdesc"),
    GOVERNMENT_PRIORITY("govtpriority"),
    HAPPENINGS("happenings"),
    INCOME("income"),
    INDUSTRY("industrydesc"),
    INFLUENCE("influence"),
    ISSUES_ANSWERED("answered"),
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
    REGION("region"),
    RELIGION("religion"),
    RICHEST("richest"),
    SCVOTE("scvote"),
    SECTORS("sectors"),
    SENSIBILITIES("sensibilities"),
    TAX("tax"),
    TYPE("type"),
    WA("wa"),
    WA_BADGES("wabadges"),
    ZOMBIE("zombie");

    private final String id;

    NationShards(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
