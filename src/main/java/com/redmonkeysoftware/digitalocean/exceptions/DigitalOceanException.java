package com.redmonkeysoftware.digitalocean.exceptions;

public class DigitalOceanException extends Exception {

    private static final long serialVersionUID = -2825290144028652317L;
    private final Exception cause;

    public DigitalOceanException(Exception cause) {
        this.cause = cause;
    }

    @Override
    public Exception getCause() {
        return cause;
    }

    @Override
    public String getMessage() {
        return cause != null ? cause.getMessage() : "Unknown Exception";
    }
}
