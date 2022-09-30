package me.phqsh.ns4j.containers.shared;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorldAssemblyBadge {
    @Getter
    @XmlAttribute
    private String type;

    @Getter
    @XmlValue
    private int resolutionNumber;
}
