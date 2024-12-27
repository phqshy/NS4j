package me.phqsh.ns4j.containers.shared.poll;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PollOption {
    @Getter @XmlAttribute(name = "id")
    private int optionId;

    @Getter @XmlElement(name = "OPTIONTEXT")
    private String text;

    @Getter @XmlElement(name = "VOTES")
    private int votes;

    @XmlElement(name = "VOTERS")
    private String voters;

    public List<String> getVoters() {
        return List.of(voters.split(":"));
    }
}
