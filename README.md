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

### License

#### The MIT License

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
