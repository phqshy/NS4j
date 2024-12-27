package me.phqsh.ns4j.request.http;

import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.verification.VerificationResult;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class HttpRequestImpl implements HttpRequest {
    private Class<?> returnType;
    private String url;
    private Map<String, String> headers;
    private Map<String, List<String>> responseHeaders;

    private String userAgent;

    public HttpRequestImpl(String url, Class<?> returnType){
        this.url = url;
        this.returnType = returnType;
    }

    public HttpRequestImpl(String url, Class<?> returnType, Map<String, String> headers){
        this.url = url;
        this.returnType = returnType;
        this.headers = headers;
    }

    @Override
    public Container execute(String userAgent) {
        try{
            this.userAgent = userAgent;
            Container c = (Container) parseXml(url, returnType);
            c.setTimestamp(System.currentTimeMillis());
            return c;
        } catch (JAXBException | IOException | ExecutionException | InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException("Could not fetch " + url, e);
        }
    }

    private Object parseXml(String url, Class<?> class1) throws IOException, ExecutionException, InterruptedException, JAXBException {
        InputStream resp = makeGetRequest(url);
        if (resp == null) throw new RuntimeException("Failed to fetch " + url);
        BufferedInputStream data = new BufferedInputStream(resp);
        data.mark(32);

        // verification api has a special formatting, and it messes up regular unmarshalling.
        Scanner s = new Scanner(data).useDelimiter("\\A");
        if (s.hasNext()) {
            String temp = s.next().strip();
            if (temp.equals("0") || temp.equals("1")) {
                return new VerificationResult(Integer.parseInt(temp));
            }
        }

        data.reset();

        JAXBContext context = JAXBContext.newInstance(class1);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(data);
    }

    private InputStream makeGetRequest(String url1) throws MalformedURLException {
        URL url = new URL(url1);

        try {
            HttpsURLConnection is = (HttpsURLConnection) url.openConnection();
            is.setRequestProperty("User-Agent", this.userAgent);
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

            return resp;
        } catch (IOException e) {
            return null;
        }
    }

    public Map<String, List<String>> getResponseHeaders() throws IllegalAccessException {
        if (this.responseHeaders == null) throw new IllegalAccessException("Attempting to access response headers when they are uninitialized");
        return this.responseHeaders;
    }

    public String getUrl() {
        return url;
    }
}
