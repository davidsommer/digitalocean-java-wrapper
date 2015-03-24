package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Image;
import com.redmonkeysoftware.digitalocean.logic.Images;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
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
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "images/" + id).build();
            Image result = client.execute(request, new ImageResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Image lookupImage(String slug) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "images/" + slug).build();
            Image result = client.execute(request, new ImageResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Image updateImage(Long id, String name) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.put().setUri(baseUrl + "images/" + id)
                    .addParameter("name", name)
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
            HttpUriRequest request = RequestBuilder.post().setUri(baseUrl + "images/" + imageId + "/actions")
                    .addParameter("type", "transfer")
                    .addParameter("region", region)
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
            return objectMapper.readValue(response.getEntity().getContent(), Image.class);
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
            return objectMapper.readValue(response.getEntity().getContent(), Action.class);
        }
    }
}
