package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
public class CensusDescription {
    @XmlElement(name = "NDESC")
    private String nationDescription;

    @XmlElement(name = "RDESC")
    private String regionDescription;

    @XmlAttribute
    private int id;
}
