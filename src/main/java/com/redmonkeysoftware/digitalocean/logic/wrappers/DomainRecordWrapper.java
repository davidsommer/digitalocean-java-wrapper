package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import java.io.Serializable;

public class DomainRecordWrapper implements Serializable {

    private static final long serialVersionUID = -4173799531708157363L;
    protected DomainRecord record;

    @JsonProperty("domain_record")
    public DomainRecord getRecord() {
        return record;
    }

    public void setRecord(DomainRecord record) {
        this.record = record;
    }
}
