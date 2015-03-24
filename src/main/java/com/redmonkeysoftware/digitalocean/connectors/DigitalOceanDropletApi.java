package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Backups;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import com.redmonkeysoftware.digitalocean.logic.Kernels;
import com.redmonkeysoftware.digitalocean.logic.Neighbors;
import com.redmonkeysoftware.digitalocean.logic.Snapshots;
import com.redmonkeysoftware.digitalocean.logic.Upgrade;
import java.util.List;

public interface DigitalOceanDropletApi {

    public Droplets lookupDroplets(Long page) throws DigitalOceanException;

    public Droplet lookupDroplet(Long id) throws DigitalOceanException;

    public Kernels lookupDropletKernels(Long id) throws DigitalOceanException;

    public Snapshots lookupDropletSnapshots(Long id) throws DigitalOceanException;

    public Actions lookupDropletActions(Long id) throws DigitalOceanException;

    public Droplets lookupDropletNeighbors(Long id) throws DigitalOceanException;

    public Neighbors lookupNeighbors() throws DigitalOceanException;

    public List<Upgrade> lookupUpgrades() throws DigitalOceanException;

    public Backups lookupDropletBackups(Long id) throws DigitalOceanException;

    public Droplet createDroplet(String name, String region, String size,
            Long imageId, List<Integer> sshKeys, Boolean backups, Boolean ipv6,
            Boolean privateNetworking, String userData) throws DigitalOceanException;

    public void deleteDroplet(Long id) throws DigitalOceanException;
}
