package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.redmonkeysoftware.digitalocean.logic.Account;
import java.io.Serializable;

public class AccountWrapper implements Serializable {

    private static final long serialVersionUID = 1827970370877379263L;
    protected Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
