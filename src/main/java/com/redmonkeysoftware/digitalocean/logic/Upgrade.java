package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Calendar;

public class Upgrade implements Serializable {

    private static final long serialVersionUID = 2899554540105565995L;
    protected Long dropletId;
    protected Calendar migrationDate;
    protected String url;

    public Long getDropletId() {
        return dropletId;
    }

    public void setDropletId(Long dropletId) {
        this.dropletId = dropletId;
    }

    @JsonProperty("date_of_migration")
    public Calendar getMigrationDate() {
        return migrationDate;
    }

    public void setMigrationDate(Calendar migrationDate) {
        this.migrationDate = migrationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
