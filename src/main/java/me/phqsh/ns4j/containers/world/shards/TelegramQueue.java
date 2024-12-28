package me.phqsh.ns4j.containers.world.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class TelegramQueue {
    @XmlElement(name = "MANUAL")
    private int manual;

    @XmlElement(name = "MASS")
    private int mass;

    @XmlElement(name = "API")
    private int api;
}
