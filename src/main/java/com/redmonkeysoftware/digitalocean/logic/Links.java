package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;

public class Links implements Serializable {

    private static final long serialVersionUID = -523699908234382222L;
    protected Pages pages;

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
