package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.redmonkeysoftware.digitalocean.logic.Domain;
import java.io.Serializable;

public class DomainWrapper implements Serializable {

    private static final long serialVersionUID = -2053912855825149357L;
    protected Domain domain;

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
