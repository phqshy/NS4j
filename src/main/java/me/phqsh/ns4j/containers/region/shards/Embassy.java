package me.phqsh.ns4j.containers.region.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Getter
public class Embassy {
    @XmlAttribute(name = "type")
    private String status;

    @XmlValue
    private String region;

    public void afterUnmarshall() {
        if (status == null) {
            status = "established";
        }
    }
}
