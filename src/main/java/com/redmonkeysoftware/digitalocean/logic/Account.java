package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = -9101904291840312827L;
    protected String email;
    protected String uuid;
    protected boolean emailVerified;
    protected Integer dropletLimit;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("email_verified")
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @JsonProperty("droplet_limit")
    public Integer getDropletLimit() {
        return dropletLimit;
    }

    public void setDropletLimit(Integer dropletLimit) {
        this.dropletLimit = dropletLimit;
    }

}
