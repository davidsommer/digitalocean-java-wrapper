package com.redmonkeysoftware.digitalocean.logic;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class Pages implements Serializable {

    private static final long serialVersionUID = -8541846601143379949L;
    protected String first;
    protected String last;
    protected String prev;
    protected String next;

    public boolean hasNext() {
        return getNextPageNumber() != null;
    }

    public boolean hasPrev() {
        return getPrevPageNumber() != null;
    }

    public Long getCurrentPageNumber() {
        Long nextNum = getNextPageNumber();
        Long prevNum = getPrevPageNumber();
        if (nextNum != null) {
            return nextNum > 1l ? nextNum - 1l : 1l;
        } else {
            if (prevNum != null) {
                return prevNum > 0l ? prevNum + 1l : 1l;
            }
        }
        return 1l;
    }

    public Long getNextPageNumber() {
        return extractPageNumber(next);
    }

    public Long getFirstPageNumber() {
        return 1l;
    }

    public Long getPrevPageNumber() {
        return extractPageNumber(prev);
    }

    public Long getLastPageNumber() {
        return extractPageNumber(last);
    }

    protected Long extractPageNumber(String url) {
        if (StringUtils.isNotBlank(url)) {
            try {
                List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
                for (NameValuePair param : params) {
                    if (StringUtils.equals("page", param.getName())) {
                        return Long.valueOf(param.getValue());
                    }
                }
            } catch (URISyntaxException | NumberFormatException use) {
                Logger.getLogger(Links.class.getName()).log(Level.WARNING, "Error not parsing url and extracting page number: " + url, use);
            }
        }
        return null;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
