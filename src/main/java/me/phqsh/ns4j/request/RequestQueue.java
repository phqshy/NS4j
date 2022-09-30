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
    private boolean isRunning = false;

    public CompletableFuture<Container> queue(Request request){
        queue.add(request);
        CompletableFuture<Container> future = new CompletableFuture<>();
        futures.put(request, future);
        if (!this.accessRunning(false, true)) this.run();
        return future;
    }
    
    public RequestQueue(int ratelimit){
        this.RATELIMIT = ratelimit;
    }

    public RequestQueue(){
    }

    /**
     * Access if the queue is running
     * @param b set if true or false
     * @param ignoreUpdate don't set only get
     * @return if the queue is running
     */
    private synchronized boolean accessRunning(boolean b, boolean ignoreUpdate){
        if (ignoreUpdate) return this.isRunning;
        this.isRunning = b;
        return this.isRunning;
    }

    private void run(){
        this.accessRunning(true, false);
        ThreadManager.executeOffThread(() -> {
            try{
                while (!queue.isEmpty()){
                    Request request = queue.poll();
                    futures.get(request).complete(request.execute());
                    for (String s : request.)
                    futures.remove(request);
                    Thread.sleep(this.RATELIMIT);
                }
            } catch (RuntimeException | InterruptedException e){
                e.printStackTrace();
            } finally {
                this.accessRunning(false, false);
            }
        });
    }

    public void setRateLimit(int ratelimit){
        this.RATELIMIT = ratelimit;
    }
}
