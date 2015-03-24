package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import com.redmonkeysoftware.digitalocean.logic.DomainRecords;

public interface DigitalOceanDomainRecordApi {

    public DomainRecords lookupRecords(Long page, String domainName) throws DigitalOceanException;

    public DomainRecord lookupRecord(String domainName, Long id) throws DigitalOceanException;

    public DomainRecord persistRecord(String domainName, DomainRecord record) throws DigitalOceanException;

    public void deleteRecord(String domainName, Long id) throws DigitalOceanException;
}
