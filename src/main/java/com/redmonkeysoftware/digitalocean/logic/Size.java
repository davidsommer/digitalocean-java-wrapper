package com.redmonkeysoftware.digitalocean.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Size implements Serializable {

    private static final long serialVersionUID = 1108264496256771887L;
    protected String slug;
    protected boolean available;
    protected BigDecimal transfer;
    protected BigDecimal priceMonthly;
    protected BigDecimal priceHourly;
    protected Long vcpus;
    protected Long disk;
    protected List<String> regions = new ArrayList<>();

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getTransfer() {
        return transfer;
    }

    public void setTransfer(BigDecimal transfer) {
        this.transfer = transfer;
    }

    @JsonProperty("price_monthly")
    public BigDecimal getPriceMonthly() {
        return priceMonthly;
    }

    public void setPriceMonthly(BigDecimal priceMonthly) {
        this.priceMonthly = priceMonthly;
    }

    @JsonProperty("price_hourly")
    public BigDecimal getPriceHourly() {
        return priceHourly;
    }

    public void setPriceHourly(BigDecimal priceHourly) {
        this.priceHourly = priceHourly;
    }

    public Long getVcpus() {
        return vcpus;
    }

    public void setVcpus(Long vcpus) {
        this.vcpus = vcpus;
    }

    public Long getDisk() {
        return disk;
    }

    public void setDisk(Long disk) {
        this.disk = disk;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}
