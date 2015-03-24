package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import com.redmonkeysoftware.digitalocean.logic.Kernels;
import java.util.List;

public interface DigitalOceanDropletApi {

    public Droplets lookupDroplets(Long page) throws DigitalOceanException;

    public Droplet lookupDroplet(Long id) throws DigitalOceanException;

    public Kernels lookupDropletKernels(Long id) throws DigitalOceanException;

    public Droplet createDroplet(String name, String region, String size,
            Long imageId, List<Integer> sshKeys, Boolean backups, Boolean ipv6,
            Boolean privateNetworking, String userData) throws DigitalOceanException;

    public void deleteDroplet(Long id) throws DigitalOceanException;
}
