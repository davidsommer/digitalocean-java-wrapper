package com.redmonkeysoftware.digitalocean.connectors;

import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Image;
import com.redmonkeysoftware.digitalocean.logic.Images;

public interface DigitalOceanImageApi {

    public Images lookupAllImages(Long page) throws DigitalOceanException;

    public Images lookupDistributionImages(Long page) throws DigitalOceanException;

    public Images lookupApplicationImages(Long page) throws DigitalOceanException;

    public Images lookupPrivateImages(Long page) throws DigitalOceanException;

    public Image lookupImage(Long id) throws DigitalOceanException;

    public Image lookupImage(String slug) throws DigitalOceanException;

    public Image updateImage(Long id, String name) throws DigitalOceanException;

    public void deleteImage(Long id) throws DigitalOceanException;

    public Actions lookupImageActions(Long page, Long imageId) throws DigitalOceanException;

    public Action transferImage(Long imageId, String region) throws DigitalOceanException;

    public Action lookupImageAction(Long imageId, Long id) throws DigitalOceanException;
}
