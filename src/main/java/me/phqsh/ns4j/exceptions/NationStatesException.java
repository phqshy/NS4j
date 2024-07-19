package me.phqsh.ns4j.exceptions;

public class NationStatesException extends Exception {
    public NationStatesException(String error) {
        super(error);
    }

    public NationStatesException(String error, Throwable throwable) {
        super(error, throwable);
    }
}
