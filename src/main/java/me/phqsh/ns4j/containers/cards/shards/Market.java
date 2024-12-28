package me.phqsh.ns4j.containers.cards.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class Market {
    @XmlElement(name = "NATION")
    private String nation;

    @XmlElement(name = "PRICE")
    private double price;

    @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    @XmlElement(name = "TYPE")
    private String type;
}
