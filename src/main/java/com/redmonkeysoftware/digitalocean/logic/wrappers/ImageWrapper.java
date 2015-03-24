package com.redmonkeysoftware.digitalocean.logic.wrappers;

import com.redmonkeysoftware.digitalocean.logic.Image;
import java.io.Serializable;

public class ImageWrapper implements Serializable {

    private static final long serialVersionUID = 8191561822020927328L;
    protected Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
