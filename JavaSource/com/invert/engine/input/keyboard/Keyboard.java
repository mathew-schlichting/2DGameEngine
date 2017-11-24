package com.invert.engine.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Mathew on 5/28/2017.
 */
public class Keyboard implements KeyListener {

    private Key[] keys;

    public Keyboard(Key[] keys){
        this.keys = keys;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(int i=0;i<keys.length;i++){
            if(keys[i].getKeyCode() == e.getKeyCode()) {
                keys[i].setPressed(true);
                if(keys[i].isActionKey())
                    ((ActionKey) keys[i]).press();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(int i=0;i<keys.length;i++){
            if(keys[i].getKeyCode() == e.getKeyCode()) {
                keys[i].setPressed(false);
                if(keys[i].isActionKey())
                    ((ActionKey) keys[i]).release();
            }
        }
    }


    public void keyTyped(KeyEvent e) {}
}
