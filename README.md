# digitalocean-java-wrapper
Java Wrapper for the Digital Ocean API v2

This is a very simple Java Wrapper for the Digital Ocean API v2 found here:

https://developers.digitalocean.com/documentation/v2/

It does not do anything such as type checking or validation. You are expected to have knowledge of how the api works or have at least looked through the api documentation.

### Usage

You can use the ``DigitalOceanConnector`` class like so:
```java
//Initialise the Digital Ocean Connector with your auth token
DigitalOceanConnector digitalOceanConnector = new DigitalOceanConnector("<authToken>");
//Lookup your Account Details
Account account = digitalOceanConnector.getAccountApi().lookupAccount();
//Lookup a list of droplets, passing null for the page parameter will return by default the first page
Droplets droplets = digitalOceanConnector.getDropletApi().lookupDroplets(null);
//Lookup a list of domains, passing null for the page parameter will return by default the first page
Domains domains = digitalOceanConnector.getDomainApi().lookupDomains(null);
```

You have access to:
 - DigitalOceanAccountApi
 - DigitalOceanDomainApi
 - DigitalOceanDomainRecordApi
 - DigitalOceanDropletApi
 - DigitalOceanDropletActionApi
 - DigitalOceanImageApi
 - DigitalOceanSshKeyApi

### Exception Handling

Each method may throw a DigitalOceanException, you will have to use the getCause() method to understand the cause of the exception.
