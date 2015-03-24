package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Image implements Serializable {

    private static final long serialVersionUID = -6791475835778998801L;
    protected Long id;
    protected String name;
    protected String type;
    protected String distribution;
    protected String slug;
    protected boolean _public;
    protected List<String> regions = new ArrayList<>();
    protected Long minDiskSize;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("public")
    public boolean isPublic() {
        return _public;
    }

    public void setPublic(boolean _public) {
        this._public = _public;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    @JsonProperty("min_disk_size")
    public Long getMinDiskSize() {
        return minDiskSize;
    }

    public void setMinDiskSize(Long minDiskSize) {
        this.minDiskSize = minDiskSize;
    }
}
