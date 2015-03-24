package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Account;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Regions;
import com.redmonkeysoftware.digitalocean.logic.Sizes;

public interface DigitalOceanAccountApi {

    public Account lookupAccount() throws DigitalOceanException;

    public Actions lookupActions(Long page) throws DigitalOceanException;

    public Action lookupAction(Long id) throws DigitalOceanException;

    public Sizes lookupSizes(Long page) throws DigitalOceanException;

    public Regions lookupRegions(Long page) throws DigitalOceanException;
}
