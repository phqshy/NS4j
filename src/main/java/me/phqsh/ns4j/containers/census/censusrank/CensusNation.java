package me.phqsh.ns4j.containers.census.censusrank;

import lombok.Getter;
import me.phqsh.ns4j.containers.census.CensusScale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CensusNation {
    @Getter
    private List<CensusNationScore> NATION;

    public CensusNation(){}

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CensusNationScore{
        @Getter
        private String NAME;
        @Getter
        private String SCORE;
        @Getter
        private String RANK;

        public CensusNationScore(){}
    }
}
