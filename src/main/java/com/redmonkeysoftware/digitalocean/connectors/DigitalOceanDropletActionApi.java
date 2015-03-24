package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;

public interface DigitalOceanDropletActionApi {

    public Actions lookupActions(Long dropletId) throws DigitalOceanException;

    public Action lookupAction(Long dropletId, Long actionId) throws DigitalOceanException;

    public Action disableBackups(Long dropletId) throws DigitalOceanException;

    public Action reboot(Long dropletId) throws DigitalOceanException;

    public Action powerCycle(Long dropletId) throws DigitalOceanException;

    public Action shutdown(Long dropletId) throws DigitalOceanException;

    public Action powerOff(Long dropletId) throws DigitalOceanException;

    public Action powerOn(Long dropletId) throws DigitalOceanException;

    public Action restore(Long dropletId, Long imageId) throws DigitalOceanException;

    public Action restore(Long dropletId, String imageSlug) throws DigitalOceanException;

    public Action passwordReset(Long dropletId) throws DigitalOceanException;

    public Action resize(Long dropletId, Boolean resizeDisk, String sizeSlug) throws DigitalOceanException;

    public Action rebuild(Long dropletId, Long imageId) throws DigitalOceanException;

    public Action rebuild(Long dropletId, String imageSlug) throws DigitalOceanException;

    public Action rename(Long dropletId, String name) throws DigitalOceanException;

    public Action changeKernel(Long dropletId, Long kernelId) throws DigitalOceanException;

    public Action enableIpv6(Long dropletId) throws DigitalOceanException;

    public Action enablePrivateNetworking(Long dropletId) throws DigitalOceanException;

    public Action snapshot(Long dropletId) throws DigitalOceanException;

    public Action upgrade(Long dropletId) throws DigitalOceanException;
}
