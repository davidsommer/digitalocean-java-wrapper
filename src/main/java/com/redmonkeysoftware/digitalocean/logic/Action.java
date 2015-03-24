package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Calendar;

public class Action implements Serializable {

    private static final long serialVersionUID = -9213504877238512679L;
    protected Long id;
    protected String status;
    protected String type;
    protected Calendar started;
    protected Calendar completed;
    protected Long resourceId;
    protected String resourceType;
    protected String regionSlug;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("started_at")
    public Calendar getStarted() {
        return started;
    }

    public void setStarted(Calendar started) {
        this.started = started;
    }

    @JsonProperty("completed_at")
    public Calendar getCompleted() {
        return completed;
    }

    public void setCompleted(Calendar completed) {
        this.completed = completed;
    }

    @JsonProperty("resource_id")
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @JsonProperty("resource_type")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty("region_slug")
    public String getRegionSlug() {
        return regionSlug;
    }

    public void setRegionSlug(String regionSlug) {
        this.regionSlug = regionSlug;
    }

}
