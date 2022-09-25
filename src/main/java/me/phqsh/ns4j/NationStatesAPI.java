package me.phqsh.ns4j;

import lombok.Getter;
import lombok.Setter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.Nation;
import me.phqsh.ns4j.containers.Region;
import me.phqsh.ns4j.enums.CensusType;
import me.phqsh.ns4j.enums.NationShards;
import me.phqsh.ns4j.enums.RegionShards;
import me.phqsh.ns4j.request.Request;
import me.phqsh.ns4j.request.RequestImpl;
import me.phqsh.ns4j.request.RequestQueue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NationStatesAPI{
    private final String baseURL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    private RequestQueue queue = RequestQueue.init();

    /**
     * User-Agent header for the HTTP request. Set this to your own.
     */
    @Getter
    @Setter
    private static String UserAgent = "NationStates API Wrapper for Java (the.yeetusa@gmail.com)";

    /**
     * Get the specified shard of a nation.
     * @param nation The nation to get the shard from.
     * @param shards The shard(s) to get
     * @return A Nation object containing the specified shard(s).
     */
    public Nation getNationShard(String nation, NationShards... shards){
        if (shards.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }
        try {
            Request request = new RequestImpl(generateNationURL(nation, shards), Nation.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (Nation) container.get();
        } catch (RuntimeException | InterruptedException | ExecutionException e){
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the specified shard of a region.
     * @param region The region to get the shard from.
     * @param shards The shard(s) to get.
     * @return A Region object containing the specified shard(s).
     */
    public Region getRegionShard(String region, RegionShards... shards){
        if (shards.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }
        try{
            Request request = new RequestImpl(generateRegionURL(region, shards), Region.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (Region) container.get();
        } catch (Exception e){
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the specified census shard of a nation.
     * @param nation The nation to get the census shard from.
     * @param mode The census mode to get (score, region rank, etc.)
     * @param censuses The census(es) to get.
     * @return A Nation object containing the specified census(es) (use the getCensus() method to get the census hashmap).
     */
    public Nation getNationCensus(String nation, CensusType.Mode mode, CensusType... censuses){
        if (censuses.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }
        try {
            System.out.println(generateNationCensusURL(nation, mode, censuses));

            Request request = new RequestImpl(generateNationCensusURL(nation, mode, censuses), Nation.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (Nation) container.get();
        } catch (Exception e){
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the specified census shard of a nation, with the default CensusType.Mode.SCORE mode.
     * @param nation The nation to get the census shard from.
     * @param censuses The census(es) to get.
     * @return A Region object containing the specified census(es) (use the getCensus() method to get the census hashmap).
     */
    public Nation getNationCensus(String nation, CensusType... censuses){
        return getNationCensus(nation, null, censuses);
    }

    private String generateNationURL(String nation, NationShards... shards){
        String base = baseURL + "nation=" + nation.replace(" ", "_") + "&q=";
        for (NationShards i : shards){
            base = base.concat(i.getId().concat("+"));
        }
        base = base.concat(NationShards.NAME.getId().concat("+"));
        return base;
    }

    private String generateNationCensusURL(String nation, CensusType.Mode mode, CensusType... censuses){
        nation = nation.replace(" ", "_");
        if (nation.indexOf(0) == '_'){
            nation = nation.substring(1);
        }
        String base = baseURL + "nation=" + nation + "&q=census";
        if (mode != null){
            base = base.concat("&mode=" + mode.getId());
        }
        base = base.concat("&scale=");
        for (CensusType c : censuses){
            if (c.getId() == -1){
                base = base.concat("all");
                break;
            }

            base = base.concat(c.getId() + "+");
        }
        return base;
    }

    public String generateRegionURL(String region, RegionShards... shards){
        String base = baseURL + "region=" + region.replace(" ", "_") + "&q=";
        for (RegionShards i : shards){
            base = base.concat(i.getId().concat("+"));
        }
        base = base.concat(NationShards.NAME.getId().concat("+"));
        return base;
    }
}