package me.phqsh.ns4j.containers.world;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.shared.Dispatch;
import me.phqsh.ns4j.containers.shared.Happening;
import me.phqsh.ns4j.containers.shared.census.CensusRanks;
import me.phqsh.ns4j.containers.shared.census.Scale;
import me.phqsh.ns4j.containers.shared.poll.Poll;
import me.phqsh.ns4j.containers.world.shards.*;
import me.phqsh.ns4j.enums.shards.Census;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "WORLD") @XmlAccessorType(XmlAccessType.FIELD)
public class World extends Container {
    @Getter @XmlElementWrapper(name = "BANNERS") @XmlElement(name = "BANNER")
    private List<Banner> banners;

    @XmlElementWrapper(name = "CENSUS") @XmlElement(name = "SCALE")
    private List<Scale> census;

    @Getter @XmlElement(name = "CENSUSID")
    private int censusId;

    @Getter @XmlElement(name = "CENSUSDESC")
    private CensusDescription censusDescription;

    @Getter @XmlElement(name = "CENSUS")
    private String censusName;

    @Getter @XmlElement(name = "CENSUSRANKS")
    private CensusRanks censusRanks;

    @Getter @XmlElement(name = "CENSUSSCALE")
    private String censusScale;

    @Getter @XmlElement(name = "CENSUSTITLE")
    private String censusTitle;

    @Getter @XmlElement(name = "DISPATCH")
    private Dispatch dispatch;

    @Getter @XmlElementWrapper(name = "DISPATCHLIST") @XmlElement(name = "DISPATCH")
    private List<Dispatch> dispatchList;

    @Getter @XmlElement(name = "FACTION")
    private Faction faction;

    @Getter @XmlElementWrapper(name = "FACTIONS") @XmlElement(name = "FACTION")
    private List<Faction> factionList;

    @Getter @XmlElement(name = "FEATUREDREGION")
    private String featuredRegion;

    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<Happening> happenings;

    @Getter @XmlElement(name = "LASTEVENTID")
    private long lastEventId;

    @XmlElement(name = "NATIONS")
    private String nations;

    @XmlElement(name = "NEWNATIONS")
    private String newNations;

    @Getter @XmlElementWrapper(name = "NEWNATIONDETAILS") @XmlElement(name = "NEWNATION")
    private List<NewNation> newNationDetails;

    @Getter @XmlElement(name = "NUMNATIONS")
    private int numberNations;

    @Getter @XmlElement(name = "NUMREGIONS")
    private int numberRegions;

    @Getter @XmlElement(name = "POLL")
    private Poll poll;

    @XmlElement(name = "REGIONS")
    private String regions;

    @XmlElement(name = "TGQUEUE")
    private TelegramQueue telegramQueue;


    public List<String> getNations() {
        return List.of(nations.split(","));
    }

    public List<String> getRegions() {
        return List.of(regions.split(","));
    }

    public List<String> getNewNations() {
        return List.of(newNations.split(","));
    }

    public Map<Census, Scale> getCensus(){
        Map<Census, Scale> censusScaleMap = new HashMap<>();

        for (Scale i : census) {
            censusScaleMap.put(Census.getByValue(i.getCensusId()), i);
        }

        return censusScaleMap;
    }
}
