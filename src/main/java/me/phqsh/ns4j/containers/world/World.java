package me.phqsh.ns4j.containers.world;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.world.shards.Faction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WORLD") @XmlAccessorType(XmlAccessType.FIELD)
public class World implements Container {
    @Getter @XmlElement(name = "FACTION")
    private Faction faction;

    @Getter @XmlElement(name = "REGIONS")
    private String regions;
}
