package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
public class Faction {
    @XmlAttribute 
    private int id;
    
    @XmlElement(name = "NAME")
    private String name;
    
    @XmlElement(name = "DESC")
    private String description;
    
    @XmlElement(name = "FOUNDED")
    private long foundedTimestamp;
    
    @XmlElement(name = "REGION")
    private String region;
    
    @XmlElement(name = "RNAME")
    private String regionName;
    
    @XmlElement(name = "ENTRY")
    private int entry;
    
    @XmlElement(name = "SCORE")
    private int score;
    
    @XmlElement(name = "PRODUCTION")
    private int production;
    
    @XmlElement(name = "NUKES")
    private int nukes;
    
    @XmlElement(name = "SHIELDS")
    private int shields;
    
    @XmlElement(name = "TARGETS")
    private int targets;
    
    @XmlElement(name = "LAUNCHES")
    private int launches;
    
    @XmlElement(name = "INCOMING")
    private int incoming;
    
    @XmlElement(name = "TARGETED")
    private int targeted;
    
    @XmlElement(name = "STRIKES")
    private int strikes;
    
    @XmlElement(name = "RADIATION")
    private int radiation;

    // only for use with the FACTIONS shard
    @XmlElement(name = "NATIONS")
    private int nations;
}
