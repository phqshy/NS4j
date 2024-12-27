package me.phqsh.ns4j.request.ns;

import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.ContainerType;
import me.phqsh.ns4j.containers.world.World;
import me.phqsh.ns4j.enums.Shards;
import me.phqsh.ns4j.enums.wa.Council;
import me.phqsh.ns4j.exceptions.NationStatesException;
import me.phqsh.ns4j.exceptions.RequestBuilderException;
import me.phqsh.ns4j.request.http.HttpRequestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NationStatesRequestBuilder {
    private static final String BASE_URL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    private Class<?> decodingClass;
    private List<Shards> shards = new ArrayList<>();
    private Map<String, String> options = new HashMap<>();
    private String target;
    private String waResolution;
    private String url;

    public NationStatesRequestBuilder setType(Class<?> clazz) {
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Container.class) {
            throw new RequestBuilderException("Class " + clazz.getSimpleName() + " does not derive from " + Container.class.getSimpleName() + ".");
        }
        this.decodingClass = clazz;
        return this;
    }

    public NationStatesRequestBuilder addShards(Shards... shards) {
        this.shards.addAll(List.of(shards));
        return this;
    }

    public NationStatesRequestBuilder clearShards() {
        this.shards = new ArrayList<>();
        return this;
    }

    public NationStatesRequestBuilder addOptions(Map<String, String> map) {
        this.options.putAll(map);
        return this;
    }

    public NationStatesRequestBuilder addOption(String key, String value) {
        this.options.put(key, value);
        return this;
    }

    public NationStatesRequestBuilder addOption(String key, int value) {
        return addOption(key, String.valueOf(value));
    }

    public NationStatesRequestBuilder clearOptions() {
        this.options = new HashMap<>();
        return this;
    }

    public NationStatesRequestBuilder setTarget(String target) {
        this.target = target.toLowerCase().replace(" ", "_");
        return this;
    }

    public NationStatesRequestBuilder setTarget(int target) {
        this.target = String.valueOf(target);
        return this;
    }

    public NationStatesRequestBuilder setTarget(Council target) {
        this.target = String.valueOf(target.getId());
        return this;
    }

    public NationStatesRequestBuilder setWaResolution(String id) {
        this.waResolution = id;
        return this;
    }

    public NationStatesRequestBuilder setWaResolution(int id) {
        this.waResolution = String.valueOf(id);
        return this;
    }

    public String generateUrl() {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append(ContainerType.CLASSES.get(decodingClass)).append("=");

        if (decodingClass != World.class) {
            if (target == null) {
                throw new RequestBuilderException("You must specify a target when fetching this request!");
            }

            url.append(target).append("&q=");
        }

        for (Shards i : shards){
            url = new StringBuilder(url.toString().concat(i.getId().concat("+")));
        }

        url.append(";");

        for (String i : options.keySet()) {
            url.append(i).append("=").append(options.get(i)).append(";");
        }

        return url.toString();
    }

    public NationStatesRequestBuilder setUrl(String url) {
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
