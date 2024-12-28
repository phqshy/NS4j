package me.phqsh.ns4j.containers.shared.census;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class CensusRanks {
    @Getter @XmlElementWrapper(name = "NATIONS") @XmlElement(name = "NATION")
    private List<Rank> censusRanks;

    @Getter @XmlAttribute
    private int censusId;
}
