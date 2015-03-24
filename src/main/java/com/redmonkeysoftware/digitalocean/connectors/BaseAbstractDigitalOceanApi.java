package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.AuthenticationException;
import com.redmonkeysoftware.digitalocean.exceptions.GenericException;
import com.redmonkeysoftware.digitalocean.exceptions.RateLimitExceededException;
import com.redmonkeysoftware.digitalocean.exceptions.ResourceNotFoundException;
import com.redmonkeysoftware.digitalocean.logic.ErrorResponse;
import java.io.IOException;
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
            final CloseableHttpClient client, final String baseUrl) {
        this.objectMapper = objectMapper;
        this.client = client;
        this.baseUrl = baseUrl;
    }

    protected class EmptyResponseHandler implements ResponseHandler<Object> {

        @Override
        public Object handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            checkResponse(response);
            return "Success";
        }
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

    protected final ErrorResponse parseErrorResponse(HttpResponse response) {
        try {
            return objectMapper.readValue(response.getEntity().getContent(), ErrorResponse.class);
        } catch (IOException | IllegalStateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot parse Error Response", e);
            return null;
        }
    }
}
