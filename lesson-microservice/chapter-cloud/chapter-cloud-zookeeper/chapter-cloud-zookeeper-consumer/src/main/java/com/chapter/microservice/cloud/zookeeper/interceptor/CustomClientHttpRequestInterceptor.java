package com.chapter.microservice.cloud.zookeeper.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhengshijun
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000).build();

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String serviceId = request.getURI().getHost();
        String path = request.getURI().getPath();
        String scheme = request.getURI().getScheme();
        String query = request.getURI().getQuery();
        HttpMethod method = request.getMethod();

        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(serviceId);
        if (ObjectUtils.isEmpty(serviceInstances)){
            return execution.execute(request,body);
        }
        List<String> hosts = serviceInstances.stream().map(serviceInstance -> serviceInstance.getHost().concat(":").concat(String.valueOf(serviceInstance.getPort()))).collect(Collectors.toList());
        String host = hosts.get(new Random().nextInt(hosts.size()));
        String actualUrl = scheme+"://"+host+path+(StringUtils.isBlank(query)?StringUtils.EMPTY:"?"+query);

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(actualUrl);
        httpGet.setConfig(requestConfig);
        HttpResponse response  = httpClient.execute(httpGet);

        ClientHttpResponse clientHttpResponse = new ClientHttpResponse(){
            private int statusCode = response.getStatusLine().getStatusCode();
            private String statusText = response.getStatusLine().getReasonPhrase();
            private InputStream inputStream = response.getEntity().getContent();
            private Header[] headerArray = response.getAllHeaders();

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.valueOf(statusCode);
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return statusCode;
            }

            @Override
            public String getStatusText() throws IOException {
                return statusText;
            }

            @Override
            public void close() {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public InputStream getBody() throws IOException {
                return inputStream;
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                if (!ObjectUtils.isEmpty(headerArray)){
                    for (Header header : headerArray) {
                        headers.add(header.getName(),header.getValue());
                    }
                }
                return headers;
            }
        };
        return clientHttpResponse;
    }
}
