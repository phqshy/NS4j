package me.phqsh.ns4j.containers.world;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.shared.Happening;
import me.phqsh.ns4j.containers.world.shards.Faction;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "WORLD") @XmlAccessorType(XmlAccessType.FIELD)
public class World extends Container {
    @Getter @XmlElement(name = "FACTION")
    private Faction faction;

    @Getter @XmlElement(name = "REGIONS")
    private String regions;

    @XmlElement(name = "NEWNATIONS")
    private String newNations;

    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<Happening> happenings;

    public List<String> getNewNations() {
        return List.of(newNations.split(","));
    }
}
