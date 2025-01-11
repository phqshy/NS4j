package me.phqsh.ns4j.sse;

import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.InboundSseEvent;
import jakarta.ws.rs.sse.SseEventSource;
import me.phqsh.ns4j.enums.options.Buckets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

// https://stackoverflow.com/a/47697858
public class EventSubscriber {
    private Client client;

    private Map<SseSourceData, SseEventSource> sources;

    private Gson gson;

    public EventSubscriber() {
        client = ClientBuilder.newClient();
        sources = new HashMap<>();
        gson = new Gson();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (SseEventSource source : sources.values()) {
                source.close();
            }
        }));
    }

    public SseSourceData subscribe(Consumer<SseEvent> onEvent, Consumer<Throwable> onError, Runnable onComplete, String url) {
        WebTarget target = client.target(url);

        SseEventSource source = SseEventSource.target(target)
                .reconnectingEvery(5, TimeUnit.SECONDS)
                .build();

        source.register((event -> parseEvent(event, onEvent)), onError, onComplete);
        source.open();

        SseSourceData data = new SseSourceData(url);

        sources.put(data, source);

        return data;
    }

    private String getUrl(Buckets... buckets) {
        StringBuilder url = new StringBuilder("https://www.nationstates.net/api/");

        for (Buckets i : buckets) {
            url.append(i.getId()).append("+");
        }

        return url.toString();
    }

    public SseSourceData subscribe(Consumer<SseEvent> onEvent, Consumer<Throwable> onError, Runnable onComplete, Buckets buckets) {
        return subscribe(onEvent, onError, onComplete, getUrl(buckets));
    }

    public SseSourceData subscribe(Consumer<SseEvent> onEvent, String url) {
        return subscribe(onEvent, this::handleError, () -> {}, url);
    }

    public SseSourceData subscribe(Consumer<SseEvent> onEvent, Buckets... buckets) {
        return subscribe(onEvent, this::handleError, () -> {}, getUrl(buckets));
    }

    private void parseEvent(InboundSseEvent event, Consumer<SseEvent> consumer) {
        SseEvent pojo = gson.fromJson(event.readData(), SseEvent.class);
        if (pojo == null) {
            return;
        }
        consumer.accept(pojo);
    }

    private void handleError(Throwable t) {
        t.printStackTrace();
    }

    public List<SseSourceData> getSources() {
        return sources.keySet().stream().toList();
    }

    public void closeSource(SseSourceData baseUrl) {
        SseEventSource source = sources.remove(baseUrl);
        source.close();
    }
}
