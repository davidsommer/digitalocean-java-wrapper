package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Region implements Serializable {

    private static final long serialVersionUID = 1411939248060501651L;
    protected String slug;
    protected String name;
    protected List<String> sizes = new ArrayList<>();
    protected boolean available;
    protected List<String> features = new ArrayList<>();

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
