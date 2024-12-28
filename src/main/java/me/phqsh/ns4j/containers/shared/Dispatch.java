package me.phqsh.ns4j.containers.shared;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

//this is reused for factbook since they're the same data under different names
@XmlAccessorType(XmlAccessType.FIELD)
public class Dispatch {
    @Getter @XmlAttribute
    private int id;

    @Getter @XmlElement(name = "TITLE")
    private String title;

    @Getter @XmlElement(name = "AUTHOR")
    private String author;

    @Getter @XmlElement(name = "CATEGORY")
    private String category;

    @Getter @XmlElement(name = "SUBCATEGORY")
    private String subcategory;

    /**
     * Unix timestamp of when this dispatch was created.
     */
    @Getter @XmlElement(name = "CREATED")
    private long created;

    /**
     * Unix timestamp of when this dispatch was edited.
     */
    @Getter @XmlElement(name = "EDITED")
    private long edited;

    @Getter @XmlElement(name = "VIEWS")
    private int views;

    @Getter @XmlElement(name = "SCORE")
    private int score;

    @Getter @XmlElement(name = "TEXT")
    private String text;
}
