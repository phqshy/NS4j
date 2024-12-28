package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class Legislation {
    @XmlElement(name = "CATEGORY")
    private String category;

    @XmlElement(name = "CREATED")
    private long createdTimestamp;

    @XmlElement(name = "DESC")
    private String description;

    @XmlElement(name = "ID")
    private String proposalId;

    @XmlElement(name = "NAME")
    private String title;

    @XmlElement(name = "OPTION")
    private String option;

    @XmlElement(name = "PROPOSED_BY")
    private String author;
}
