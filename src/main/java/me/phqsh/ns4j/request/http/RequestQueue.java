package me.phqsh.ns4j.request.http;

import com.google.gson.Gson;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.ContainerType;
import me.phqsh.ns4j.containers.nation.Nation;
import me.phqsh.ns4j.containers.region.Region;
import me.phqsh.ns4j.containers.wa.WorldAssembly;
import me.phqsh.ns4j.containers.world.World;
import me.phqsh.ns4j.threading.ThreadManager;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.lang.*;

public class RequestQueue {
    //default of one second
    private int RATELIMIT;
    private Queue<HttpRequest> queue = new LinkedList<>();
    private Map<HttpRequest, CompletableFuture<Container>> futures = new HashMap<>();
    private volatile boolean isRunning = false;
    private boolean cache = false;
    private String cacheDirectory = "./ns4j/";
    private long expiration = 0L;
    private Gson gson = new Gson();

    public CompletableFuture<Container> queue(HttpRequest request){
        queue.add(request);
        CompletableFuture<Container> future = new CompletableFuture<>();
        futures.put(request, future);
        if (!this.isRunning) this.run();
        return future;
    }
    
    public RequestQueue(int ratelimit){
        this.RATELIMIT = ratelimit;
    }

    private void run(){
        this.isRunning = true;
        ThreadManager.executeOffThread(() -> {
            while (!queue.isEmpty()) {
                HttpRequest request = queue.poll();

                try{
                    Container response = request.execute();
                    futures.get(request).complete(response);
                    futures.remove(request);
                    handleResponseHeaders(request);
                } catch (RuntimeException | InterruptedException | IllegalAccessException e){
                    CompletableFuture<Container> future = futures.remove(request);
                    if (future == null) continue;
                    future.cancel(false);
                    e.printStackTrace();
                }

                try {
                    if (!cache) Thread.sleep(this.RATELIMIT);
                } catch (InterruptedException e) {
                }
            }

            this.isRunning = false;
        });
    }

    public void setRateLimit(int ratelimit){
        this.RATELIMIT = ratelimit;
    }

    private void handleResponseHeaders(HttpRequest request) throws IllegalAccessException, InterruptedException {
        int requestsRemaining = 0;
        int requestsLimit = 0;

        for (String s : request.getResponseHeaders().keySet()){
            //ignore null values
            if (s == null) continue;

            //where we are in terms of rate limit
            if (s.equalsIgnoreCase("ratelimit-remaining")){
                requestsRemaining = Integer.parseInt(request.getResponseHeaders().get(s).get(0));
                //remain a few (5) below rate limit just for safety
                if (requestsRemaining <= 5){
                    // temporarily halve request rate limit
                    Thread.sleep(this.RATELIMIT);
                }
            }

            if (s.equalsIgnoreCase("ratelimit-reset")) {
                requestsLimit = Integer.parseInt(request.getResponseHeaders().get(s).get(0));
            }
        }

        if (requestsRemaining <= 1) {
            System.err.println("NS4J > Rate limit reached, sleeping for " + requestsLimit + " seconds.");
            Thread.sleep(requestsLimit * 1000L);
        }
    }
}
