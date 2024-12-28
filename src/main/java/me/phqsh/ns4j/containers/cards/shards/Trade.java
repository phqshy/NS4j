package me.phqsh.ns4j.containers.cards.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class Trade {
    @XmlElement(name = "BUYER")
    private String buyer;

    @XmlElement(name = "SELLER")
    private String seller;

    @XmlElement(name = "PRICE")
    private double price;

    @XmlElement(name = "TIMESTAMP")
    private long timestamp;
}
