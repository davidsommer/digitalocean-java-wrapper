package com.redmonkeysoftware.digitalocean;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanAccountApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanAccountApiImpl;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDomainApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDomainApiImpl;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDomainRecordApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDomainRecordApiImpl;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDropletApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanDropletApiImpl;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanImageApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanImageApiImpl;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanSshKeyApi;
import com.redmonkeysoftware.digitalocean.connectors.DigitalOceanSshKeyApiImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

public class DigitalOceanConnector {

    private final ObjectMapper objectMapper;
    private final CloseableHttpClient client;
    private final String baseUrl;
    private final String authToken;

    private DigitalOceanAccountApi accountApi;
    private DigitalOceanDomainApi domainApi;
    private DigitalOceanDomainRecordApi domainRecordApi;
    private DigitalOceanDropletApi dropletApi;
    private DigitalOceanImageApi imageApi;
    private DigitalOceanSshKeyApi sshKeyApi;

    public DigitalOceanConnector(final String authToken) {
        this.authToken = authToken;
        this.client = createDefaultHttpClient();
        this.objectMapper = createDefaultObjectMapper();
        this.baseUrl = "https://api.digitalocean.com/v2/";
    }

    private CloseableHttpClient createDefaultHttpClient() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Authorization", "Bearer " + authToken));
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
        return HttpClients.custom().setDefaultHeaders(headers).setDefaultRequestConfig(requestConfig).build();
    }

    private ObjectMapper createDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public synchronized DigitalOceanAccountApi getAccountApi() {
        if (accountApi == null) {
            accountApi = new DigitalOceanAccountApiImpl(objectMapper, client, baseUrl);
        }
        return accountApi;
    }

    public synchronized DigitalOceanDomainApi getDomainApi() {
        if (domainApi == null) {
            domainApi = new DigitalOceanDomainApiImpl(objectMapper, client, baseUrl);
        }
        return domainApi;
    }

    public synchronized DigitalOceanDomainRecordApi getDomainRecordApi() {
        if (domainRecordApi == null) {
            domainRecordApi = new DigitalOceanDomainRecordApiImpl(objectMapper, client, baseUrl);
        }
        return domainRecordApi;
    }

    public synchronized DigitalOceanDropletApi getDropletApi() {
        if (dropletApi == null) {
            dropletApi = new DigitalOceanDropletApiImpl(objectMapper, client, baseUrl);
        }
        return dropletApi;
    }

    public synchronized DigitalOceanImageApi getImageApi() {
        if (imageApi == null) {
            imageApi = new DigitalOceanImageApiImpl(objectMapper, client, baseUrl);
        }
        return imageApi;
    }

    public synchronized DigitalOceanSshKeyApi getSshKeyApi() {
        if (sshKeyApi == null) {
            sshKeyApi = new DigitalOceanSshKeyApiImpl(objectMapper, client, baseUrl);
        }
        return sshKeyApi;
    }
}
