package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Domain implements Serializable {

    private static final long serialVersionUID = -9047289261582397004L;
    protected String name;
    protected Long ttl;
    protected String zoneFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("zone_file")
    public String getZoneFile() {
        return zoneFile;
    }

    public void setZoneFile(String zoneFile) {
        this.zoneFile = zoneFile;
    }
}
