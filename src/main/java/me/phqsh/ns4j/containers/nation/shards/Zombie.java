package me.phqsh.ns4j.containers.nation.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Zombie {
    @Getter
    @XmlElement(name = "ZACTION")
    private String zombieAction;

    @Getter
    @XmlElement(name = "ZACTIONINTENDED")
    private String zombieActionIntended;

    @Getter
    @XmlElement(name = "SURVIVORS")
    private long survivors;

    @Getter
    @XmlElement(name = "ZOMBIES")
    private long zombies;

    @Getter
    @XmlElement(name = "DEAD")
    private long dead;
}
