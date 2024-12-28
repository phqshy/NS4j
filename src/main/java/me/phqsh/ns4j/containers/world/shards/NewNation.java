package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
public class NewNation {
    @XmlAttribute
    private String name;

    @XmlElement(name = "REGION")
    private String region;

    @XmlElement(name = "FOUNDEDTIME")
    private long foundedTimestamp;
}
