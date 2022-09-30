package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Faction {
    @XmlAttribute @Getter
    private int id;
    @Getter
    private String NAME;
    @Getter
    private String DESC;
    @Getter
    private String FOUNDED;
    @Getter
    private String REGION;
    @Getter
    private String RNAME;
    @Getter
    private String ENTRY;
    @Getter
    private int SCORE;
    @Getter
    private int PRODUCTION;
    @Getter
    private int NUKES;
    @Getter
    private int SHIELDS;
    @Getter
    private int TARGETS;
    @Getter
    private int LAUNCHES;
    @Getter
    private int INCOMING;
    @Getter
    private int TARGETED;
    @Getter
    private int STRIKES;
    @Getter
    private int RADIATION;
}
