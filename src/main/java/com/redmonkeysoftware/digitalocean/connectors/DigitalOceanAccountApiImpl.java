package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Account;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Regions;
import com.redmonkeysoftware.digitalocean.logic.Sizes;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanAccountApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanAccountApi {

    public DigitalOceanAccountApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Account lookupAccount() throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "account").build();
            Account result = client.execute(request, new AccountResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Actions lookupActions(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "actions");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Actions result = client.execute(rb.build(), new ActionsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Action lookupAction(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "actions/" + id).build();
            Action result = client.execute(request, new ActionResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Sizes lookupSizes(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "sizes");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Sizes result = client.execute(rb.build(), new SizesResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Regions lookupRegions(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "regions");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Regions result = client.execute(rb.build(), new RegionsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }
}
