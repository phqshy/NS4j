package me.phqsh.ns4j.containers.wa;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="WA")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorldAssembly implements Container {
    @Getter @XmlAttribute
    private int council;

    @Getter @XmlElement(name = "RESOLUTION")
    private Resolution resolution;

    public enum Council {
        GENERAL_ASSEMBLY(1),
        SECURITY_COUNCIL(2);

        @Getter
        private int id;
        Council(int id) {
            this.id = id;
        }
    }
}
