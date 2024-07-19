package me.phqsh.ns4j.containers.region;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Post {
    @XmlAttribute
    @Getter
    private String id;

    @Getter
    @XmlElement(name = "TIMESTAMP")
    private long postedTimestamp;

    @Getter
    @XmlElement(name = "NATION")
    private String nation;

    @Getter
    @XmlElement(name = "EDITED")
    private long editedTimestamp;

    @Getter
    @XmlElement(name = "LIKES")
    private int likes;

    @XmlElement(name = "LIKERS")
    private String likers;

    @Getter
    @XmlElement(name = "MESSAGE")
    private String message;

    @XmlElement(name = "STATUS")
    private int status;

    @Getter
    @XmlElement(name = "SUPPRESSOR")
    private String suppressor;

    public String[] getLikers() {
        if (this.likers == null) return new String[]{};
        return this.likers.split(":");
    }

    public PostStatus getStatus() {
        return PostStatus.getValue(this.status);
    }

    public enum PostStatus{
        REGULAR(0),
        SUPPRESSED(1),
        DELETED(2),
        MOD_SUPPRESSED(9);

        @Getter
        private int status;

        PostStatus(int status) {
            this.status = status;
        }

        private static PostStatus getValue(int id) {
            for (PostStatus i : PostStatus.values()) {
                if (i.getStatus() == id) {
                    return i;
                }
            }

            throw new RuntimeException();
        }
    }
}
