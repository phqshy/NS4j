package me.phqsh.ns4j.containers.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Zombie {
    @Getter
    private String ZACTION;
    @Getter
    private String ZACTIONINTENDED;
    @Getter
    private int SURVIVORS;
    @Getter
    private int ZOMBIES;
    @Getter
    private int DEAD;

    public Zombie(){
    }
}
