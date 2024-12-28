package me.phqsh.ns4j.containers.verification;

import me.phqsh.ns4j.containers.Container;

public class VerificationResult extends Container {
    private int result;

    public boolean verified() {
        return result == 1;
    }

    public VerificationResult(int result) {
        this.result = result;
    }
}
