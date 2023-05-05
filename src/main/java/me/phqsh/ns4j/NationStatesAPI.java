package me.phqsh.ns4j;

import lombok.Getter;
import lombok.Setter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.nation.Nation;
import me.phqsh.ns4j.containers.nation.PrivateNation;
import me.phqsh.ns4j.containers.region.Region;
import me.phqsh.ns4j.containers.wa.WorldAssembly;
import me.phqsh.ns4j.containers.world.World;
import me.phqsh.ns4j.enums.*;
import me.phqsh.ns4j.request.*;

import java.util.HashMap;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NationStatesAPI{
    private final String baseURL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    //set rate limit to 1000ms
    private RequestQueue queue = new RequestQueue(1000);

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
    public Nation getNationShards(String nation, NationShards... shards){
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
    public Region getRegionShards(String region, RegionShards... shards){
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
     * @throws IllegalArgumentException If the request contains CensusType.ZOMBIES.
     */
    public Nation getNationCensus(String nation, CensusType.Mode mode, CensusType... censuses) throws IllegalArgumentException{
        if (censuses.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }
        if (Arrays.asList(censuses).contains(CensusType.ZOMBIES)) {
            throw new IllegalArgumentException("You cannot use the CensusType.ZOMBIES on a nation. Use the zombies shard instead.");
        }
        try {
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
     * Get the specified census shard of a region.
     * @param region The region to get the census shard from.
     * @param mode The census mode to get (score, rank, etc.)
     * @param censuses The census(es) to get.
     * @return A region object containing the specified census(es) (use the getCensus() method to get the census hashmap).
     */
    public Region getRegionCensus(String region, CensusType.Mode mode, CensusType... censuses){
        if (censuses.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }
        try {
            Request request = new RequestImpl(generateRegionCensusURL(region, mode, censuses), Region.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (Region) container.get();
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
     * @throws IllegalArgumentException If the request contains CensusType.ZOMBIES.
     */
    public Nation getNationCensus(String nation, CensusType... censuses) throws IllegalArgumentException{
        return getNationCensus(nation, null, censuses);
    }

    /**
     * Get the specified census shard of a region, with the default CensusType.Mode.SCORE mode.
     * @param region The region to get the census shard from.
     * @param censuses The census(es) to get.
     * @return A Region object containing the specified census(es) (use the getCensus() method to get the census hashmap).
     */
    public Region getRegionCensus(String region, CensusType... censuses){
        return getRegionCensus(region, null, censuses);
    }

    /**
     * Gets the census ranks for a region. Census ranks are the top 20 nations in a region for a specific census.
     * @param region The region to get the census ranks from.
     * @param census The census to get the ranks for.
     * @param startPosition The position to start at.
     * @return A region object containing the census ranks.
     */
    public Region getRegionCensusRanks(String region, CensusType census, int startPosition){
        try{
            Request request = new RequestImpl(generateRegionRankURL(region, census, startPosition), Region.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (Region) container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the census ranks for a region. Census ranks are the top 10 nations in a region for a specific census (starting with #1, descending)
     * @param region The region to get the census ranks from.
     * @param census The census to get the ranks for.
     * @return A region object containing the census ranks.
     */
    public Region getRegionCensusRanks(String region, CensusType census){
        return getRegionCensusRanks(region, census, 1);
    }

    /**
     * Gets faction data for N-Day faction
     * @param factionID The faction ID to get the data for.
     * @return A World object containing the faction data.
     */
    public World getWorldFaction(int factionID){
        try{
            Request request = new RequestImpl(generateWorldFactionURL(factionID), World.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (World) container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets data from a world shard. Not fully implemented, only regions for now.
     * @param shards The shards to get
     * @return A World object containing the data,
     */
    public World getWorldShards(WorldShards... shards){
        try{
            Request request = new RequestImpl(generateWorldShardURL(shards), World.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (World) container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Not supported
     * @param nation The nation to get the private shard from.
     * @param password The password of the nation.
     * @param shards The shards to get.
     */
    public void getPrivateShard(String nation, String password, PrivateShards... shards){
        try{
            HashMap<String, String> headers = new HashMap<>();
            headers.put("X-Password", password);
            Request request = new RequestImpl(generatePrivateShardsURL(nation, shards), PrivateNation.class, headers);
            CompletableFuture<Container> container = queue.queue(request);
            container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
        }
    }

    /**
     * Gets the World Assembly data for the specified shards.
     * @param shards The shards to get.
     * @return A WorldAssembly object containing the specified shards.
     */
    public WorldAssembly getWorldAssemblyShards(WorldAssemblyShards... shards){
        try{
            Request request = new RequestImpl(generateWorldAssemblyURL(shards), WorldAssembly.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (WorldAssembly) container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a world assembly resolution
     * @param council the council to get the resolution from
     * @param id the id of the resolution
     * @return A WorldAssembly object containing the resolution
     */
    public WorldAssembly getWorldAssemblyResolution(WorldAssembly.Council council, int id){
        try {
            Request request = new RequestImpl(generateWAResolutionURL(council, id), WorldAssembly.class);
            CompletableFuture<Container> container = queue.queue(request);
            return (WorldAssembly) container.get();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
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

    private String generateRegionCensusURL(String region, CensusType.Mode mode, CensusType... censuses){
        region = region.replace(" ", "_");
        if (region.indexOf(0) == '_'){
            region = region.substring(1);
        }
        String base = baseURL + "region=" + region + "&q=census";
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

    private String generateRegionURL(String region, RegionShards... shards){
        String base = baseURL + "region=" + region.replace(" ", "_") + "&q=";
        for (RegionShards i : shards){
            base = base.concat(i.getId().concat("+"));
        }
        base = base.concat(NationShards.NAME.getId().concat("+"));
        return base;
    }

    private String generateRegionRankURL(String region, CensusType census, int startPosition){
        return baseURL + "region=" + region.replace(" ", "_") + "&q=censusranks&start=" + startPosition + ";scale=" + census.getId();
    }

    private String generateWorldShardURL(WorldShards... shards){
        String base = baseURL + "q=";
        for (WorldShards shards1 : shards){
            base = base.concat(shards1.getId().concat("+"));
        }
        return base;
    }

    private String generateWorldFactionURL(int id){
        return baseURL + "q=faction&id=" + id;
    }

    private String generatePrivateShardsURL(String nation, PrivateShards... shards){
        String base = baseURL + "nation=" + nation.replace(" ", "_") + "&q=";
        for (PrivateShards shards1 : shards){
            base = base.concat(shards1.getId().concat("+"));
        }
        return base;
    }

    private String generateWorldAssemblyURL(WorldAssemblyShards... shards){
        String base = baseURL + "q=";
        for (WorldAssemblyShards i : shards){
            base = base.concat(i.getId().concat("+"));
        }
        return base;
    }

    private String generateWAResolutionURL(WorldAssembly.Council council, int id){
        return baseURL + "wa=" + council.getId() + "&q=resolution&id=" + id;
    }

    /**
     * Set the API rate limit (in milliseconds).
     * @param ms The rate limit in milliseconds.
     */
    public void updateRatelimit(int ms){
        queue.setRateLimit(ms);
    }

    /**
     * Enables caching for requests, with an expiration
     * @param status should caching be enabled
     * @param expiration how long until the cache for a request is invalidated (in milliseconds). If disabling caching, set this to 0L
     */
    public void toggleCaching(boolean status, long expiration) {
        queue.setCaching(status, expiration);
    }
}
