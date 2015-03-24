package com.redmonkeysoftware.digitalocean.exceptions;

import com.redmonkeysoftware.digitalocean.logic.ErrorResponse;
import java.io.IOException;

public class GenericException extends IOException {

    private static final long serialVersionUID = 5389504600636981485L;
    private final int code;
    private final ErrorResponse response;

    public GenericException(int code, ErrorResponse response) {
        this.code = code;
        this.response = response;
    }

    public ErrorResponse getError() {
        return response;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Generic Exception - ").append(code).append(": ");
        if (response != null) {
            sb.append(response.getId()).append(" - ").append(response.getMessage());
        }
        return sb.toString();
    }
}
