package me.phqsh.ns4j.request.http;

import me.phqsh.ns4j.containers.Container;

import java.util.List;
import java.util.Map;

public interface HttpRequest {
    Map<String, List<String>> getResponseHeaders() throws IllegalAccessException;
    Container execute(String userAgent);
    String getUrl();
}
