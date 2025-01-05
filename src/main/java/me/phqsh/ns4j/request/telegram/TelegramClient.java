package me.phqsh.ns4j.request.telegram;

import lombok.Setter;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TelegramClient {
    private LinkedList<QueuedTelegram> telegramQueue;
    private Map<QueuedTelegram, CompletableFuture<Boolean>> futureMap;
    private boolean running;

    private List<Consumer<SentTelegram>> hooks;

    @Setter
    private String userAgent;

    public TelegramClient() {
        this.telegramQueue = new LinkedList<>();
        this.futureMap = new HashMap<>();
    }

    public CompletableFuture<Boolean> queueTelegram(QueuedTelegram telegram) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        futureMap.put(telegram, future);
        telegramQueue.add(telegram);

        if (!running) {
            runTelegramQueue();
        }

        return future;
    }

    private void sendTelegram(String nation, String apiKey, String tgid, String secretKey) throws IOException {
        String url = "https://www.nationstates.net/cgi-bin/api.cgi?a=sendTG&client=%APIKEY%&tgid=%TGID%&key=%SECRET%&to=%NATION%"
                .replace("%APIKEY%", apiKey)
                .replace("%TGID%", tgid)
                .replace("%SECRET%", secretKey)
                .replace("%NATION%", nation);

        URL url1 = new URL(url);
        HttpsURLConnection is = (HttpsURLConnection) url1.openConnection();
        is.setRequestProperty("User-Agent", userAgent);
        int status = is.getResponseCode();

        if (status == 429){
            System.err.println("Reached rate limit! Slowing down...");
            try {
                Thread.sleep(180 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //TODO: logic after sending if needed
        InputStream resp = is.getInputStream();
        Map<String, List<String>> responseHeaders = is.getHeaderFields();

        // buffer here is 1 because this works in tandem with RequestClient
        // the default buffer there is 5, so there can be 4 telegrams sent after buffer hit
        int remaining = Integer.parseInt(responseHeaders.get("RateLimit-Remaining").get(0));
        if (remaining <= 1) {
            try {
                Thread.sleep(180 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void runTelegramQueue() {
        this.running = true;
        Thread thread = new Thread(() -> {
            while (!telegramQueue.isEmpty()) {
                QueuedTelegram t = telegramQueue.poll();

                try {
                    sendTelegram(t.target(), t.apiKey(), t.tgId(), t.tgSecret());
                    SentTelegram sent = new SentTelegram(t.target(), OffsetDateTime.now());

                    for (Consumer<SentTelegram> hook : hooks) {
                        hook.accept(sent);
                    }

                    futureMap.get(t).complete(true);

                    if (telegramQueue.peek() != null && telegramQueue.peek().isRecruitment()) {
                        // next telegram exists and is recruitment
                        Thread.sleep(180 * 1000);
                    } else if (telegramQueue.peek() != null) {
                        // next telegram exists and is not recruitment
                        Thread.sleep(30 * 1000);
                    } else {
                        // no more telegrams
                        continue;
                    }
                } catch (IOException e) {
                    System.err.println("Failed to send telegram");
                    e.printStackTrace();
                    futureMap.get(t).complete(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.running = false;
        });

        thread.start();
    }

    public void registerHook(Consumer<SentTelegram> hook) {
        this.hooks.add(hook);
    }
}
