package me.phqsh.ns4j.containers.wa;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="WA")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorldAssembly extends Container {
    @Getter @XmlAttribute
    private int council;

    @Getter @XmlElement(name = "RESOLUTION")
    private Resolution resolution;

    @Getter @XmlElement(name = "LASTRESOLUTION")
    private String lastResolution;
}
