package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Images implements Serializable {

    private static final long serialVersionUID = -4735033420097168422L;
    protected List<Image> images = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : images.size();
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
    
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
