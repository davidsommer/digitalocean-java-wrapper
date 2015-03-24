package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanDropletApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanDropletApi {

    public DigitalOceanDropletApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Droplets lookupDroplets(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "droplets");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Droplets result = client.execute(rb.build(), new DropletsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Droplet lookupDroplet(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "droplets/" + id).build();
            Droplet result = client.execute(request, new DropletResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Droplet createDroplet(String name, String region, String size,
            Long imageId, List<Integer> sshKeys, Boolean backups, Boolean ipv6,
            Boolean privateNetworking, String userData) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.post().setUri(baseUrl + "droplets")
                    .addParameter("name", name)
                    .addParameter("region", region)
                    .addParameter("size", size)
                    .addParameter("image", String.valueOf(imageId));
            if ((sshKeys != null) && (!sshKeys.isEmpty())) {
                rb.addParameter("ssh_keys", objectMapper.writeValueAsString(sshKeys));
            }
            if (backups != null) {
                rb.addParameter("backups", backups.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (ipv6 != null) {
                rb.addParameter("ipv6", ipv6.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (privateNetworking != null) {
                rb.addParameter("private_networking", privateNetworking.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (StringUtils.isNotBlank(userData)) {
                rb.addParameter("user_data", userData);
            }
            Droplet result = client.execute(rb.build(), new DropletResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteDroplet(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "droplets/" + id).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    protected class DropletsResponseHandler implements ResponseHandler<Droplets> {

        @Override
        public Droplets handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Droplets.class);
        }
    }

    protected class DropletResponseHandler implements ResponseHandler<Droplet> {

        @Override
        public Droplet handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Droplet.class);
        }
    }
}
