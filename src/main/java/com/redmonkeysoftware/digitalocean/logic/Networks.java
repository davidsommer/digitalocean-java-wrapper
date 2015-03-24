package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Networks implements Serializable {

    private static final long serialVersionUID = -2806215947588956064L;
    protected List<Network> v4 = new ArrayList<>();
    protected List<Network> v6 = new ArrayList<>();

    public List<Network> getV4() {
        return v4;
    }

    public void setV4(List<Network> v4) {
        this.v4 = v4;
    }

    public List<Network> getV6() {
        return v6;
    }

    public void setV6(List<Network> v6) {
        this.v6 = v6;
    }
}
