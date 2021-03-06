package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Image;
import com.redmonkeysoftware.digitalocean.logic.Images;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanImageApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanImageApi {

    public DigitalOceanImageApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Images lookupAllImages(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "images");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Images result = client.execute(rb.build(), new ImagesResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Images lookupDistributionImages(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "images")
                    .addParameter("type", "distribution");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Images result = client.execute(rb.build(), new ImagesResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Images lookupApplicationImages(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "images")
                    .addParameter("type", "application");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Images result = client.execute(rb.build(), new ImagesResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Images lookupPrivateImages(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "images")
                    .addParameter("private", "true");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Images result = client.execute(rb.build(), new ImagesResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Image lookupImage(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "images/" + id).build();
            Image result = client.execute(request, new ImageResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Image lookupImage(String slug) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "images/" + slug).build();
            Image result = client.execute(request, new ImageResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Image updateImage(Long id, String name) throws DigitalOceanException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("name", name);
            String paramString = objectMapper.writeValueAsString(params);
            HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "images/" + id)
                    .setEntity(new StringEntity(paramString))
                    .build();
            Image result = client.execute(request, new ImageResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteImage(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "images/" + id).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Actions lookupImageActions(Long page, Long imageId) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "images/" + imageId + "/actions");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Actions result = client.execute(rb.build(), new ImageActionsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Action transferImage(Long imageId, String region) throws DigitalOceanException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("type", "transfer");
            params.put("region", region);
            String paramString = objectMapper.writeValueAsString(params);
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "images/" + imageId + "/actions")
                    .setEntity(new StringEntity(paramString))
                    .build();
            Action result = client.execute(request, new ImageActionResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Action lookupImageAction(Long imageId, Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "images/" + imageId + "/actions/" + id).build();
            Action result = client.execute(request, new ImageActionResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }
}
