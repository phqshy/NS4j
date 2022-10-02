package me.phqsh.ns4j.containers.region;


import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
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

    @Getter
    private String BANNER;
    @Getter
    private String BANNERBY;

    private Census CENSUS;
    private CensusRanks CENSUSRANKS;
    @Getter
    private String DBID;
    @Getter
    private String DELEGATE;
    @Getter
    private String DELEGATEVOTES;
    @Getter
    private String DISPATCHES;
    @Getter
    private String EMBASSIES;
    @Getter
    private String EMBASSYRMB;
    @Getter
    private String FACTBOOK;
    @Getter
    private String FLAG;
    @Getter
    private String FOUNDED;
    @Getter
    private String FOUNDEDTIME;
    @Getter
    private String FOUNDER;
    @Getter
    private String FOUNDERAUTH;
    @Getter
    private String GAVOTE;
    @Getter
    private String HAPPENINGS;
    @Getter
    private String HISTORY;
    @Getter
    private String LASTUPDATE;
    @Getter
    private String MESSAGES;
    @Getter
    private String NAME;

    private String NATIONS;

    @Getter
    private String NUMNATIONS;
    @Getter
    private String OFFICERS;
    @Getter
    private String POLL;
    @Getter
    private String POWER;
    @Getter
    private String SCVOTE;
    @Getter
    private String TAGS;
    @Getter
    private String WABADGES;
    @Getter
    private String ZOMBIE;

    private Region(){
        super();
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        if (CENSUS != null){
            CENSUS.initScales();
        }
    }

    public HashMap<CensusType, CensusContainer> getCENSUS(){
        return CENSUS.getSCALES();
    }

    public List<CensusContainer> getCensusRanks(){
        return CENSUSRANKS.getRANKS();
    }

    public List<String> getNations(){
        return List.of(NATIONS.split(":"));
    }
}
