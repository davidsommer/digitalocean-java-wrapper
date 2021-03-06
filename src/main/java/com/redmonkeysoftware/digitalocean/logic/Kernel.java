package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;

public class Kernel implements Serializable {

    private static final long serialVersionUID = -1834735016191585930L;
    protected Long id;
    protected String name;
    protected String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
