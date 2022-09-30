package me.phqsh.ns4j.containers.shared.census.censusrank;

import lombok.Getter;
import me.phqsh.ns4j.containers.shared.census.CensusContainer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CensusRankListContainer {
    @Getter
    private List<CensusContainer> NATION;

    public CensusRankListContainer(){}
}
