package com.invert.engine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mathew on 6/25/2017.
 */
public class ImageUtil {



    /********************   Image Loading   ***************************/

    public static BufferedImage loadImage(String resFolder, String image) {
        if(image == null)
            throw new IllegalArgumentException("File is null\nLocation: ImageManager.getImage()");

        InputStream inputStream;
        BufferedImage result = null;

        try {

            inputStream = ResourceUtil.getResourceStream(resFolder, image);
            result = ImageIO.read(inputStream);
            inputStream.close();

        }catch (IOException ioe){
            GameLogger.logError("Unable to load Image: " + image);
        }

        return result;
    }
}
