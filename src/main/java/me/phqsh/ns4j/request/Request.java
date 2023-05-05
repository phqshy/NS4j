package me.phqsh.ns4j.request;

import me.phqsh.ns4j.containers.Container;

import java.util.List;
import java.util.Map;

public interface Request {
    Map<String, List<String>> getResponseHeaders() throws IllegalAccessException;
    Container execute();
    String getUrl();
}
