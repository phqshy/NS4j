package me.phqsh.ns4j.containers.nation;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.shared.WorldAssemblyBadge;
import me.phqsh.ns4j.containers.shared.census.Census;
import me.phqsh.ns4j.containers.shared.census.CensusContainer;
import me.phqsh.ns4j.containers.nation.shards.*;
import me.phqsh.ns4j.enums.CensusType;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name="NATION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Nation extends Container implements Serializable {
    private static final String bannerBaseURL = "https://www.nationstates.net/images/banners/";

    /*
        TODO:
        Fix how census works
     */

    @XmlAttribute @Getter
    private String id;

    //here we have the different types of shards
    @Getter @XmlElement(name = "ADMIRABLE")
    private String admirable;

    @Getter @XmlElementWrapper(name = "ADMIRABLES") @XmlElement(name = "ADMIRABLE")
    private List<String> admirables;

    @Getter @XmlElement(name = "ANIMAL")
    private String animal;

    @Getter @XmlElement(name = "ANIMALTRAIT")
    private String animalTrait;

    @Getter @XmlElement(name = "ISSUES_ANSWERED")
    private int issuesAnswered;

    @Getter @XmlElement(name = "BANNER")
    private String banner;

    @Getter @XmlElementWrapper(name = "BANNERS") @XmlElement(name = "BANNER")
    private List<String> banners;

    @Getter @XmlElement(name = "CAPITAL")
    private String capital;

    @Getter @XmlElement(name = "CATEGORY")
    private String category;

    private Census CENSUS;

    @Getter @XmlElement(name = "CRIME")
    private String crime;

    @Getter @XmlElement(name = "CURRENCY")
    private String currency;

    @Getter @XmlElement(name = "LEADER")
    private String leader;

    @Getter @XmlElement(name = "RELIGION")
    private String religion;

    @Getter @XmlElement(name = "DBID")
    private long dbid;

    @Getter @XmlElementWrapper(name = "DEATHS") @XmlElement(name = "CAUSE")
    private List<DeathCause> deaths;

    @Getter @XmlElement(name = "DEMONYM")
    private String demonym;

    @Getter @XmlElement(name = "DEMONYM2")
    private String demonym2;

    @Getter @XmlElement(name = "DEMONYM2PLURAL")
    private String demonym2Plural;

    @Getter @XmlElement(name = "DISPATCHES")
    private int dispatches;

    @Getter @XmlElementWrapper(name = "DISPATCHLIST") @XmlElement(name = "DISPATCH")
    private List<Dispatch> dispatchList;

    @XmlElement(name = "ENDORSEMENTS")
    private String endorsements;

    /**
     * Unpacked version of the ENDORSEMENTS tag.
     */
    @Getter
    private List<String> endorsementList;

    @Getter @XmlElement(name = "FACTBOOKS")
    private int factbooks;

    @Getter @XmlElementWrapper(name = "FACTBOOKLIST") @XmlElement(name = "FACTBOOK")
    private List<Dispatch> factbookList;

    @Getter @XmlElement(name = "FIRSTLOGIN")
    private long firstLogin;

    @Getter @XmlElement(name = "FLAG")
    private String flag;

    @Getter @XmlElement(name = "FOUNDED")
    private String founded;

    @Getter @XmlElement(name = "FOUNDEDTIME")
    private long foundedTime;

    @Getter @XmlElement(name = "FREEDOM")
    private Freedom freedom;

    @Getter @XmlElement(name = "FULLNAME")
    private String fullName;

    @Getter @XmlElement(name = "GAVOTE")
    private String generalAssemblyVote;

    @Getter @XmlElement(name = "GDP")
    private long GDP;

    @Getter @XmlElement(name = "GOVT")
    private Government government;

    @Getter @XmlElement(name = "GOVTDESC")
    private String governmentDescription;

    @Getter @XmlElement(name = "GOVTPRIORITY")
    private String governmentPriority;

    /**
     * Sequential list of events.
     */
    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<String> happenings;

    @Getter @XmlElement(name = "INCOME")
    private int income;

    @Getter @XmlElement(name = "INDUSTRYDESC")
    private String industryDescription;

    @Getter @XmlElement(name = "INFLUENCE")
    private String influence;

    @Getter @XmlElement(name = "LASTACTIVITY")
    private String lastActivity;

    @Getter @XmlElement(name = "LASTLOGIN")
    private long lastLogin;

    @Getter @XmlElementWrapper(name = "LEGISLATION") @XmlElement(name = "LAW")
    private List<String> legislation;

    @Getter @XmlElement(name = "MAJORINDUSTRY")
    private String majorIndustry;

    @Getter @XmlElement(name = "MOTTO")
    private String motto;

    @Getter @XmlElement(name = "NAME")
    private String name;

    @Getter @XmlElement(name = "NOTABLE")
    private String notable;

    @Getter @XmlElementWrapper(name = "NOTABLES") @XmlElement(name = "NOTABLE")
    private List<String> notables;

    @Getter @XmlElementWrapper(name = "POLICIES") @XmlElement(name = "POLICY")
    private List<Policy> policies;

    @Getter @XmlElement(name = "POOREST")
    private int poorest;

    @Getter @XmlElement(name = "POPULATION")
    private int population;

    /**
     * Value of public sector in millions.
     */
    @Getter @XmlElement(name = "PUBLICSECTOR")
    private double publicSector;

    /**
     * Number of rank of regional census.
     */
    @Getter @XmlElement(name = "RCENSUS")
    private int regionalCensus;

    @Getter @XmlElement(name = "REGION")
    private String region;

    @Getter @XmlElement(name = "RICHEST")
    private int richest;

    @Getter @XmlElement(name = "SCVOTE")
    private String securityCouncilVote;

    @Getter @XmlElement(name = "SECTORS")
    private Sectors sectors;

    @Getter @XmlElement(name = "SENSIBILITIES")
    private String sensibilities;

    @Getter @XmlElement(name = "TAX")
    private double tax;

    @XmlElement(name = "TGCANRECRUIT")
    private int TGCANRECRUIT;

    @XmlElement(name = "TGCANCAMPAIGN")
    private int TGCANCAMPAIGN;

    @Getter @XmlElement(name = "TYPE")
    private String type;

    @Getter @XmlElement(name = "UNSTATUS")
    private String worldAssemblyStatus;

    @Getter @XmlElement(name = "WABADGES")
    private List<WorldAssemblyBadge> worldAssemblyBadges;

    @Getter @XmlElement(name = "WCENSUS")
    private int worldCensus;

    @Getter @XmlElement(name = "ZOMBIE")
    private Zombie zombie;

    private Nation(){
        super();
    }

    //post unmarshal initialization
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        if (CENSUS != null){
            CENSUS.initScales();
        }
        if (this.banner != null){
            this.banner = bannerBaseURL + this.banner + ".jpg";
        }
        if (this.banners != null){
            this.banners.replaceAll(s -> bannerBaseURL + s + ".jpg");
        }
        if (this.endorsements != null){
            this.endorsementList = List.of(this.endorsements.split(","));
        }
    }

    public HashMap<CensusType, CensusContainer> getCENSUS(){
        return CENSUS.getSCALES();
    }

    public boolean canRecruit(){
        return this.TGCANRECRUIT == 1;
    }

    public boolean canCampaign(){
        return this.TGCANCAMPAIGN == 1;
    }
}
