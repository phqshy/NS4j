package me.phqsh.ns4j;

import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.nation.Nation;
import me.phqsh.ns4j.containers.nation.PrivateNation;
import me.phqsh.ns4j.containers.region.Region;
import me.phqsh.ns4j.enums.shards.PrivateShards;
import me.phqsh.ns4j.exceptions.NationStatesException;
import me.phqsh.ns4j.request.dump.DataDumpDownloader;
import me.phqsh.ns4j.request.http.HttpRequest;
import me.phqsh.ns4j.request.http.HttpRequestImpl;
import me.phqsh.ns4j.request.http.RequestClient;
import me.phqsh.ns4j.request.telegram.QueuedTelegram;
import me.phqsh.ns4j.request.telegram.SentTelegram;
import me.phqsh.ns4j.request.telegram.TelegramClient;
import me.phqsh.ns4j.sse.EventSubscriber;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public class NationStatesAPI{
    private final String baseURL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    //set rate limit to 1000ms
    private RequestClient requestClient = new RequestClient();
    private TelegramClient telegramClient = new TelegramClient();
    private EventSubscriber sseSubscriber = new EventSubscriber();

    /**
     * Set the API rate limit (in milliseconds).
     * @param ms The rate limit in milliseconds.
     */
    public void updateRatelimit(int ms){
        requestClient.setRateLimit(ms);
    }

    public void setRatelimitBuffer(int requests) {
        requestClient.setRequestBuffer(Math.max(0, Math.min(requests, 50)));
    }

    public void setUserAgent(String userAgent) {
        requestClient.setUserAgent(userAgent);
        telegramClient.setUserAgent(userAgent);
    }

    public RequestClient getRequestClient() {
        return this.requestClient;
    }

    public List<Region> getRegionDataDump(Date date) throws NationStatesException {
        try {
            return DataDumpDownloader.downloadRegionDump(date);
        } catch (JAXBException | IOException e) {
            throw new NationStatesException("Failed to download regional data dump", e);
        }
    }

    public List<Nation> getNationDataDump(Date date) throws NationStatesException {
        try {
            return DataDumpDownloader.downloadNationDump(date);
        } catch (JAXBException | IOException e) {
            throw new NationStatesException("Failed to download nation data dump", e);
        }
    }

    public CompletableFuture<Boolean> queueTelegram(QueuedTelegram telegram) {
        return telegramClient.queueTelegram(telegram);
    }

    public void registerTelegramHook(Consumer<SentTelegram> function) {
        telegramClient.registerHook(function);
    }

    public void setRecruitmentFilter(Function<QueuedTelegram, Boolean> function) {
        telegramClient.setRecruitmentFilter(function);
    }

    public EventSubscriber getEventSubscriber() {
        return this.sseSubscriber;
    }

    public LinkedList<QueuedTelegram> getTelegramQueue() {
        return this.telegramClient.getTelegramQueue();
    }

    public void setTelegramQueue(LinkedList<QueuedTelegram> queue) {
        this.telegramClient.setTelegramQueue(queue);
    }

    public void removeTelegramFromQueue(QueuedTelegram tg) {
        this.telegramClient.removeTelegram(tg);
    }

    /**
     * Not supported
     * @param nation The nation to get the private shard from.
     * @param password The password of the nation.
     * @param shards The shards to get.
     */
    public void getPrivateShard(String nation, String password, PrivateShards... shards) throws NationStatesException{
        try{
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-Password", password);
            HttpRequest request = new HttpRequestImpl(generatePrivateShardsURL(nation, shards), PrivateNation.class, headers);
            CompletableFuture<Container> container = requestClient.queue(request);
            container.get();
        } catch (ExecutionException | InterruptedException | CancellationException e) {
            throw new NationStatesException("Error getting the data from the API.", e);
        }
    }

    private String generatePrivateShardsURL(String nation, PrivateShards... shards){
        String base = baseURL + "nation=" + nation.replace(" ", "_") + "&q=";
        for (PrivateShards shards1 : shards){
            base = base.concat(shards1.getId().concat("+"));
        }
        return base;
    }
}
