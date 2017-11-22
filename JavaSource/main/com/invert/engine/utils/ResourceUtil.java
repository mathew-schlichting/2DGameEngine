package com.invert.engine.utils;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Mathew on 6/25/2017.
 */
public class ResourceUtil {



    public static URL getResourceURL(String resFolder, String s){
        URL url;

        url = ResourceUtil.class.getResource("/"+s);
        if(url == null){
            url = ResourceUtil.class.getResource(resFolder + s);
        }

        if(url == null){
            GameLogger.logError("Unable to obtain URL from: " + resFolder + s);
        }



        return url;
    }

    public static InputStream getResourceStream(String resFolder, String s){
        InputStream inputStream;

        inputStream = ResourceUtil.class.getResourceAsStream("/"+s);
        if(inputStream == null){
            inputStream = ResourceUtil.class.getResourceAsStream(resFolder + s);
        }

        return inputStream;
    }
}