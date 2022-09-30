package me.phqsh.ns4j.containers.nation.shards;

import lombok.Getter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class DeathCause {
    @Getter
    @XmlAttribute
    private String type;

    @Getter
    @XmlValue
    private double percent;
}
