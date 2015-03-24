package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DomainRecords implements Serializable {

    private static final long serialVersionUID = 4648680117365779933L;
    protected List<DomainRecord> records = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : records.size();
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
    
    @JsonProperty("domain_records")
    public List<DomainRecord> getRecords() {
        return records;
    }

    public void setRecords(List<DomainRecord> records) {
        this.records = records;
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
