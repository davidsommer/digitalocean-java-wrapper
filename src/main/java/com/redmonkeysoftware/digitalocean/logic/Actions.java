package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Actions implements Serializable {

    private static final long serialVersionUID = -2190065873492402758L;
    protected List<Action> actions = new ArrayList<>();
    protected Links links;
    protected Meta meta;

    public Long getTotalCount() {
        return ((meta != null) && (meta.getTotal() != null)) ? meta.getTotal() : actions.size();
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
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
