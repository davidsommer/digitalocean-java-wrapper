package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import java.util.List;

public interface DigitalOceanDropletApi {

    public Droplets lookupDroplets(Long page) throws DigitalOceanException;

    public Droplet lookupDroplet(Long id) throws DigitalOceanException;

    public Droplet createDroplet(String name, String region, String size,
            Long imageId, List<Integer> sshKeys, boolean backups, boolean ipv6,
            boolean privateNetworking, String userData) throws DigitalOceanException;

    public void deleteDroplet(Long id) throws DigitalOceanException;
}
