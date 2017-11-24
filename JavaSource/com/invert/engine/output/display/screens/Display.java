package com.invert.engine.output.display.screens;

import com.invert.engine.input.mouse.Clickable;

import java.awt.*;

/**
 * Created by Mathew on 7/8/2017.
 */
public class Display implements Clickable{

    private Screen screen;


    public Display(Screen screen){
        if(screen == null)
            throw new IllegalArgumentException("Screen cannot be null when creating display");

        this.screen = screen;
    }


    public void changeScreen(Screen screen){
        if(screen == null)
            throw new IllegalArgumentException("Screen cannot be null when changing screens");

        this.screen = screen;
    }


    public void leftPress(int x, int y) {screen.leftPress(x, y);}
    public void leftRelease(int x, int y) {screen.leftRelease(x, y);}
    public void rightPress(int x, int y) {screen.rightPress(x, y);}
    public void rightRelease(int x, int y) {screen.rightPress(x, y);}
    public void click(int x, int y) {screen.click(x, y);}



    public void tick(){
        screen.tick();
    }

    public void render(Graphics2D g, float v){
        screen.render(g, v);
    }
}
