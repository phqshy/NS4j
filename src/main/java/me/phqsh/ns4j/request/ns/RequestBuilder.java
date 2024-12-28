package me.phqsh.ns4j.request.ns;

import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.ContainerType;
import me.phqsh.ns4j.containers.TargetedContainer;
import me.phqsh.ns4j.containers.cards.Card;
import me.phqsh.ns4j.enums.Options;
import me.phqsh.ns4j.enums.Shards;
import me.phqsh.ns4j.enums.wa.Council;
import me.phqsh.ns4j.exceptions.NationStatesException;
import me.phqsh.ns4j.exceptions.RequestBuilderException;
import me.phqsh.ns4j.request.http.HttpRequestImpl;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class RequestBuilder {
    private static final String BASE_URL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    private Class<?> decodingClass;
    private List<Shards> shards = new ArrayList<>();
    private Map<String, String> options = new HashMap<>();
    private String target;
    private String waResolution;
    private String url;

    public RequestBuilder setType(Class<?> clazz) {
        // decoding types must extend Container
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Container.class) {
            throw new RequestBuilderException("Class " + clazz.getSimpleName() + " does not extend " + Container.class.getSimpleName() + ".");
        }
        this.decodingClass = clazz;
        return this;
    }

    public RequestBuilder addShards(Shards... shards) {
        this.shards.addAll(List.of(shards));
        return this;
    }

    public RequestBuilder clearShards() {
        this.shards = new ArrayList<>();
        return this;
    }

    public RequestBuilder addOptions(Map<String, String> map) {
        this.options.putAll(map);
        return this;
    }

    public RequestBuilder addOption(String key, String value) {
        this.options.put(key, value);
        return this;
    }

    public RequestBuilder addOption(String key, int value) {
        return addOption(key, String.valueOf(value));
    }

    public RequestBuilder addOption(Options key, String value) {
        return addOption(key.getId(), value);
    }

    public RequestBuilder addOption(Options key, int value) {
        return addOption(key.getId(), value);
    }

    public RequestBuilder addOption(Options key, Options value) {
        return addOption(key.getId(), value.getId());
    }

    public RequestBuilder clearOptions() {
        this.options = new HashMap<>();
        return this;
    }

    public RequestBuilder setTarget(String target) {
        this.target = target.toLowerCase().replace(" ", "_");
        return this;
    }

    public RequestBuilder setTarget(int target) {
        this.target = String.valueOf(target);
        return this;
    }

    public RequestBuilder setTarget(Council target) {
        this.target = String.valueOf(target.getId());
        return this;
    }

    public RequestBuilder setWaResolution(String id) {
        this.waResolution = id;
        return this;
    }

    public RequestBuilder setWaResolution(int id) {
        this.waResolution = String.valueOf(id);
        return this;
    }

    public String generateUrl() {
        // initialize the base URL with appropriate prefixes
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append(ContainerType.CLASSES.get(decodingClass)).append("=");

        // targeted containers (ex nation where the url contains "nation=x" before list of shards)
        if (Arrays.stream(decodingClass.getInterfaces()).anyMatch(e -> e == TargetedContainer.class)) {
            if (target == null) {
                throw new RequestBuilderException("You must specify a target when fetching this request!");
            }

            // prep for adding shards
            url.append(target).append("&q=");;

            // the "cards" shard is implied for the Cards container, so hardcode it here
            if (decodingClass == Card.class) {
                url.append("card+");
            }
        }

        // add shards to URL
        for (Shards i : shards){
            url = new StringBuilder(url.toString().concat(i.getId().concat("+")));
        }

        // prep for additional options
        url.append(";");

        // add options to URL
        for (String i : options.keySet()) {
            url.append(i).append("=").append(options.get(i)).append(";");
        }

        return url.toString();
    }

    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public Container fetch(NationStatesAPI api) throws NationStatesException {
        if (this.decodingClass == null) throw new RequestBuilderException("Failed to supply a Container subclass for the request.");

        try {
            if (this.url == null) {
                this.url = generateUrl();
            }

            return api.getRequestQueue().queue(new HttpRequestImpl(url, decodingClass)).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new NationStatesException("Error getting data from the NationStates API.", e);
        }
    }
}
