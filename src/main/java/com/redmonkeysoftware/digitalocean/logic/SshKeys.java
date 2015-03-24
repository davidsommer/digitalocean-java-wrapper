package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SshKeys implements Serializable {

    private static final long serialVersionUID = 4100281101096219832L;
    protected List<SshKey> keys = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : keys.size();
    }
    
    public Long getCurrentPage() {
        return links != null ? links.getCurrentPageNumber() : 1l;
    }

    public Long getNextPage() {
        return links != null ? links.getNextPageNumber() : 1l;
    }

    public Long getPrevPage() {
        return links != null ? links.getPrevPageNumber() : 1l;
    }
    
    public Long getTotalPages() {
        return links != null ? links.getLastPageNumber() : 1l;
    }

    public List<SshKey> getKeys() {
        return keys;
    }

    public void setKeys(List<SshKey> keys) {
        this.keys = keys;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
