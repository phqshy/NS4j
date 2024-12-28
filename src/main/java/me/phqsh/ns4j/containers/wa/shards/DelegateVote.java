package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class DelegateVote {
    @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    @XmlElement(name = "NATION")
    private String nation;

    @XmlElement(name = "ACTION")
    private String action;

    @XmlElement(name = "VOTES")
    private int votes;
}
