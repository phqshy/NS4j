package me.phqsh.ns4j.sse;

import lombok.Getter;

@Getter
public class SseSourceData {
    private String url;

    public SseSourceData(String url) {
        this.url = url;
    }
}
