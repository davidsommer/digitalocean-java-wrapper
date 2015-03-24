package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redmonkeysoftware.digitalocean.logic.SshKey;
import java.io.Serializable;

public class SshKeyWrapper implements Serializable {

    private static final long serialVersionUID = 1451344692573491516L;
    protected SshKey key;

    @JsonProperty("ssh_key")
    public SshKey getKey() {
        return key;
    }

    public void setKey(SshKey key) {
        this.key = key;
    }
}
