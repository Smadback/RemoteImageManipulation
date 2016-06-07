package edu.tuc.bsvs.remoteimagemanipulation.objects;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Maik on 06/06/16.
 */
public class ImageRepresentation implements Serializable {

    private BufferedImage bufferedImage = null;

    public ImageRepresentation() {
        // bufferedImage = new BufferedImage();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bi) {
        this.bufferedImage = bi;
    }
}
