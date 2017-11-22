package com.invert.engine.input.keyboard;

/**
 * Created by Mathew on 5/29/2017.
 */
public class Key {

    private int keyCode;
    private boolean pressed;

    public Key(int keyCode){
        this.keyCode = keyCode;
    }

    public boolean isPressed(){return pressed;}
    public void setPressed(boolean pressed){this.pressed = pressed;}
    public int getKeyCode(){return keyCode;}

    public boolean isActionKey(){return false;}


}
