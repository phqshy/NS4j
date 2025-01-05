package me.phqsh.ns4j.sse;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class SseEvent {
    @SerializedName("str")
    private String data;

    @SerializedName("id")
    private long eventId;

    @SerializedName("time")
    private long timestamp;
}
