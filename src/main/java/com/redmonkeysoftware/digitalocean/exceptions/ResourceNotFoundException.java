package com.redmonkeysoftware.digitalocean.exceptions;

import com.redmonkeysoftware.digitalocean.logic.ErrorResponse;
import java.io.IOException;

public class ResourceNotFoundException extends IOException {

    private static final long serialVersionUID = -7155563219860817876L;
    private final int code;
    private final ErrorResponse response;

    public ResourceNotFoundException(int code, ErrorResponse response) {
        this.code = code;
        this.response = response;
    }

    public ErrorResponse getError() {
        return response;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Resource Not Found Exception - ").append(code).append(": ");
        if (response != null) {
            sb.append(response.getId()).append(" - ").append(response.getMessage());
        }
        return sb.toString();
    }
}
