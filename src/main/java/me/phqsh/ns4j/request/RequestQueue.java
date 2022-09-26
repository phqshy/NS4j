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
    private static RequestQueue INSTANCE = null;
    //default of one second
    private int RATELIMIT = 1000;
    private Queue<Request> queue = new LinkedList<>();
    private Map<Request, CompletableFuture<Container>> futures = new HashMap<>();
    boolean isRunning = false;

    public CompletableFuture<Container> queue(Request request){
        queue.add(request);
        CompletableFuture<Container> future = new CompletableFuture<>();
        futures.put(request, future);
        if (!this.isRunning) this.run();
        return future;
    }

    public static RequestQueue init(int ratelimit){
        if (INSTANCE == null){
            INSTANCE = new RequestQueue(ratelimit);
        }
        return INSTANCE;
    }
    
    private RequestQueue(int ratelimit){
        if (INSTANCE != null) throw new IllegalStateException("Cannot have more than one RequestQueue!");
        this.RATELIMIT = ratelimit;
    }    

    public static RequestQueue getInstance() {
        return INSTANCE;
    }

    public void run(){
        this.isRunning = true;
        ThreadManager.executeOffThread(() -> {
            try{
                while (!queue.isEmpty()){
                    Request request = queue.poll();
                    futures.get(request).complete(request.execute());
                    Thread.sleep(this.RATELIMIT);
                }
            } catch (RuntimeException | InterruptedException e){
                e.printStackTrace();
            } finally {
                this.isRunning = false;
            }
        });
    }
}
