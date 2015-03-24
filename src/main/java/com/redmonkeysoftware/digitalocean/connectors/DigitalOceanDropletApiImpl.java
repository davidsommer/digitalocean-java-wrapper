package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Backups;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import com.redmonkeysoftware.digitalocean.logic.Kernels;
import com.redmonkeysoftware.digitalocean.logic.Neighbors;
import com.redmonkeysoftware.digitalocean.logic.Snapshots;
import com.redmonkeysoftware.digitalocean.logic.Upgrade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanDropletApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanDropletApi {

    public DigitalOceanDropletApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Droplets lookupDroplets(Long page) throws DigitalOceanException {
        try {
            RequestBuilder rb = RequestBuilder.get().setUri(baseUrl + "droplets");
            if (page != null) {
                rb.addParameter("page", String.valueOf(page));
            }
            Droplets result = client.execute(rb.build(), new DropletsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Droplet lookupDroplet(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id).build();
            Droplet result = client.execute(request, new DropletResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Kernels lookupDropletKernels(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/kernels").build();
            Kernels result = client.execute(request, new KernelsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Snapshots lookupDropletSnapshots(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/snapshots").build();
            Snapshots result = client.execute(request, new SnapshotsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Backups lookupDropletBackups(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/backups").build();
            Backups result = client.execute(request, new BackupsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Actions lookupDropletActions(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/actions").build();
            Actions result = client.execute(request, new ActionsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Droplets lookupDropletNeighbors(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + id + "/neighbors").build();
            Droplets result = client.execute(request, new DropletsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Neighbors lookupNeighbors() throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "reports/droplet_neighbors").build();
            Neighbors result = client.execute(request, new NeighborsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public List<Upgrade> lookupUpgrades() throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplet_upgrades").build();
            List<Upgrade> results = client.execute(request, new UpgradesResponseHandler());
            return results;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Droplet createDroplet(String name, String region, String size,
            Long imageId, List<Integer> sshKeys, Boolean backups, Boolean ipv6,
            Boolean privateNetworking, String userData) throws DigitalOceanException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("name", name);
            params.put("region", region);
            params.put("size", size);
            params.put("image", String.valueOf(imageId));
            if ((sshKeys != null) && (!sshKeys.isEmpty())) {
                params.put("ssh_keys", objectMapper.writeValueAsString(sshKeys));
            }
            if (backups != null) {
                params.put("backups", backups.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (ipv6 != null) {
                params.put("ipv6", ipv6.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (privateNetworking != null) {
                params.put("private_networking", privateNetworking.equals(Boolean.TRUE) ? "true" : "false");
            }
            if (StringUtils.isNotBlank(userData)) {
                params.put("user_data", userData);
            }
            String paramString = objectMapper.writeValueAsString(params);
            RequestBuilder rb = RequestBuilder.post().setUri(baseUrl + "droplets").setEntity(new StringEntity(paramString));
            Droplet result = client.execute(rb.build(), new DropletResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public void deleteDroplet(Long id) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.delete().setUri(baseUrl + "droplets/" + id).build();
            client.execute(request, new EmptyResponseHandler());
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }
}
