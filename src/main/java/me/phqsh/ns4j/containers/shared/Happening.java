package me.phqsh.ns4j.containers.shared;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Happening {
    @Getter @XmlAttribute
    private String id;

    @Getter @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    @Getter @XmlElement(name = "TEXT")
    private String text;
}
