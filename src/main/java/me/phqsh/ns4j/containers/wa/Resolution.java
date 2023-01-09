package me.phqsh.ns4j.containers.wa;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class Resolution {
    @Getter @XmlElement(name = "CATEGORY")
    private String category;

    @Getter @XmlElement(name = "CREATED")
    private long createdTimestamp;

    @Getter @XmlElement(name = "PROMOTED")
    private long promotedTimestamp;

    @Getter @XmlElement(name = "IMPLEMENTED")
    private long implementedTimestamp;

    @Getter @XmlElement(name = "NAME")
    private String title;

    @Getter @XmlElement(name = "DESC")
    private String description;

    @Getter @XmlElement(name = "PROPOSED_BY")
    private String author;

    @Getter @XmlElementWrapper(name = "COAUTHOR") @XmlElement(name = "N")
    private List<String> coauthors;

    @Getter @XmlElement(name = "OPTION")
    private String option;

    //global id
    @Getter @XmlElement(name = "RESID")
    private int resId;

    //council id
    @Getter @XmlElement(name = "COUNCILID")
    private int id;

    @Getter @XmlElement(name = "ID")
    private String stringId;

    @Getter @XmlElement(name = "TOTAL_VOTES_FOR")
    private int totalVotesFor;

    @Getter @XmlElement(name = "TOTAL_VOTES_AGAINST")
    private int totalVotesAgainst;

    @Getter @XmlElement(name = "COUNCIL")
    private int council;

    @Getter @XmlElement(name = "REPEALED")
    private int repealedGlobalId;

    @Getter @XmlElement(name = "REPEALED_BY")
    private int repealedId;

    @Getter @XmlElement(name = "REPEALS_COUNCILID")
    private int repealsId;

    @Getter @XmlElement(name = "REPEALS_RESID")
    private int repealsGlobalId;

    /**
     * If this is FALSE, the resolution has not been passed.
     * If this is TRUE, the resolution has been passed.
     * Some fields may be null if it hasn't been passed, and vice versa.
     * Read the API docs for more information
     */
    @Getter
    private boolean enacted;

    /**
     * Marks if the resolution was repealed.
     */
    @Getter
    private boolean repealed;

    /**
     * Marks if the resolution repeals a previous one.
     */
    @Getter
    private boolean repeals;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        enacted = resId != 0;
        repealed = repealedGlobalId != 0;
        repeals = repealsGlobalId != 0;
    }
}
