package me.phqsh.ns4j.containers.region.census;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class RegionalCensusRanks {
    @Getter @XmlElementWrapper(name = "NATIONS") @XmlElement(name = "NATION")
    private List<CensusRank> censusRanks;

    @Getter @XmlAttribute
    private int censusId;
}
