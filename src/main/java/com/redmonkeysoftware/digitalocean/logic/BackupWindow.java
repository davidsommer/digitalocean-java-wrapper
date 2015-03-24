package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.util.Calendar;

public class BackupWindow implements Serializable {

    private static final long serialVersionUID = 8643509869486668372L;
    protected Calendar start;
    protected Calendar end;

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
