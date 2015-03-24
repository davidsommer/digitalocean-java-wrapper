package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;

public class Meta implements Serializable {

    private static final long serialVersionUID = -2654083673902282902L;
    protected Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
