package me.phqsh.ns4j.request;

import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.threading.ThreadManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.lang.*;

public class RequestQueue {
    //default of one second
    private int RATELIMIT = 1000;
    private Queue<Request> queue = new LinkedList<>();
    private Map<Request, CompletableFuture<Container>> futures = new HashMap<>();
    private volatile boolean isRunning = false;

    public CompletableFuture<Container> queue(Request request){
        queue.add(request);
        CompletableFuture<Container> future = new CompletableFuture<>();
        futures.put(request, future);
        if (!this.isRunning) this.run();
        return future;
    }
    
    public RequestQueue(int ratelimit){
        this.RATELIMIT = ratelimit;
    }

    public RequestQueue(){
    }

    private void run(){
        ThreadManager.executeOffThread(() -> {
            try{
                while (!queue.isEmpty()){
                    Request request = queue.poll();
                    futures.get(request).complete(request.execute());

                    //handle response headers
                    for (String s : request.getResponseHeaders().keySet()){
                        //ignore null values
                        if (s == null) continue;

                        //where we are in terms of rate limit
                        if (s.equalsIgnoreCase("X-ratelimit-requests-seen")){
                            int requestsSeen = Integer.parseInt(request.getResponseHeaders().get(s).get(0));
                            //remain 1 below rate limit just for safety
                            if (requestsSeen >= 99){
                                Thread.sleep(RATELIMIT);
                            }
                        }

                    }
                    futures.remove(request);
                    Thread.sleep(this.RATELIMIT);
                }
            } catch (RuntimeException | InterruptedException | IllegalAccessException e){
                e.printStackTrace();
            } finally {
                this.isRunning = false;
            }
        });
    }

    public void setRateLimit(int ratelimit){
        this.RATELIMIT = ratelimit;
    }
}
