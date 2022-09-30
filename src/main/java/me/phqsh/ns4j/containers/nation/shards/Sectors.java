package me.phqsh.ns4j.containers.nation.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sectors {
    @Getter
    @XmlElement(name = "BLACKMARKET")
    private double blackMarket;

    @Getter
    @XmlElement(name = "GOVERNMENT")
    private double government;

    @Getter
    @XmlElement(name = "INDUSTRY")
    private double industry;

    @Getter
    @XmlElement(name = "PUBLIC")
    private double publicSector;
}
