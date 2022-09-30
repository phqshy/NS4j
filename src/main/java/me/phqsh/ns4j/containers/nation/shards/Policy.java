package me.phqsh.ns4j.containers.nation.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Policy {
    private static final String bannerBaseURL = "https://www.nationstates.net/images/banners/";

    @Getter
    @XmlElement(name = "NAME")
    private String name;

    @Getter
    @XmlElement(name = "PIC")
    private String icon;

    @Getter
    @XmlElement(name = "CAT")
    private String category;

    @Getter
    @XmlElement(name = "DESC")
    private String description;

    public void afterUnmarshal() {
        this.icon = bannerBaseURL + this.icon + ".jpg";
    }
}
