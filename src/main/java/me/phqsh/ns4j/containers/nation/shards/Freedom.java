package me.phqsh.ns4j.containers.nation.shards;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Freedom {
    @XmlElement(name = "CIVILRIGHTS")
    private String civilRights;

    @XmlElement(name = "ECONOMY")
    private String economy;

    @XmlElement(name = "POLITICALFREEDOM")
    private String politicalFreedom;
}
