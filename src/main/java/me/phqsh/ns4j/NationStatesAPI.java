package me.phqsh.ns4j;

import lombok.Getter;
import lombok.Setter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.Nation;
import me.phqsh.ns4j.containers.Region;
import me.phqsh.ns4j.containers.World;
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
     */
    public Nation getNationCensus(String nation, CensusType.Mode mode, CensusType... censuses){
        if (censuses.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
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
     */
    public Nation getNationCensus(String nation, CensusType... censuses){
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

    private String generateWorldFactionURL(int id){
        return baseURL + "q=faction&id=" + id;
    }

    /**
     * Set the API rate limit (in milliseconds).
     * @param ms The rate limit in milliseconds.
     */
    public void updateRatelimit(int ms){
        queue.setRateLimit(ms);
    }
}
