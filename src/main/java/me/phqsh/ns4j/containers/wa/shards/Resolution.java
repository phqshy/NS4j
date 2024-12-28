package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class Resolution extends Legislation {
    @XmlElement(name = "PROMOTED")
    private long promotedTimestamp;

    @XmlElement(name = "IMPLEMENTED")
    private long implementedTimestamp;

    @XmlElementWrapper(name = "COAUTHOR") @XmlElement(name = "N")
    private List<String> coauthors;

    //global id
    @XmlElement(name = "RESID")
    private int resolutionId;

    //council id
    @XmlElement(name = "COUNCILID")
    private int councilId;

    @XmlElement(name = "TOTAL_VOTES_FOR")
    private int totalVotesFor;

    @XmlElement(name = "TOTAL_VOTES_AGAINST")
    private int totalVotesAgainst;

    @XmlElement(name = "COUNCIL")
    private int council;

    @XmlElement(name = "REPEALED")
    private int repealedGlobalId;

    @XmlElement(name = "REPEALED_BY")
    private int repealedId;

    @XmlElement(name = "REPEALS_COUNCILID")
    private int repealsId;

    @XmlElement(name = "REPEALS_RESID")
    private int repealsGlobalId;

    @XmlElementWrapper(name = "VOTES_AGAINST") @XmlElement(name = "N")
    private List<String> votersAgainst;

    @XmlElementWrapper(name = "VOTES_FOR") @XmlElement(name = "N")
    private List<String> votersFor;

    @XmlElementWrapper(name = "VOTES_TRACK_AGAINST") @XmlElement(name = "N")
    private List<Integer> voteTrackAgainst;

    @XmlElementWrapper(name = "VOTES_TRACK_FOR") @XmlElement(name = "N")
    private List<Integer> voteTrackFor;

    @XmlElementWrapper(name = "DELLOG") @XmlElement(name = "ENTRY")
    private List<DelegateVote> delegateLog;

    @XmlElementWrapper(name = "DELVOTES_FOR") @XmlElement(name = "DELEGATE")
    private List<DelegateVote> delegateVotesFor;

    @XmlElementWrapper(name = "DELVOTES_AGAINST") @XmlElement(name = "DELEGATE")
    private List<DelegateVote> delegateVotesAgainst;

    /**
     * If this is FALSE, the resolution has not been passed.
     * If this is TRUE, the resolution has been passed.
     * Some fields may be null if it hasn't been passed, and vice versa.
     * Read the API docs for more information
     */
    private boolean enacted;

    /**
     * Marks if the resolution was repealed.
     */
    private boolean repealed;

    /**
     * Marks if the resolution repeals a previous one.
     */
    private boolean repeals;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        enacted = resolutionId != 0;
        repealed = repealedGlobalId != 0;
        repeals = repealsGlobalId != 0;
    }
}
