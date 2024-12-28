package me.phqsh.ns4j.containers.wa.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class GenSec {
    @XmlElementWrapper(name = "LEGAL") @XmlElement(name = "LEGAL")
    private List<String> legalVotes;

    @XmlElementWrapper(name = "ILLEGAL") @XmlElement(name = "ILLEGAL")
    private List<String> illegalVotes;

    @XmlElementWrapper(name = "DISCARD") @XmlElement(name = "DISCARD")
    private List<String> discardVotes;

    @XmlElementWrapper(name = "LOG") @XmlElement(name = "ENTRY")
    private List<GenSecLogEntry> decisionLog;
}
