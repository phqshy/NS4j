package me.phqsh.ns4j.containers.region;


import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.shared.Happening;
import me.phqsh.ns4j.containers.shared.census.Census;
import me.phqsh.ns4j.containers.shared.census.CensusContainer;
import me.phqsh.ns4j.containers.shared.census.censusrank.CensusRanks;
import me.phqsh.ns4j.enums.CensusType;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name="REGION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Region implements Container {
    @XmlAttribute
    @Getter
    private String id;

    @Getter @XmlElement(name = "BANNER")
    private String banner;

    @Getter @XmlElement(name = "BANNERBY")
    private String bannerAuthor;

    @Getter @XmlElement(name = "BANNERURL")
    private String bannerUrl;

    private Census CENSUS;
    private CensusRanks CENSUSRANKS;

    @Getter @XmlElement(name = "DBID")
    private int databaseId;

    @Getter @XmlElement(name = "DELEGATE")
    private String delegate;

    @Getter @XmlElement(name = "DELEGATEAUTH")
    private String delegateAuthority;

    @Getter @XmlElement(name = "DELEGATEVOTES")
    private int delegateVotes;

    @Getter @XmlElement(name = "DISPATCHES")
    private String dispatches;

    @Getter @XmlElementWrapper(name = "EMBASSIES") @XmlElement(name = "EMBASSY")
    private List<String> embassies;

    @Getter @XmlElement(name = "EMBASSYRMB")
    private String embassyRMBPerms;

    @Getter @XmlElement(name = "FACTBOOK")
    private String factbook;

    @Getter @XmlElement(name = "FLAG")
    private String flag;

    @Getter @XmlElement(name = "FOUNDED")
    private String founded;

    @Getter @XmlElement(name = "FOUNDEDTIME")
    private long foundedTimestamp;

    @Getter @XmlElement(name = "FOUNDER")
    private String founder;

    @Getter @XmlElement(name = "FOUNDERAUTH")
    private String founderAuthority;

    @Getter @XmlElementWrapper(name = "GAVOTE") @XmlElement(name = "FOR")
    private String gaFor;

    @Getter @XmlElementWrapper(name = "GAVOTE") @XmlElement(name = "AGAINST")
    private String gaAgainst;

    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<Happening> happenings;

    @Getter @XmlElementWrapper(name = "HISTORY") @XmlElement(name = "EVENT")
    private List<Happening> history;

    @Getter @XmlElement(name = "LASTUPDATE")
    private long lastUpdate;

    //TODO- implement this
    private String MESSAGES;

    @Getter @XmlElement(name = "NAME")
    private String name;

    private String NATIONS;

    @Getter @XmlElement(name = "NUMNATIONS")
    private int numberNations;

    //TODO- implement this
    private String OFFICERS;

    //TODO- implement this
    private String POLL;

    @Getter @XmlElement(name = "POWER")
    private String power;

    @Getter @XmlElementWrapper(name = "SCVOTE") @XmlElement(name = "FOR")
    private String scFor;

    @Getter @XmlElementWrapper(name = "GAVOTE") @XmlElement(name = "AGAINST")
    private String scAgainst;

    @Getter @XmlElementWrapper(name = "TAGS") @XmlElement(name = "TAG")
    private List<String> tags;

    @Getter @XmlElementWrapper(name = "WABADGES") @XmlElement(name = "WABADGE")
    private List<String> badges;

    //TODO- implement this
    private String ZOMBIE;

    private Region(){
        super();
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        if (CENSUS != null){
            CENSUS.initScales();
        }
    }

    public HashMap<CensusType, CensusContainer> getCensus(){
        return CENSUS.getSCALES();
    }

    public List<CensusContainer> getCensusRanks(){
        return CENSUSRANKS.getRANKS();
    }

    public List<String> getNations(){
        return List.of(NATIONS.split(":"));
    }
}
