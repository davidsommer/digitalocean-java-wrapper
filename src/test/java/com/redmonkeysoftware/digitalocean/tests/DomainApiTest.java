package com.redmonkeysoftware.digitalocean.tests;

import com.redmonkeysoftware.digitalocean.DigitalOceanConnector;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.exceptions.ResourceNotFoundException;
import com.redmonkeysoftware.digitalocean.logic.Domain;
import com.redmonkeysoftware.digitalocean.logic.Domains;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DomainApiTest {

    private DigitalOceanConnector connector;
    private String authToken = null;
    private String testDomain = "testdomainhjk567.com";

    public DomainApiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        connector = new DigitalOceanConnector(null);
    }

    @After
    public void tearDown() {
        connector.close();
    }

    @Test
    public void testLookupDomains() {
        if (authToken != null) {
            try {
                Domains domains = connector.getDomainApi().lookupDomains(null);
                Assert.assertNotNull("Domains lookup failed", domains);
                Assert.assertFalse("Domains lookup returned empty", domains.getDomains().isEmpty());
                Assert.assertNotNull("Domains lookup returned null Domain", domains.getDomains().get(0));
                Assert.assertNotNull("Domains lookup returned null Domain Name", domains.getDomains().get(0).getName());
                Domains paginatedDomains = connector.getDomainApi().lookupDomains(domains.getNextPage() != null ? domains.getNextPage() : 1l);
                Assert.assertNotNull("Paginated Domains lookup failed", paginatedDomains);
                Assert.assertFalse("Paginated Domains lookup returned empty", paginatedDomains.getDomains().isEmpty());
                Assert.assertNotNull("Paginated Domains lookup returned null Domain", paginatedDomains.getDomains().get(0));
                Assert.assertNotNull("Paginated Domains lookup returned null Domain Name", paginatedDomains.getDomains().get(0).getName());
                Domain domain = connector.getDomainApi().lookupDomain(domains.getDomains().get(0).getName());
                Assert.assertNotNull("Domain lookup failed", domain);
                Assert.assertNotNull("Domain lookup returned null Name", domain.getName());
            } catch (DigitalOceanException doe) {
                Assert.fail("Domains test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void createDeleteDomain() {
        if (authToken != null) {
            try {
                Domain domain = connector.getDomainApi().createDomain(testDomain, "127.0.0.1");
                Assert.assertNotNull("Domain create returned null", domain);
                Assert.assertEquals("Domain Name differs from created domain", testDomain, domain.getName());
                domain = connector.getDomainApi().lookupDomain(testDomain);
                Assert.assertNotNull("Domain lookup returned null", domain);
                Assert.assertEquals("Domain Name differs from searched domain", testDomain, domain.getName());
                connector.getDomainApi().deleteDomain(testDomain);
                try {
                    domain = connector.getDomainApi().lookupDomain(testDomain);
                    Assert.assertNull("Domain lookup for deleted domain did not return null", domain);
                } catch (DigitalOceanException de) {
                    Assert.assertTrue("Domain lookup for deleted domain did not give the expected error", de.getCause() instanceof ResourceNotFoundException);
                }
            } catch (DigitalOceanException doe) {
                Assert.fail("Domains create / delete test caused an exception: " + doe.getMessage());
            }
        }
    }
}
