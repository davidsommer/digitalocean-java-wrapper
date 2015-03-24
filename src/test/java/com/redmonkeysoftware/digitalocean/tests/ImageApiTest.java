package com.redmonkeysoftware.digitalocean.tests;

import com.redmonkeysoftware.digitalocean.DigitalOceanConnector;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Image;
import com.redmonkeysoftware.digitalocean.logic.Images;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImageApiTest {

    private Logger logger = Logger.getLogger(ImageApiTest.class.getName());
    private DigitalOceanConnector connector;
    private String authToken = null;

    public ImageApiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        connector = new DigitalOceanConnector(authToken);
    }

    @After
    public void tearDown() {
        connector.close();
    }

    @Test
    public void testLookupAllImages() {
        if (authToken != null) {
            try {
                Images images = connector.getImageApi().lookupAllImages(null);
                for (Image image : images.getImages()) {
                    logger.log(Level.INFO, "All Image: {0} - {1}", new Object[]{image.getId(), image.getName()});
                }
                Assert.assertNotNull("Images lookup failed", images);
                Assert.assertFalse("Images lookup returned empty", images.getImages().isEmpty());
                Assert.assertNotNull("Images lookup returned null Image", images.getImages().get(0));
                Assert.assertNotNull("Images lookup returned null Image Id", images.getImages().get(0).getId());
                Images paginatedImages = connector.getImageApi().lookupAllImages(images.getNextPage() != null ? images.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Images lookup failed", paginatedImages);
                Assert.assertFalse("Paginated Images lookup returned empty", paginatedImages.getImages().isEmpty());
                Assert.assertNotNull("Paginated Images lookup returned null Image", paginatedImages.getImages().get(0));
                Assert.assertNotNull("Paginated Images lookup returned null Image Id", paginatedImages.getImages().get(0).getId());
                Image image = connector.getImageApi().lookupImage(images.getImages().get(0).getId());
                Assert.assertNotNull("Image lookup failed", image);
                Assert.assertNotNull("Image lookup returned null id", image.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Images test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupDistributionImages() {
        if (authToken != null) {
            try {
                Images images = connector.getImageApi().lookupDistributionImages(null);
                for (Image image : images.getImages()) {
                    logger.log(Level.INFO, "Distribution Image: {0} - {1}", new Object[]{image.getId(), image.getName()});
                }
                Assert.assertNotNull("Images lookup failed", images);
                Assert.assertFalse("Images lookup returned empty", images.getImages().isEmpty());
                Assert.assertNotNull("Images lookup returned null Image", images.getImages().get(0));
                Assert.assertNotNull("Images lookup returned null Image Id", images.getImages().get(0).getId());
                Images paginatedImages = connector.getImageApi().lookupDistributionImages(images.getNextPage() != null ? images.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Images lookup failed", paginatedImages);
                Assert.assertFalse("Paginated Images lookup returned empty", paginatedImages.getImages().isEmpty());
                Assert.assertNotNull("Paginated Images lookup returned null Image", paginatedImages.getImages().get(0));
                Assert.assertNotNull("Paginated Images lookup returned null Image Id", paginatedImages.getImages().get(0).getId());
                Image image = connector.getImageApi().lookupImage(images.getImages().get(0).getId());
                Assert.assertNotNull("Image lookup failed", image);
                Assert.assertNotNull("Image lookup returned null id", image.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Images test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupApplicationImages() {
        if (authToken != null) {
            try {
                Images images = connector.getImageApi().lookupApplicationImages(null);
                for (Image image : images.getImages()) {
                    logger.log(Level.INFO, "Application Image: {0} - {1}", new Object[]{image.getId(), image.getName()});
                }
                Assert.assertNotNull("Images lookup failed", images);
                Assert.assertFalse("Images lookup returned empty", images.getImages().isEmpty());
                Assert.assertNotNull("Images lookup returned null Image", images.getImages().get(0));
                Assert.assertNotNull("Images lookup returned null Image Id", images.getImages().get(0).getId());
                Images paginatedImages = connector.getImageApi().lookupApplicationImages(images.getNextPage() != null ? images.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Images lookup failed", paginatedImages);
                Assert.assertFalse("Paginated Images lookup returned empty", paginatedImages.getImages().isEmpty());
                Assert.assertNotNull("Paginated Images lookup returned null Image", paginatedImages.getImages().get(0));
                Assert.assertNotNull("Paginated Images lookup returned null Image Id", paginatedImages.getImages().get(0).getId());
                Image image = connector.getImageApi().lookupImage(images.getImages().get(0).getId());
                Assert.assertNotNull("Image lookup failed", image);
                Assert.assertNotNull("Image lookup returned null id", image.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Images test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupPrivateImages() {
        if (authToken != null) {
            try {
                Images images = connector.getImageApi().lookupPrivateImages(null);
                for (Image image : images.getImages()) {
                    logger.log(Level.INFO, "Private Image: {0} - {1}", new Object[]{image.getId(), image.getName()});
                }
                Assert.assertNotNull("Images lookup failed", images);
                Assert.assertFalse("Images lookup returned empty", images.getImages().isEmpty());
                Assert.assertNotNull("Images lookup returned null Image", images.getImages().get(0));
                Assert.assertNotNull("Images lookup returned null Image Id", images.getImages().get(0).getId());
                Images paginatedImages = connector.getImageApi().lookupPrivateImages(images.getNextPage() != null ? images.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Images lookup failed", paginatedImages);
                Assert.assertFalse("Paginated Images lookup returned empty", paginatedImages.getImages().isEmpty());
                Assert.assertNotNull("Paginated Images lookup returned null Image", paginatedImages.getImages().get(0));
                Assert.assertNotNull("Paginated Images lookup returned null Image Id", paginatedImages.getImages().get(0).getId());
                Image image = connector.getImageApi().lookupImage(images.getImages().get(0).getId());
                Assert.assertNotNull("Image lookup failed", image);
                Assert.assertNotNull("Image lookup returned null id", image.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Images test caused an exception: " + doe.getMessage());
            }
        }
    }

}
