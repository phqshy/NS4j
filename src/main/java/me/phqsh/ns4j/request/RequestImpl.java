package me.phqsh.ns4j.request;

import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.containers.Container;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestImpl implements Request{
    private Class<?> returnType;
    private String url;

    public RequestImpl(String url, Class<?> returnType){
        this.url = url;
        this.returnType = returnType;
    }

    @Override
    public Container execute() {
        try{
            return (Container) parseXml(url, returnType);
        } catch (JAXBException | IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Object parseXml(String url, Class<?> class1) throws IOException, ExecutionException, InterruptedException, JAXBException {
        InputStream data = makeGetRequest(url).get();
        /*Scanner s = new Scanner(data).useDelimiter("\\A");
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
                HttpURLConnection is = (HttpURLConnection) url.openConnection();
                is.setRequestProperty("User-Agent", NationStatesAPI.getUserAgent());
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
}
