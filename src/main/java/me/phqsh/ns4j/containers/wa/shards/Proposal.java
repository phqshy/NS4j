package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Proposal extends Legislation {
    @Getter @XmlAttribute
    private String id;

    @XmlElement(name = "APPROVALS")
    private String approvals;

    @Getter @XmlElement(name = "COAUTHOR")
    private String coauthor;

    @Getter @XmlElement(name = "GENSEC")
    private GenSec genSec;

    public List<String> getApprovals() {
        return List.of(approvals.split(":"));
    }
}
