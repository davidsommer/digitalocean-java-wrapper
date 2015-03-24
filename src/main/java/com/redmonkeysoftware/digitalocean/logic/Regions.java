package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Regions implements Serializable {

    private static final long serialVersionUID = 4046304861037737477L;
    protected List<Region> regions = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : regions.size();
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
    
    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
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
