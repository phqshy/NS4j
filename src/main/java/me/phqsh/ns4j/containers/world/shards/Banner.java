package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
public class Banner {
    @XmlElement(name = "NAME")
    private String name;

    @XmlElement(name = "VALIDITY")
    private String validity;

    @XmlAttribute
    private String id;
}
