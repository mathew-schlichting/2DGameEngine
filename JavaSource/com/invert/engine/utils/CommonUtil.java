package com.invert.engine.utils;

import java.awt.*;

/**
 * Created by Mathew on 7/16/2017.
 */
public class CommonUtil {



    public static int getStringLength(Graphics2D g, String s){
        return g.getFontMetrics().stringWidth(s);
    }

    public static int createId(){
        return (int) (Math.floor(Math.random() * 100000000));
    }
}
