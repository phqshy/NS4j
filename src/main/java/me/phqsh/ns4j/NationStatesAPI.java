package me.phqsh.ns4j;

import lombok.Getter;
import lombok.Setter;
import me.phqsh.ns4j.containers.Nation;
import me.phqsh.ns4j.enums.CensusType;
import me.phqsh.ns4j.enums.NationShards;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NationStatesAPI{
    private final String baseURL = "https://www.nationstates.net/cgi-bin/api.cgi?";

    @Getter
    @Setter
    private String UserAgent = "NationStates API Wrapper for Java (the.yeetusa@gmail.com)";

    public Nation getNationShard(String nation, NationShards... shards){
        if (shards.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }

        try {
            InputStream data = makeGetRequest(generateNationURL(nation, shards)).get();

            System.out.println(generateNationURL(nation, shards));

            JAXBContext context = JAXBContext.newInstance(Nation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (Nation) unmarshaller.unmarshal(data);
        } catch (Exception e){
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    public Nation getNationCensus(String nation, CensusType.Mode mode, CensusType... censuses){
        if (censuses.length == 0) {
            System.err.println("Length of shards cannot be 0!");
            return null;
        }

        try {
            InputStream data = makeGetRequest(generateNationCensusURL(nation, mode, censuses)).get();

            System.out.println(generateNationCensusURL(nation, mode, censuses));

            JAXBContext context = JAXBContext.newInstance(Nation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (Nation) unmarshaller.unmarshal(data);
        } catch (Exception e){
            System.err.println("Error getting the data from the API.");
            e.printStackTrace();
            return null;
        }
    }

    public Nation getNationCensus(String nation, CensusType... censuses){
        return getNationCensus(nation, null, censuses);
    }

    private CompletableFuture<InputStream> makeGetRequest(String url1) throws MalformedURLException {
        URL url = new URL(url1);
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<InputStream> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                HttpURLConnection is = (HttpURLConnection) url.openConnection();
                is.setRequestProperty("User-Agent", UserAgent);
                int status = is.getResponseCode();
                if (status == 429){
                    throw new RuntimeException("The rate limit has been exceeded.");
                }
                InputStream resp = is.getInputStream();

                future.complete(resp);
            } catch (IOException e) {
                future.complete(null);
                return;
            }
        });

        return future;
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
}