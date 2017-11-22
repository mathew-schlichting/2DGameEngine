package com.invert.engine.output.display.screens;

import com.invert.engine.objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Mathew on 7/8/2017.
 */
public abstract class Button extends GameObject {

    public Button(BufferedImage image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public abstract void onPress();
    public abstract void onRelease();


    @Override
    public void render(Graphics2D g, float v){
        if(getImage() != null){
            super.render(g, v);
        }
    }

}
