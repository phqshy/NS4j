package me.phqsh.ns4j.containers;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ZombieData {
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

    public ZombieData(){
    }
}
