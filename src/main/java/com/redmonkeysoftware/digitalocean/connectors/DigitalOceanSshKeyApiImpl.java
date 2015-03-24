package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.SshKey;
import com.redmonkeysoftware.digitalocean.logic.SshKeys;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanSshKeyApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanSshKeyApi {

    public DigitalOceanSshKeyApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public SshKeys lookupSshKeys(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "account/keys");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            SshKeys result = client.execute(rb.build(), new SshKeysResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public SshKey createSshKey(String name, String publicKey) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "account/keys")
                    .addParameter("name", name)
                    .addParameter("public_key", publicKey)
                    .build();
            SshKey result = client.execute(request, new SshKeyResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public SshKey lookupSshKey(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "account/keys/" + id).build();
            SshKey result = client.execute(request, new SshKeyResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public SshKey lookupSshKey(String fingerprint) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "account/keys/" + fingerprint).build();
            SshKey result = client.execute(request, new SshKeyResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public SshKey updateSshKey(Long id, String name) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "account/keys/" + id)
                    .addParameter("name", name)
                    .build();
            SshKey result = client.execute(request, new SshKeyResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public SshKey updateSshKey(String fingerprint, String name) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "account/keys/" + fingerprint)
                    .addParameter("name", name)
                    .build();
            SshKey result = client.execute(request, new SshKeyResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteSshKey(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "account/keys/" + id).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteSshKey(String fingerprint) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "account/keys/" + fingerprint).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    protected class SshKeysResponseHandler implements ResponseHandler<SshKeys> {

        @Override
        public SshKeys handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), SshKeys.class);
        }
    }

    protected class SshKeyResponseHandler implements ResponseHandler<SshKey> {

        @Override
        public SshKey handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), SshKey.class);
        }
    }
}
