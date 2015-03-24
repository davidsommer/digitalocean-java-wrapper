package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import com.redmonkeysoftware.digitalocean.logic.Kernels;
import com.redmonkeysoftware.digitalocean.logic.wrappers.DropletWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
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
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id).build();
            Droplet result = client.execute(request, new DropletResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Kernels lookupDropletKernels(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/kernels").build();
            Kernels result = client.execute(request, new KernelsResponseHandler());
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
            Map<String, String> params = new HashMap<>();
            params.put("name", name);
            params.put("region", region);
            params.put("size", size);
            params.put("image", String.valueOf(imageId));
            if ((sshKeys != null) && (!sshKeys.isEmpty())) {
                params.put("ssh_keys", objectMapper.writeValueAsString(sshKeys));
            }
            if (backups != null) {
                params.put("backups", backups.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (ipv6 != null) {
                params.put("ipv6", ipv6.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (privateNetworking != null) {
                params.put("private_networking", privateNetworking.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (StringUtils.isNotBlank(userData)) {
                params.put("user_data", userData);
            }
            String paramString = objectMapper.writeValueAsString(params);
            RequestBuilder rb = RequestBuilder.post().setUri(baseUrl + "droplets").setEntity(new StringEntity(paramString));
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
            DropletWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), DropletWrapper.class);
            return wrapper != null ? wrapper.getDroplet() : null;
        }
    }

    protected class KernelsResponseHandler implements ResponseHandler<Kernels> {

        @Override
        public Kernels handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Kernels.class);
        }
    }
}
