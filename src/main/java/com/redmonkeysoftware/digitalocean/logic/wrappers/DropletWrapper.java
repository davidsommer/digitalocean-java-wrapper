package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.redmonkeysoftware.digitalocean.logic.Droplet;
import java.io.Serializable;

public class DropletWrapper implements Serializable {

    private static final long serialVersionUID = -1202940887396575723L;
    protected Droplet droplet;

    public Droplet getDroplet() {
        return droplet;
    }

    public void setDroplet(Droplet droplet) {
        this.droplet = droplet;
    }
}
