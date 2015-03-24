package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Domain;
import com.redmonkeysoftware.digitalocean.logic.Domains;

public interface DigitalOceanDomainApi {

    public Domains lookupDomains(Long page) throws DigitalOceanException;

    public Domain createDomain(String domainName, String ipAddress) throws DigitalOceanException;

    public Domain lookupDomain(String domainName) throws DigitalOceanException;

    public void deleteDomain(String domainName) throws DigitalOceanException;
}
