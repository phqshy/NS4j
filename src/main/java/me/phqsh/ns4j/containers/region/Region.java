package me.phqsh.ns4j.containers.region;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.region.shards.Officer;
import me.phqsh.ns4j.containers.region.shards.Post;
import me.phqsh.ns4j.containers.region.shards.WorldAssemblyVote;
import me.phqsh.ns4j.containers.shared.Happening;
import me.phqsh.ns4j.containers.shared.census.Rank;
import me.phqsh.ns4j.containers.shared.census.CensusRanks;
import me.phqsh.ns4j.containers.shared.Zombie;
import me.phqsh.ns4j.containers.shared.census.Scale;
import me.phqsh.ns4j.containers.shared.poll.Poll;
import me.phqsh.ns4j.enums.shards.Census;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name="REGION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Region extends Container {
    @Getter @XmlAttribute
    private String id;

    @Getter @XmlElement(name = "BANNER")
    private String banner;

    @Getter @XmlElement(name = "BANNERBY")
    private String bannerAuthor;

    @Getter @XmlElement(name = "BANNERURL")
    private String bannerUrl;

    @XmlElementWrapper(name = "CENSUS") @XmlElement(name = "SCALE")
    private List<Scale> census;

    @XmlElement(name = "CENSUSRANKS")
    private CensusRanks censusRanks;

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
    private String embassyRmbPerms;

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

    @Getter @XmlElement(name = "GAVOTE")
    private WorldAssemblyVote generalAssemblyVotes;

    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<Happening> happenings;

    @Getter @XmlElementWrapper(name = "HISTORY") @XmlElement(name = "EVENT")
    private List<Happening> history;

    @Getter @XmlElement(name = "LASTUPDATE")
    private long lastUpdate;

    @Getter @XmlElementWrapper(name = "MESSAGES") @XmlElement(name = "POST")
    private List<Post> messages;

    @Getter @XmlElement(name = "NAME")
    private String name;

    @XmlElement(name = "NATIONS")
    private String nations;

    @Getter @XmlElement(name = "NUMNATIONS")
    private int numberNations;

    @Getter @XmlElement(name = "NUMUNNATIONS")
    private int numberWaNations;

    @Getter @XmlElementWrapper(name = "OFFICERS") @XmlElement(name = "OFFICER")
    private List<Officer> officers;

    @Getter @XmlElement(name = "POLL")
    private Poll poll;

    @Getter @XmlElement(name = "POWER")
    private String power;

    @Getter @XmlElement(name = "SCVOTE")
    private WorldAssemblyVote securityCouncilVotes;

    @Getter @XmlElementWrapper(name = "TAGS") @XmlElement(name = "TAG")
    private List<String> tags;

    @Getter @XmlElementWrapper(name = "WABADGES") @XmlElement(name = "WABADGE")
    private List<String> badges;

    @Getter @XmlElement(name = "ZOMBIE")
    private Zombie zombie;

    private Region(){
        super();
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
    }

    public Map<Census, Scale> getCensus(){
        Map<Census, Scale> censusScaleMap = new HashMap<>();

        for (Scale i : census) {
            censusScaleMap.put(Census.getByValue(i.getCensusId()), i);
        }

        return censusScaleMap;
    }

    public List<Rank> getCensusRanks() {
        return this.censusRanks.getCensusRanks();
    }

    public List<String> getNations(){
        return List.of(nations.split(":"));
    }
}
