package com.redmonkeysoftware.digitalocean.tests;

import com.redmonkeysoftware.digitalocean.DigitalOceanConnector;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.logic.Account;
import com.redmonkeysoftware.digitalocean.logic.Action;
import com.redmonkeysoftware.digitalocean.logic.Actions;
import com.redmonkeysoftware.digitalocean.logic.Region;
import com.redmonkeysoftware.digitalocean.logic.Regions;
import com.redmonkeysoftware.digitalocean.logic.Size;
import com.redmonkeysoftware.digitalocean.logic.Sizes;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountApiTest {

    private Logger logger = Logger.getLogger(AccountApiTest.class.getName());
    private DigitalOceanConnector connector;
    private String authToken = null;

    public AccountApiTest() {

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
    public void testLookupAccount() {
        if (authToken != null) {
            try {
                Account account = connector.getAccountApi().lookupAccount();
                logger.log(Level.INFO, "Account: {0}", account.getUuid());
                Assert.assertNotNull("Account lookup failed", account);
                Assert.assertNotNull("Account UUID is null", account.getUuid());
            } catch (DigitalOceanException doe) {
                Assert.fail("Account test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupActions() {
        if (authToken != null) {
            try {
                Actions actions = connector.getAccountApi().lookupActions(null);
                for (Action action : actions.getActions()) {
                    logger.log(Level.INFO, "Action: {0} - {1}", new Object[]{action.getId(), action.getType()});
                }
                Assert.assertNotNull("Actions lookup failed", actions);
                Assert.assertFalse("Actions lookup returned empty", actions.getActions().isEmpty());
                Assert.assertNotNull("Actions lookup returned null Action", actions.getActions().get(0));
                Assert.assertNotNull("Actions lookup returned null Action Id", actions.getActions().get(0).getId());
                Actions paginatedActions = connector.getAccountApi().lookupActions(actions.getNextPage() != null ? actions.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Actions lookup failed", paginatedActions);
                Assert.assertFalse("Paginated Actions lookup returned empty", paginatedActions.getActions().isEmpty());
                Assert.assertNotNull("Paginated Actions lookup returned null Action", paginatedActions.getActions().get(0));
                Assert.assertNotNull("Paginated Actions lookup returned null Action Id", paginatedActions.getActions().get(0).getId());
                Action action = connector.getAccountApi().lookupAction(actions.getActions().get(0).getId());
                Assert.assertNotNull("Action lookup failed", action);
                Assert.assertNotNull("Action lookup returned null id", action.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Actions test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupSizes() {
        if (authToken != null) {
            try {
                Sizes sizes = connector.getAccountApi().lookupSizes(null);
                for (Size size : sizes.getSizes()) {
                    logger.log(Level.INFO, "Size: {0}", size.getSlug());
                }
                Assert.assertNotNull("Sizes lookup failed", sizes);
                Assert.assertFalse("Sizes lookup returned empty", sizes.getSizes().isEmpty());
                Assert.assertNotNull("Sizes lookup returned null Size", sizes.getSizes().get(0));
                Assert.assertNotNull("Sizes lookup returned null Size Slug", sizes.getSizes().get(0).getSlug());
                Sizes paginatedSizes = connector.getAccountApi().lookupSizes(sizes.getNextPage() != null ? sizes.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Sizes lookup failed", paginatedSizes);
                Assert.assertFalse("Paginated Sizes lookup returned empty", paginatedSizes.getSizes().isEmpty());
                Assert.assertNotNull("Paginated Sizes lookup returned null Size", paginatedSizes.getSizes().get(0));
                Assert.assertNotNull("Paginated Sizes lookup returned null Size Slug", paginatedSizes.getSizes().get(0).getSlug());
            } catch (DigitalOceanException doe) {
                Assert.fail("Sizes test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void testLookupRegions() {
        if (authToken != null) {
            try {
                Regions regions = connector.getAccountApi().lookupRegions(null);
                for (Region region : regions.getRegions()) {
                    logger.log(Level.INFO, "Region: {0} - {1}", new Object[]{region.getSlug(), region.getName()});
                }
                Assert.assertNotNull("Regions lookup failed", regions);
                Assert.assertFalse("Regions lookup returned empty", regions.getRegions().isEmpty());
                Assert.assertNotNull("Regions lookup returned null Region", regions.getRegions().get(0));
                Assert.assertNotNull("Regions lookup returned null Region Slug", regions.getRegions().get(0).getSlug());
                Regions paginatedRegions = connector.getAccountApi().lookupRegions(regions.getNextPage() != null ? regions.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Regions lookup failed", paginatedRegions);
                Assert.assertFalse("Paginated Regions lookup returned empty", paginatedRegions.getRegions().isEmpty());
                Assert.assertNotNull("Paginated Regions lookup returned null Region", paginatedRegions.getRegions().get(0));
                Assert.assertNotNull("Paginated Regions lookup returned null Region Slug", paginatedRegions.getRegions().get(0).getSlug());
            } catch (DigitalOceanException doe) {
                Assert.fail("Regions test caused an exception: " + doe.getMessage());
            }
        }
    }
}
