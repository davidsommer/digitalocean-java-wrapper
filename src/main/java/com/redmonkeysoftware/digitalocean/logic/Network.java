package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Network implements Serializable {

    private static final long serialVersionUID = -5004395573190786991L;
    protected String ipAddress;
    protected String netmask;
    protected String gateway;
    protected String type;

    @JsonProperty("ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
