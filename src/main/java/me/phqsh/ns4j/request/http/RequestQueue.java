package me.phqsh.ns4j.request.http;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.exceptions.NationStatesException;
import me.phqsh.ns4j.threading.ThreadManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.lang.*;

public class RequestQueue {
    //default of one second
    private int ratelimit = 1000;
    private Queue<HttpRequest> queue = new LinkedList<>();
    private Map<HttpRequest, CompletableFuture<Container>> futures = new HashMap<>();
    private volatile boolean isRunning = false;

    @Setter
    private String userAgent;

    @Setter
    private int requestBuffer = 5;

    public CompletableFuture<Container> queue(HttpRequest request){
        queue.add(request);
        CompletableFuture<Container> future = new CompletableFuture<>();
        futures.put(request, future);
        if (!this.isRunning) this.run();
        return future;
    }
    
    public RequestQueue(){
    }

    private void run(){
        this.isRunning = true;
        ThreadManager.executeOffThread(() -> {
            while (!queue.isEmpty()) {
                HttpRequest request = queue.poll();

                try{
                    if (userAgent == null) {
                        throw new NationStatesException("User-Agent is null. Set it with NationStatesAPI#setUserAgent.");
                    }
                    Container response = request.execute(this.userAgent);
                    futures.get(request).complete(response);
                    futures.remove(request);
                    handleResponseHeaders(request);
                } catch (RuntimeException | InterruptedException | IllegalAccessException | NationStatesException e){
                    CompletableFuture<Container> future = futures.remove(request);
                    if (future == null) continue;
                    future.cancel(false);
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(this.ratelimit);
                } catch (InterruptedException e) {
                }
            }

            this.isRunning = false;
        });
    }

    public void setRateLimit(int ratelimit){
        this.ratelimit = ratelimit;
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
            }

            if (s.equalsIgnoreCase("ratelimit-reset")) {
                requestsLimit = Integer.parseInt(request.getResponseHeaders().get(s).get(0));
            }
        }

        if (requestsRemaining <= this.requestBuffer) {
            System.err.println("NS4J > Ratelimit reached, sleeping for " + requestsLimit + " seconds.");
            Thread.sleep(requestsLimit * 1000L);
        }
    }
}
