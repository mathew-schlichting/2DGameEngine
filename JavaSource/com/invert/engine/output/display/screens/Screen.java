package com.invert.engine.output.display.screens;

import com.invert.engine.input.mouse.Clickable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mathew on 7/8/2017.
 */
public abstract class Screen implements Clickable {

    private ArrayList<Button> buttons;

    public Screen(Button[] buttons){
        this.buttons = new ArrayList<>();
        for(int i=0;i<buttons.length;i++)
            this.buttons.add(buttons[i]);
    }

    @Override
    public void leftPress(int x, int y){
        for(int i=0;i<buttons.size();i++){
            if(buttons.get(i).contains(x, y, 1, 1)){
                buttons.get(i).onPress();
            }
        }
    }

    @Override
    public void leftRelease(int x, int y){
        for(int i=0;i<buttons.size();i++){
            if(buttons.get(i).contains(x, y, 1, 1)){
                buttons.get(i).onRelease();
            }
        }
    }


    public void tick(){
        for(int i=0;i<buttons.size();i++){
            buttons.get(i).tick();
        }
    }

    public void render(Graphics2D g, float v){
        for(int i=0;i<buttons.size();i++){
            buttons.get(i).render(g, v);
        }
    }


}
