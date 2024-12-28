package me.phqsh.ns4j.containers.wa;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.TargetedContainer;
import me.phqsh.ns4j.containers.shared.Happening;
import me.phqsh.ns4j.containers.wa.shards.Proposal;
import me.phqsh.ns4j.containers.wa.shards.Resolution;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="WA")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorldAssembly extends Container implements TargetedContainer {
    @Getter @XmlAttribute
    private int council;

    @Getter @XmlElement(name = "NUMNATIONS")
    private int numberNations;

    @Getter @XmlElement(name = "NUMDELEGATES")
    private int numberDelegates;

    @XmlElement(name = "DELEGATES")
    private String delegates;

    @XmlElement(name = "MEMBERS")
    private String members;

    @Getter @XmlElementWrapper(name = "HAPPENINGS") @XmlElement(name = "EVENT")
    private List<Happening> happenings;

    @Getter @XmlElementWrapper(name = "PROPOSALS") @XmlElement(name = "PROPOSAL")
    private List<Proposal> proposals;

    @Getter @XmlElement(name = "RESOLUTION")
    private Resolution resolution;

    @Getter @XmlElement(name = "LASTRESOLUTION")
    private String lastResolution;

    public List<String> getDelegates() {
        return List.of(delegates.split(","));
    }

    public List<String> getMembers() {
        return List.of(members.split(","));
    }
}
