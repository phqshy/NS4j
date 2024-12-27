package me.phqsh.ns4j.containers.shared.poll;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class Poll {
    @Getter @XmlAttribute(name = "id")
    private int id;

    @Getter @XmlElement(name = "TITLE")
    private String title;

    @Getter @XmlElement(name = "TEXT")
    private String text;

    @Getter @XmlElement(name = "REGION")
    private String region;

    @Getter @XmlElement(name = "START")
    private long startTimestamp;

    @Getter @XmlElement(name = "STOP")
    private long stopTimestamp;

    @Getter @XmlElement(name = "AUTHOR")
    private String author;

    @Getter @XmlElementWrapper(name = "OPTIONS") @XmlElement(name = "OPTION")
    private List<PollOption> pollOptions;
}
