package me.phqsh.ns4j.request;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
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
import java.util.concurrent.TimeUnit;

public class RequestQueue {
    //default of one second
    private int RATELIMIT;
    private Queue<Request> queue = new LinkedList<>();
    private Map<Request, CompletableFuture<Container>> futures = new HashMap<>();
    private volatile boolean isRunning = false;
    private boolean cache = false;
    private String cacheDirectory = "./ns4j/";
    private long expiration = 0L;
    private Gson gson = new Gson();

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

    private void run(){
        this.isRunning = true;
        ThreadManager.executeOffThread(() -> {
            try{
                while (!queue.isEmpty()){
                    Request request = queue.poll();
                    if (cache) {
                        File cached = new File(formatFileName(request) + ".json");
                        if (cached.exists()) {
                            Container c = deserialize(cached);
                            if (c.getTimestamp() + expiration >= System.currentTimeMillis()) {
                                futures.get(request).complete(c);
                                futures.remove(request);
                            } else {
                                cached.delete();
                                makeCachedRequest(request, cached);
                            }
                        } else {
                            makeCachedRequest(request, cached);
                        }
                    } else {
                        Container response = request.execute();
                        futures.get(request).complete(response);
                        handleResponseHeaders(request);
                        futures.remove(request);
                        Thread.sleep(this.RATELIMIT);
                    }
                }
            } catch (RuntimeException | InterruptedException | IllegalAccessException e){
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Failed to read/write from cache!");
            } finally {
                this.isRunning = false;
            }
        });
    }

    public void setRateLimit(int ratelimit){
        this.RATELIMIT = ratelimit;
    }

    public void setCaching(boolean caching, long expiration) {
        this.cache = caching;
        this.expiration = expiration;
        File cacheDir = new File(cacheDirectory);
        if (caching) {
            if (!cacheDir.exists()) cacheDir.mkdir();
        } else {
            if (cacheDir.exists()) cacheDir.delete();
        }
    }

    private String formatFileName(Request r) {
        return cacheDirectory + r.getUrl().replace("https://www.nationstates.net/cgi-bin/api.cgi?", "");
    }

    private void handleResponseHeaders(Request request) throws IllegalAccessException, InterruptedException {
        for (String s : request.getResponseHeaders().keySet()){
            //ignore null values
            if (s == null) continue;

            //where we are in terms of rate limit
            if (s.equalsIgnoreCase("X-ratelimit-requests-seen")){
                int requestsSeen = Integer.parseInt(request.getResponseHeaders().get(s).get(0));
                //remain a few (5) below rate limit just for safety
                if (requestsSeen >= 45){
                    System.err.println("NS4j> Throttling requests to avoid the rate limit. Sleeping for " + this.RATELIMIT + "ms");
                    Thread.sleep(this.RATELIMIT);
                }
            }
        }
    }

    private void makeCachedRequest(Request request, File cached) throws IOException, InterruptedException, IllegalAccessException {
        Container response = request.execute();
        response.setTimestamp(System.currentTimeMillis());

        //TODO: make this better
        //don't let aeioux see this he'll have a stroke
        String params = request.getUrl().replace("https://www.nationstates.net/cgi-bin/api.cgi?", "");
        response.setContainerType((ContainerType) Arrays.stream(ContainerType.values())
                .filter((e) -> params.startsWith(e.getUrlSegment()))
                .toArray()[0]);

        FileWriter writer = new FileWriter(cached);
        writer.write(gson.toJson(response));
        writer.close();
        futures.get(request).complete(response);
        handleResponseHeaders(request);
        futures.remove(request);
        Thread.sleep(this.RATELIMIT);
    }

    private Container deserialize(File cached) throws IOException {
        String data = Files.readString(cached.toPath());
        Container c = gson.fromJson(data, Container.class);
        switch (c.getContainerType()) {
            case WA -> {
                return gson.fromJson(data, WorldAssembly.class);
            }
            case NATION -> {
                return gson.fromJson(data, Nation.class);
            }
            case REGION -> {
                return gson.fromJson(data, Region.class);
            }
            case WORLD -> {
                return gson.fromJson(data, World.class);
            }
        }
        throw new IllegalStateException("Unable to deserialize cache");
    }
}
