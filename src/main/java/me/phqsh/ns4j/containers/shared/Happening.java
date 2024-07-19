package me.phqsh.ns4j.containers.shared;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Happening {
    @Getter
    @XmlAttribute
    private String id;

    @Getter
    @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    @Getter
    @XmlElement(name = "TEXT")
    private String text;
}
