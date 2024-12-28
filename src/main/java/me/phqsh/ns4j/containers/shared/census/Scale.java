package me.phqsh.ns4j.containers.shared.census;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Scale {
    @Getter @XmlElement(name = "SCORE")
    private double score;
    @Getter @XmlElement(name = "RANK")
    private long rank;
    @Getter @XmlElement(name = "RRANK")
    private String regionalRank;

    @Getter @XmlAttribute
    private int censusId;
}
