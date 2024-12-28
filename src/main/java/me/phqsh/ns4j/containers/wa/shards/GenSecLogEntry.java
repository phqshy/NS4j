package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class GenSecLogEntry {
    @XmlElement(name = "DECISION")
    private String decision;

    @XmlElement(name = "NATION")
    private String nation;

    @XmlElement(name = "REASON")
    private String reason;

    @XmlElement(name = "T")
    private long timestamp;
}
