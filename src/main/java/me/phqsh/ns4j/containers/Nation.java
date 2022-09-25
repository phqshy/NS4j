package me.phqsh.ns4j.containers;

import lombok.Getter;
import me.phqsh.ns4j.containers.census.Census;
import me.phqsh.ns4j.containers.census.CensusScale;
import me.phqsh.ns4j.containers.shards.Zombie;
import me.phqsh.ns4j.enums.CensusType;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;

@XmlRootElement(name="NATION")
@XmlAccessorType(XmlAccessType.FIELD)
public class Nation implements Serializable, Container{
    @XmlAttribute @Getter
    private String id;

    //here we have the different types of shards
    @Getter
    private String ADMIRABLE;
    @Getter
    private String ADMIRABLES;
    @Getter
    private String ANIMAL;
    @Getter
    private String ANIMALTRAIT;
    @Getter
    private String ISSUES_ANSWERED;
    @Getter
    private String BANNER;
    @Getter
    private String BANNERS;
    @Getter
    private String CAPITAL;
    @Getter
    private String CATEGORY;

    private Census CENSUS;

    @Getter
    private String CRIME;
    @Getter
    private String CURRENCY;
    @Getter
    private String LEADER;
    @Getter
    private String RELIGION;
    @Getter
    private String DBID;
    @Getter
    private String DEATHS;
    @Getter
    private String DEMONYM;
    @Getter
    private String DEMONYM2;
    @Getter
    private String DEMONYM2PLURAL;
    @Getter
    private String DISPATCHES;
    @Getter
    private String DISPATCHLIST;
    @Getter
    private String ENDORSEMENTS;
    @Getter
    private String FACTBOOKS;
    @Getter
    private String FACTBOOKLIST;
    @Getter
    private String FIRSTLOGIN;
    @Getter
    private String FLAG;
    @Getter
    private String FOUNDED;
    @Getter
    private String FOUNDEDTIME;
    @Getter
    private String FREEDOM;
    @Getter
    private String FULLNAME;
    @Getter
    private String GAVOTE;
    @Getter
    private String GDP;
    @Getter
    private String GOVT;
    @Getter
    private String GOVTDESC;
    @Getter
    private String GOVTPRIORITY;
    @Getter
    private String HAPPENINGS;
    @Getter
    private String INCOME;
    @Getter
    private String INDUSTRYDESC;
    @Getter
    private String INFLUENCE;
    @Getter
    private String LASTACTIVITY;
    @Getter
    private String LASTLOGIN;
    @Getter
    private String LEGISLATION;
    @Getter
    private String MAJORINDUSTRY;
    @Getter
    private String MOTTO;
    @Getter
    private String NAME;
    @Getter
    private String NOTABLE;
    @Getter
    private String NOTABLES;
    @Getter
    private String POLICIES;
    @Getter
    private String POOREST;
    @Getter
    private String POPULATION;
    @Getter
    private String PRIVATESECTOR;
    @Getter
    private String RCENSUS;
    @Getter
    private String REGION;
    @Getter
    private String RICHEST;
    @Getter
    private String SCVOTE;
    @Getter
    private String SECTORS;
    @Getter
    private String SENSIBILITIES;
    @Getter
    private String TAX;
    @Getter
    private String TGCANRECRUIT;
    @Getter
    private String TGCANCAMPAIGN;
    @Getter
    private String TYPE;
    @Getter
    private String UNSTATUS;
    @Getter
    private String WABADGES;
    @Getter
    private String WCENSUS;
    @Getter
    private Zombie ZOMBIE;

    private Nation(){
        super();
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        if (CENSUS != null){
            CENSUS.initScales();
        }
    }

    public HashMap<CensusType, CensusScale> getCENSUS(){
        return CENSUS.getSCALES();
    }
}
