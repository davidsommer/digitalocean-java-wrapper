package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import com.redmonkeysoftware.digitalocean.logic.DomainRecords;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanDomainRecordApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanDomainRecordApi {

    public DigitalOceanDomainRecordApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public DomainRecords lookupRecords(Long page, String domainName) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "domains/" + domainName + "/records");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            DomainRecords result = client.execute(rb.build(), new DomainRecordsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public DomainRecord lookupRecord(String domainName, Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "domains/" + domainName + "/records/" + id).build();
            DomainRecord result = client.execute(request, new DomainRecordResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public DomainRecord persistRecord(String domainName, DomainRecord record) throws DigitalOceanException {
        try {
            if (record.getId() == null) {
                HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "domains/" + domainName + "/records")
                        .addParameter("type", record.getType())
                        .addParameter("name", record.getName())
                        .addParameter("data", record.getData())
                        .addParameter("priority", record.getPriority() != null ? record.getPriority().toString() : null)
                        .addParameter("port", record.getPort() != null ? record.getPort().toString() : null)
                        .addParameter("weight", record.getWeight() != null ? record.getWeight().toString() : null)
                        .build();
                DomainRecord result = client.execute(request, new DomainRecordResponseHandler());
                return result;
            } else {
                HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "domains/" + domainName + "/records/" + record.getId())
                        .addParameter("type", record.getType())
                        .addParameter("name", record.getName())
                        .addParameter("data", record.getData())
                        .addParameter("priority", record.getPriority() != null ? record.getPriority().toString() : null)
                        .addParameter("port", record.getPort() != null ? record.getPort().toString() : null)
                        .addParameter("weight", record.getWeight() != null ? record.getWeight().toString() : null)
                        .build();
                DomainRecord result = client.execute(request, new DomainRecordResponseHandler());
                return result;
            }
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteRecord(String domainName, Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "domains/" + domainName + "/records/" + id).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
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
            return objectMapper.readValue(response.getEntity().getContent(), DomainRecord.class);
        }
    }
}