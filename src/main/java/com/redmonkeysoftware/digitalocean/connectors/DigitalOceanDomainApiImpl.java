package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Domain;
import com.redmonkeysoftware.digitalocean.logic.Domains;
import com.redmonkeysoftware.digitalocean.logic.wrappers.DomainWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanDomainApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanDomainApi {

    protected final static Logger logger = Logger.getLogger(DigitalOceanDomainApiImpl.class.getName());

    public DigitalOceanDomainApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Domains lookupDomains(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "domains");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Domains result = client.execute(rb.build(), new DomainsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Domain createDomain(String domainName, String ipAddress) throws DigitalOceanException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("name", domainName);
            params.put("ip_address", ipAddress);
            String paramString = objectMapper.writeValueAsString(params);
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "domains")
                    .setEntity(new StringEntity(paramString))
                    .build();
            Domain result = client.execute(request, new DomainResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Domain lookupDomain(String domainName) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "domains/" + domainName).build();
            Domain result = client.execute(request, new DomainResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteDomain(String domainName) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "domains/" + domainName).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    protected class DomainsResponseHandler implements ResponseHandler<Domains> {

        @Override
        public Domains handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Domains.class);
        }
    }

    protected class DomainResponseHandler implements ResponseHandler<Domain> {

        @Override
        public Domain handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            DomainWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), DomainWrapper.class);
            return wrapper != null ? wrapper.getDomain() : null;
        }
    }
}
