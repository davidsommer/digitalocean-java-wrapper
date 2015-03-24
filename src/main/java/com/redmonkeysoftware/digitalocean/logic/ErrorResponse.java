package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -1128715327799464195L;
    protected String id;
    protected String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
