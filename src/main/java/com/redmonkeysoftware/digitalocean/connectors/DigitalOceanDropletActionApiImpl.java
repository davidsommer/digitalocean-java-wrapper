package com.redmonkeysoftware.digitalocean.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

public class DigitalOceanDropletActionApiImpl extends BaseAbstractDigitalOceanApi implements DigitalOceanDropletActionApi {

    public DigitalOceanDropletActionApiImpl(final ObjectMapper objectMapper,
            final CloseableHttpClient client, final String baseUrl) {
        super(objectMapper, client, baseUrl);
    }

    @Override
    public Actions lookupActions(Long dropletId) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + dropletId + "/actions").build();
            Actions result = client.execute(request, new ActionsResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Action lookupAction(Long dropletId, Long actionId) throws DigitalOceanException {
        try {
            HttpUriRequest request = RequestBuilder.get().setUri(baseUrl + "droplets/" + dropletId + "/actions/" + actionId).build();
            Action result = client.execute(request, new ActionResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }

    @Override
    public Action disableBackups(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "disable_backups");
        return postAction(dropletId, params);
    }

    @Override
    public Action reboot(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "reboot");
        return postAction(dropletId, params);
    }

    @Override
    public Action powerCycle(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "power_cycle");
        return postAction(dropletId, params);
    }

    @Override
    public Action shutdown(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "shutdown");
        return postAction(dropletId, params);
    }

    @Override
    public Action powerOff(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "power_off");
        return postAction(dropletId, params);
    }

    @Override
    public Action powerOn(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "power_on");
        return postAction(dropletId, params);
    }

    @Override
    public Action restore(Long dropletId, Long imageId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "restore");
        params.put("image", imageId);
        return postAction(dropletId, params);
    }

    @Override
    public Action restore(Long dropletId, String imageSlug) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "restore");
        params.put("image", imageSlug);
        return postAction(dropletId, params);
    }

    @Override
    public Action passwordReset(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "password_reset");
        return postAction(dropletId, params);
    }

    @Override
    public Action resize(Long dropletId, Boolean resizeDisk, String sizeSlug) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "resize");
        params.put("size", sizeSlug);
        if (resizeDisk != null) {
            params.put("disk", resizeDisk);
        }
        return postAction(dropletId, params);
    }

    @Override
    public Action rebuild(Long dropletId, Long imageId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "rebuild");
        params.put("image", imageId);
        return postAction(dropletId, params);
    }

    @Override
    public Action rebuild(Long dropletId, String imageSlug) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "rebuild");
        params.put("image", imageSlug);
        return postAction(dropletId, params);
    }

    @Override
    public Action rename(Long dropletId, String name) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "rename");
        params.put("name", name);
        return postAction(dropletId, params);
    }

    @Override
    public Action changeKernel(Long dropletId, Long kernelId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "change_kernel");
        params.put("kernel", kernelId);
        return postAction(dropletId, params);
    }

    @Override
    public Action enableIpv6(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "enable_ipv6");
        return postAction(dropletId, params);
    }

    @Override
    public Action enablePrivateNetworking(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "enable_private_networking");
        return postAction(dropletId, params);
    }

    @Override
    public Action snapshot(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "snapshot");
        return postAction(dropletId, params);
    }

    @Override
    public Action upgrade(Long dropletId) throws DigitalOceanException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "upgrade");
        return postAction(dropletId, params);
    }

    protected Action postAction(Long dropletId, Map<String, Object> params) throws DigitalOceanException {
        try {
            String paramString = objectMapper.writeValueAsString(params);
            RequestBuilder rb = RequestBuilder.post().setUri(baseUrl + "droplets/" + dropletId + "/actions")
                    .setEntity(new StringEntity(paramString));
            Action result = client.execute(rb.build(), new ActionResponseHandler());
            return result;
        } catch (Exception e) {
            throw new DigitalOceanException(e);
        }
    }
}
