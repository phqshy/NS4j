package me.phqsh.ns4j.containers.verification;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;

public class VerificationResult extends Container {
    @Getter
    private int result;

    public VerificationResult(int result) {
        this.result = result;
    }
}
