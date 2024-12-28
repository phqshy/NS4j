package me.phqsh.ns4j.containers.region.shards;

import lombok.Getter;
import me.phqsh.ns4j.enums.region.OfficerAuthority;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Officer {
    @Getter @XmlElement(name = "NATION")
    private String nation;

    @Getter @XmlElement(name = "OFFICE")
    private String office;

    @Getter @XmlElement(name = "BY")
    private String appointedBy;

    @Getter @XmlElement(name = "ORDER")
    private int order;

    @Getter @XmlElement(name = "TIME")
    private long appointedTimestamp;

    @XmlElement(name = "AUTHORITY")
    private String rawAuthority;

    public List<OfficerAuthority> getAuthority() {
        List<OfficerAuthority> auth = new ArrayList<>();
        for (char i : rawAuthority.toCharArray()) {
            auth.add(OfficerAuthority.getByValue(i));
        }

        return auth;
    }
}
