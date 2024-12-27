package me.phqsh.ns4j.containers.region.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

public class WorldAssemblyVote {
    @Getter @XmlElement(name = "FOR")
    private int forVotes;

    @Getter @XmlElement(name = "AGAINST")
    private int againstVotes;
}
