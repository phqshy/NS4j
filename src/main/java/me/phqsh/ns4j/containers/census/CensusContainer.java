package me.phqsh.ns4j.containers.census;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CensusContainer {
    @Getter
    private String NAME;
    @Getter
    private String SCORE;
    @Getter
    private String RANK;
    @Getter
    private String RRANK;
    @Getter @XmlAttribute
    private int id;
}
