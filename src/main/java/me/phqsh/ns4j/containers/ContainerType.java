package me.phqsh.ns4j.containers;

import lombok.Getter;

public enum ContainerType {
    NATION("nation"),
    REGION("region"),
    WA("wa"),
    WORLD("q");

    @Getter
    private final String urlSegment;
    private ContainerType(String urlSegment) {
        this.urlSegment = urlSegment;
    }
}
