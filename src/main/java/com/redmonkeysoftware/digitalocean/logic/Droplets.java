package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Droplets implements Serializable {

    private static final long serialVersionUID = 2476813290277503243L;
    protected List<Droplet> droplets = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : droplets.size();
    }
    
    public Long getCurrentPage() {
        return ((links != null)&&(links.getPages() != null)) ? links.getPages().getCurrentPageNumber() : 1l;
    }

    public Long getNextPage() {
        return ((links != null)&&(links.getPages() != null)) ? links.getPages().getNextPageNumber() : null;
    }

    public Long getPrevPage() {
        return ((links != null)&&(links.getPages() != null)) ? links.getPages().getPrevPageNumber() : 1l;
    }
    
    public Long getTotalPages() {
        return ((links != null)&&(links.getPages() != null)) ? links.getPages().getLastPageNumber() : 1l;
    }
    
    public List<Droplet> getDroplets() {
        return droplets;
    }

    public void setDroplets(List<Droplet> droplets) {
        this.droplets = droplets;
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
