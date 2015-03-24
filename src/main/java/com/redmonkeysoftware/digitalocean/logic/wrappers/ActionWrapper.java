package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.redmonkeysoftware.digitalocean.logic.Action;
import java.io.Serializable;

public class ActionWrapper implements Serializable {

    private static final long serialVersionUID = 3563858953619677730L;
    protected Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
