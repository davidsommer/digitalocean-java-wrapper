package com.redmonkeysoftware.digitalocean.tests;

import com.redmonkeysoftware.digitalocean.DigitalOceanConnector;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.exceptions.ResourceNotFoundException;
import com.redmonkeysoftware.digitalocean.logic.Droplet;
import com.redmonkeysoftware.digitalocean.logic.Droplets;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DropletApiTest {

    private DigitalOceanConnector connector;
    private String authToken = null;
    private final Long imageId = 10616968l;

    public DropletApiTest() {
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
    public void testLookupDroplets() {
        if (authToken != null) {
            try {
                Droplets droplets = connector.getDropletApi().lookupDroplets(null);
                Assert.assertNotNull("Droplets lookup failed", droplets);
                Assert.assertFalse("Droplets lookup returned empty", droplets.getDroplets().isEmpty());
                Assert.assertNotNull("Droplets lookup returned null Droplet", droplets.getDroplets().get(0));
                Assert.assertNotNull("Droplets lookup returned null Droplet Id", droplets.getDroplets().get(0).getId());
                Droplets paginatedDroplets = connector.getDropletApi().lookupDroplets(droplets.getNextPage() != null ? droplets.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Droplets lookup failed", paginatedDroplets);
                Assert.assertFalse("Paginated Droplets lookup returned empty", paginatedDroplets.getDroplets().isEmpty());
                Assert.assertNotNull("Paginated Droplets lookup returned null Droplet", paginatedDroplets.getDroplets().get(0));
                Assert.assertNotNull("Paginated Droplets lookup returned null Droplet Id", paginatedDroplets.getDroplets().get(0).getId());
                Droplet droplet = connector.getDropletApi().lookupDroplet(droplets.getDroplets().get(0).getId());
                Assert.assertNotNull("Droplet lookup failed", droplet);
                Assert.assertNotNull("Droplet lookup returned null ID", droplet.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Droplets test caused an exception: " + doe.getMessage());
            }
        }
    }

    //@Test
    public void createDeleteDroplet() {
        if (authToken != null) {
            try {
                Droplet droplet = connector.getDropletApi().createDroplet("testdroplet", "lon1", "2gb", imageId,
                        new ArrayList(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, null);
                Assert.assertNotNull("Droplet create failed", droplet);
                Assert.assertNotNull("Droplet create returned null ID", droplet.getId());
                droplet = connector.getDropletApi().lookupDroplet(droplet.getId());
                Assert.assertNotNull("Droplet lookup after create failed", droplet);
                Assert.assertNotNull("Droplet lookup after create returned null ID", droplet.getId());
                connector.getDropletApi().deleteDroplet(droplet.getId());
                try {
                    droplet = connector.getDropletApi().lookupDroplet(droplet.getId());
                    Assert.assertNull("Droplet lookup for deleted droplet did not return null", droplet);
                } catch (DigitalOceanException de) {
                    Assert.assertTrue("Droplet lookup for deleted droplet did not give the expected error", de.getCause() instanceof ResourceNotFoundException);
                }
            } catch (DigitalOceanException doe) {
                Assert.fail("Droplets create / delete test caused an exception: " + doe.getMessage());
            }
        }
    }
}
