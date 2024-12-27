package me.phqsh.ns4j.exceptions;

public class RequestBuilderException extends RuntimeException {
    public RequestBuilderException(String error) {
        super(error);
    }

    public RequestBuilderException(String error, Throwable throwable) {
        super(error, throwable);
    }
}
