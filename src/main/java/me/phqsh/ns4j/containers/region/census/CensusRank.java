package me.phqsh.ns4j.containers.region.census;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

public class CensusRank {
    @Getter @XmlElement(name = "NAME")
    private String name;

    @Getter @XmlElement(name = "RANK")
    private int rank;

    @Getter @XmlElement(name = "SCORE")
    private double score;
}
