package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class SshKey implements Serializable {

    private static final long serialVersionUID = 682712694873477481L;
    protected Long id;
    protected String fingerprint;
    protected String publicKey;
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @JsonProperty("public_key")
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
