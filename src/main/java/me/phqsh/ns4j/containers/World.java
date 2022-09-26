package me.phqsh.ns4j.containers;

import lombok.Getter;
import me.phqsh.ns4j.containers.nday.Faction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WORLD") @XmlAccessorType(XmlAccessType.FIELD)
public class World implements Container{
    @Getter
    private Faction FACTION;
}
