package com.redmonkeysoftware.digitalocean.tests;

import com.redmonkeysoftware.digitalocean.DigitalOceanConnector;
import com.redmonkeysoftware.digitalocean.exceptions.DigitalOceanException;
import com.redmonkeysoftware.digitalocean.exceptions.ResourceNotFoundException;
import com.redmonkeysoftware.digitalocean.logic.DomainRecord;
import com.redmonkeysoftware.digitalocean.logic.DomainRecords;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DomainRecordApiTest {

    private DigitalOceanConnector connector;
    private String authToken = null;
    private String testDomainName = "rmscase.com";

    public DomainRecordApiTest() {
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
    public void testLookupDomains() {
        if (authToken != null) {
            try {
                DomainRecords domainRecords = connector.getDomainRecordApi().lookupRecords(null, testDomainName);
                Assert.assertNotNull("Domain Records lookup failed", domainRecords);
                Assert.assertFalse("Domain Records lookup returned empty", domainRecords.getRecords().isEmpty());
                Assert.assertNotNull("Domain Records lookup returned null Domain Record", domainRecords.getRecords().get(0));
                Assert.assertNotNull("Domain Records lookup returned null Domain Record Id", domainRecords.getRecords().get(0).getId());
                DomainRecords paginatedDomainRecords = connector.getDomainRecordApi().lookupRecords(domainRecords.getNextPage() != null ? domainRecords.getNextPage() : 1l, testDomainName);
                Assert.assertNotNull("Paginated Domain Records lookup failed", paginatedDomainRecords);
                Assert.assertFalse("Paginated Domain Records lookup returned empty", paginatedDomainRecords.getRecords().isEmpty());
                Assert.assertNotNull("Paginated Domain Records lookup returned null Domain Record", paginatedDomainRecords.getRecords().get(0));
                Assert.assertNotNull("Paginated Domain Records lookup returned null Domain Record Id", paginatedDomainRecords.getRecords().get(0).getId());
                DomainRecord domainRecord = connector.getDomainRecordApi().lookupRecord(testDomainName, domainRecords.getRecords().get(0).getId());
                Assert.assertNotNull("Domain Record lookup failed", domainRecord);
                Assert.assertNotNull("Domain Record lookup returned null ID", domainRecord.getId());
            } catch (DigitalOceanException doe) {
                Assert.fail("Domain Records test caused an exception: " + doe.getMessage());
            }
        }
    }

    @Test
    public void createDeleteDomainRecord() {
        if (authToken != null) {
            try {
                DomainRecord record = new DomainRecord();
                record.setType("A");
                record.setName("testrecord");
                record.setData("127.0.0.1");
                record = connector.getDomainRecordApi().persistRecord(testDomainName, record);
                Assert.assertNotNull("Domain Record create returned null", record);
                Assert.assertNotNull("Domain Record create returned null Id", record.getId());
                Assert.assertEquals("Domain Record Name differs from created Domain Record Name", "testrecord", record.getName());
                record = connector.getDomainRecordApi().lookupRecord(testDomainName, record.getId());
                Assert.assertNotNull("Domain Record lookup returned null", record);
                connector.getDomainRecordApi().deleteRecord(testDomainName, record.getId());
                try {
                    record = connector.getDomainRecordApi().lookupRecord(testDomainName, record.getId());
                    Assert.assertNull("Domain Record lookup for deleted domain did not return null", record);
                } catch (DigitalOceanException de) {
                    Assert.assertTrue("Domain Record lookup for deleted domain did not give the expected error", de.getCause() instanceof ResourceNotFoundException);
                }
            } catch (DigitalOceanException doe) {
                Assert.fail("Domain Records create / delete test caused an exception: " + doe.getMessage());
            }
        }
    }
}
