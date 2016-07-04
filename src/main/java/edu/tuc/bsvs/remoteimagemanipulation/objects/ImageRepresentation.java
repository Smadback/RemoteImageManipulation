package edu.tuc.bsvs.remoteimagemanipulation.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by Maik on 06/06/16.
 */
public class ImageRepresentation implements Serializable {

    private int width;
    private int height;
    private int[] pixels;

    public ImageRepresentation(BufferedImage bi) {
        width = bi.getWidth();
        height = bi.getHeight();
        pixels = new int[width * height];
        PixelGrabber pg = new PixelGrabber((Image)bi,0,0,width,height,pixels,0, width);

        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() throws RemoteException {
        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixelImage.setRGB(0, 0, width, height, pixels, 0, width);

        return pixelImage;
    }


    public int[] getPixels() throws RemoteException {
        return pixels;
    }

    public int getWidth() throws RemoteException {
        return width;
    }

    public int getHeight() throws RemoteException {
        return height;
    }
}