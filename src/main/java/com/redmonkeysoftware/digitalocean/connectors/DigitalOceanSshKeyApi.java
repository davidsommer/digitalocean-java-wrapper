package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.SshKey;
import com.redmonkeysoftware.digitalocean.logic.SshKeys;

public interface DigitalOceanSshKeyApi {

    public SshKeys lookupSshKeys(Long page) throws DigitalOceanException;

    public SshKey createSshKey(String name, String publicKey) throws DigitalOceanException;

    public SshKey lookupSshKey(Long id) throws DigitalOceanException;

    public SshKey lookupSshKey(String fingerprint) throws DigitalOceanException;

    public SshKey updateSshKey(Long id, String name) throws DigitalOceanException;

    public SshKey updateSshKey(String fingerprint, String name) throws DigitalOceanException;

    public void deleteSshKey(Long id) throws DigitalOceanException;

    public void deleteSshKey(String fingerprint) throws DigitalOceanException;
}
