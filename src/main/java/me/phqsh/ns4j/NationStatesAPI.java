package me.phqsh.ns4j;

import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.nation.PrivateNation;
import me.phqsh.ns4j.enums.shards.PrivateShards;
import me.phqsh.ns4j.exceptions.NationStatesException;
import me.phqsh.ns4j.request.http.HttpRequest;
import me.phqsh.ns4j.request.http.HttpRequestImpl;
import me.phqsh.ns4j.request.http.RequestQueue;

import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NationStatesAPI{
    private final String baseURL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    //set rate limit to 1000ms
    private RequestQueue queue = new RequestQueue();

    /**
     * Set the API rate limit (in milliseconds).
     * @param ms The rate limit in milliseconds.
     */
    public void updateRatelimit(int ms){
        queue.setRateLimit(ms);
    }

    public void setRatelimitBuffer(int requests) {
        queue.setRequestBuffer(Math.max(0, Math.min(requests, 50)));
    }

    public void setUserAgent(String userAgent) {
        queue.setUserAgent(userAgent);
    }

    public RequestQueue getRequestQueue() {
        return this.queue;
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
            CompletableFuture<Container> container = queue.queue(request);
            container.get();
        } catch (ExecutionException | InterruptedException | CancellationException e) {
            throw new NationStatesException("Error getting the data from the API.", e);
        }
    }

    public boolean verifyNationChecksum(String nation, String checksum) throws NationStatesException {
        try {
            HttpRequest request = new HttpRequestImpl(generateVerificationURL(nation, checksum), Integer.class);
            CompletableFuture<Container> container = queue.queue(request);
            return container.get() == null;
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

    private String generateVerificationURL(String nation, String checksum) {
        return baseURL + "a=verify&nation=" + nation.replace(" ", "_") + "&checksum=" + checksum;
    }
}
