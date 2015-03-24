package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import com.redmonkeysoftware.digitalocean.logic.DomainRecords;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
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
            Map<String, String> params = new HashMap<>();
            params.put("type", record.getType());
            params.put("name", record.getName());
            params.put("data", record.getData());
            params.put("priority", record.getPriority() != null ? String.valueOf(record.getPriority()) : null);
            params.put("port", record.getPort() != null ? record.getPort().toString() : null);
            params.put("weight", record.getWeight() != null ? record.getWeight().toString() : null);
            String paramString = objectMapper.writeValueAsString(params);
            if (record.getId() == null) {
                HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "domains/" + domainName + "/records")
                        .setEntity(new StringEntity(paramString)).build();
                DomainRecord result = client.execute(request, new DomainRecordResponseHandler());
                return result;
            } else {
                HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "domains/" + domainName + "/records/" + record.getId())
                        .setEntity(new StringEntity(paramString)).build();
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
}
