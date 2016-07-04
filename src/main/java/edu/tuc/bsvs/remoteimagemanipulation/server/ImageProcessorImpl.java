package edu.tuc.bsvs.remoteimagemanipulation.server;

import edu.tuc.bsvs.remoteimagemanipulation.objects.ImageRepresentation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

public class ImageProcessorImpl implements ImageProcessor {

    /**
     * Manipulate the colors of the image. Red x 2, Green x 2, Green x 0.5
     * @param image The image to manipulate
     * @return The manipulated image
     * @throws RemoteException
     */
    public ImageRepresentation manipulateImage(ImageRepresentation image) throws RemoteException  {

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage pixelImage = image.getImage();

        // jeden einzelnen Pixel einzeln manipulieren
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Color c = new Color(pixelImage.getRGB(x,y));
                int red = (c.getRed()*2 > 255) ? 255 : c.getRed()*2;
                int green = (c.getGreen()*2 > 255) ? 255 : c.getGreen()*2;
                int blue = ((int)(c.getBlue()*0.5) > 255) ? 255 : (int)(c.getBlue()*0.5);

                Color color = new Color(red, green, blue);
                pixelImage.setRGB(x,y,color.getRGB());
            }
        }

        return new ImageRepresentation(pixelImage);
    }

}
