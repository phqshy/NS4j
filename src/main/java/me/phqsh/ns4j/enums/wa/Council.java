package me.phqsh.ns4j.enums.wa;

import lombok.Getter;

public enum Council {
    GENERAL_ASSEMBLY(1),
    SECURITY_COUNCIL(2);

    @Getter
    private int id;
    Council(int id) {
        this.id = id;
    }
}
