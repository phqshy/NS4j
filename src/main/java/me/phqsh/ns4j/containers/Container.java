package me.phqsh.ns4j.containers;

import lombok.Getter;
import lombok.Setter;

public class Container {
    @Getter @Setter
    private long timestamp = 0L;
    @Getter @Setter
    private String containerType;
}
