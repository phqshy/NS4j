package me.phqsh.ns4j.containers.census.censusrank;

import lombok.Getter;
import me.phqsh.ns4j.containers.census.CensusContainer;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD) @XmlRootElement(name = "CENSUSRANKS")
public class CensusRanks {
    private CensusRankListContainer NATIONS;

    /**
     * List of nations in the census. Index 0 will be #1, index 1 will be #2, etc.
     */
    @XmlTransient @Getter
    private List<CensusContainer> RANKS;

    public CensusRanks(){}

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        if (NATIONS != null){
            RANKS = NATIONS.getNATION();
        }
    }
}
