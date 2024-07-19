package me.phqsh.ns4j.request;

import lombok.Getter;
import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.containers.Container;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestImpl implements Request{
    private Class<?> returnType;
    private String url;
    private Map<String, String> headers;
    private Map<String, List<String>> responseHeaders;

    public RequestImpl(String url, Class<?> returnType){
        this.url = url;
        this.returnType = returnType;
    }

    public RequestImpl(String url, Class<?> returnType, Map<String, String> headers){
        this.url = url;
        this.returnType = returnType;
        this.headers = headers;
    }

    @Override
    public Container execute() {
        try{
            Container c = (Container) parseXml(url, returnType);
            c.setTimestamp(System.currentTimeMillis());
            return c;
        } catch (JAXBException | IOException | ExecutionException | InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException("Could not fetch " + url, e);
        }
    }

    private Object parseXml(String url, Class<?> class1) throws IOException, ExecutionException, InterruptedException, JAXBException {
        InputStream data = makeGetRequest(url).get();
       /* Scanner s = new Scanner(data).useDelimiter("\\A");
        String temp = "";
        while (s.hasNext()) {
            temp += s.next();
        }

        System.out.println(temp);

        InputStream is = new ByteArrayInputStream(temp.getBytes());*/

        JAXBContext context = JAXBContext.newInstance(class1);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(data);
    }

    private CompletableFuture<InputStream> makeGetRequest(String url1) throws MalformedURLException {
        URL url = new URL(url1);
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture<InputStream> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                HttpsURLConnection is = (HttpsURLConnection) url.openConnection();
                is.setRequestProperty("User-Agent", NationStatesAPI.getUserAgent());
                if (headers != null){
                    for (String key : headers.keySet()){
                        is.setRequestProperty(key, headers.get(key));
                    }
                }
                int status = is.getResponseCode();
                if (status == 429){
                    throw new RuntimeException("The rate limit has been exceeded. You will have to wait 15 minutes to make another request.");
                }
                InputStream resp = is.getInputStream();
                this.responseHeaders = is.getHeaderFields();

                future.complete(resp);
            } catch (IOException e) {
                future.complete(null);
                return;
            }
        });

        return future;
    }

    public Map<String, List<String>> getResponseHeaders() throws IllegalAccessException {
        if (this.responseHeaders == null) throw new IllegalAccessException("Attempting to access response headers when they are uninitialized");
        return this.responseHeaders;
    }

    public String getUrl() {
        return url;
    }
}
