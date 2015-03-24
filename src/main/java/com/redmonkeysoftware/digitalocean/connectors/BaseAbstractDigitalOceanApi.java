package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.AuthenticationException;
import com.redmonkeysoftware.digitalocean.exceptions.GenericException;
import com.redmonkeysoftware.digitalocean.exceptions.RateLimitExceededException;
import com.redmonkeysoftware.digitalocean.exceptions.ResourceNotFoundException;
import com.redmonkeysoftware.digitalocean.logic.Account;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Backups;
import com.redmonkeysoftware.digitalocean.logic.Domain;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import com.redmonkeysoftware.digitalocean.logic.DomainRecords;
import com.redmonkeysoftware.digitalocean.logic.Domains;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import com.redmonkeysoftware.digitalocean.logic.ErrorResponse;
import com.redmonkeysoftware.digitalocean.logic.Image;
import com.redmonkeysoftware.digitalocean.logic.Images;
import com.redmonkeysoftware.digitalocean.logic.Kernels;
import com.redmonkeysoftware.digitalocean.logic.Neighbors;
import com.redmonkeysoftware.digitalocean.logic.Regions;
import com.redmonkeysoftware.digitalocean.logic.Sizes;
import com.redmonkeysoftware.digitalocean.logic.Snapshots;
import com.redmonkeysoftware.digitalocean.logic.SshKey;
import com.redmonkeysoftware.digitalocean.logic.SshKeys;
import com.redmonkeysoftware.digitalocean.logic.Upgrade;
import com.redmonkeysoftware.digitalocean.logic.wrappers.AccountWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.ActionWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.DomainRecordWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.DomainWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.DropletWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.ImageWrapper;
import com.redmonkeysoftware.digitalocean.logic.wrappers.SshKeyWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class BaseAbstractDigitalOceanApi {

    protected final ObjectMapper objectMapper;
    protected final CloseableHttpClient client;
    protected final String baseUrl;

    public BaseAbstractDigitalOceanApi(final ObjectMapper objectMapper,
            final CloseableHttpClient client,
            final String baseUrl) {
        this.objectMapper = objectMapper;
        this.client = client;
        this.baseUrl = baseUrl;
    }

    protected final void checkResponse(HttpResponse response) throws IOException {
        if ((response != null) && (response.getStatusLine() != null)) {
            int code = response.getStatusLine().getStatusCode();
            if ((code >= 200) && (code < 300)) {
                //ignore as fine
            } else if ((code == 401) || (code == 403) || (code == 407)) {
                throw new AuthenticationException(code, parseErrorResponse(response));
            } else if (code == 429) {
                throw new RateLimitExceededException(code, parseErrorResponse(response));
            } else if (code == 404) {
                throw new ResourceNotFoundException(code, parseErrorResponse(response));
            } else {
                throw new GenericException(code, parseErrorResponse(response));
            }
        }
    }

    protected class AccountResponseHandler implements ResponseHandler<Account> {

        @Override
        public Account handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            AccountWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), AccountWrapper.class);
            return wrapper != null ? wrapper.getAccount() : null;
        }
    }

    protected class ActionsResponseHandler implements ResponseHandler<Actions> {

        @Override
        public Actions handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Actions.class);
        }
    }

    protected class ActionResponseHandler implements ResponseHandler<Action> {

        @Override
        public Action handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            ActionWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), ActionWrapper.class);
            return wrapper != null ? wrapper.getAction() : null;
        }
    }

    protected class SizesResponseHandler implements ResponseHandler<Sizes> {

        @Override
        public Sizes handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Sizes.class);
        }
    }

    protected class RegionsResponseHandler implements ResponseHandler<Regions> {

        @Override
        public Regions handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Regions.class);
        }
    }

    protected class DomainsResponseHandler implements ResponseHandler<Domains> {

        @Override
        public Domains handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Domains.class);
        }
    }

    protected class DomainRecordsResponseHandler implements ResponseHandler<DomainRecords> {

        @Override
        public DomainRecords handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), DomainRecords.class);
        }
    }

    protected class DomainRecordResponseHandler implements ResponseHandler<DomainRecord> {

        @Override
        public DomainRecord handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            DomainRecordWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), DomainRecordWrapper.class);
            return wrapper != null ? wrapper.getRecord() : null;
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

    protected class EmptyResponseHandler implements ResponseHandler<Object> {

        @Override
        public Object handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return "Success";
        }
    }

    protected final ErrorResponse parseErrorResponse(HttpResponse response) {
        try {
            return objectMapper.readValue(response.getEntity().getContent(), ErrorResponse.class);
        } catch (IOException | IllegalStateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot parse Error Response", e);
            return null;
        }
    }

    protected class NeighborsResponseHandler implements ResponseHandler<Neighbors> {

        @Override
        public Neighbors handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Neighbors.class);
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

    protected class UpgradesResponseHandler implements ResponseHandler<List<Upgrade>> {

        @Override
        public List<Upgrade> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            List<Upgrade> results = objectMapper.readValue(response.getEntity().getContent(), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Upgrade.class));
            return results;
        }
    }

    protected class KernelsResponseHandler implements ResponseHandler<Kernels> {

        @Override
        public Kernels handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Kernels.class);
        }
    }

    protected class SnapshotsResponseHandler implements ResponseHandler<Snapshots> {

        @Override
        public Snapshots handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Snapshots.class);
        }
    }

    protected class BackupsResponseHandler implements ResponseHandler<Backups> {

        @Override
        public Backups handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Backups.class);
        }
    }

    protected class ImagesResponseHandler implements ResponseHandler<Images> {

        @Override
        public Images handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Images.class);
        }
    }

    protected class ImageResponseHandler implements ResponseHandler<Image> {

        @Override
        public Image handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            ImageWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), ImageWrapper.class);
            return wrapper != null ? wrapper.getImage() : null;
        }
    }

    protected class ImageActionsResponseHandler implements ResponseHandler<Actions> {

        @Override
        public Actions handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return objectMapper.readValue(response.getEntity().getContent(), Actions.class);
        }
    }

    protected class ImageActionResponseHandler implements ResponseHandler<Action> {

        @Override
        public Action handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            ActionWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), ActionWrapper.class);
            return wrapper != null ? wrapper.getAction() : null;
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
            SshKeyWrapper wrapper = objectMapper.readValue(response.getEntity().getContent(), SshKeyWrapper.class);
            return wrapper != null ? wrapper.getKey() : null;
        }
    }
}
