package com.invert.engine.input.mouse;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Mathew on 5/29/2017.
 */
public class Mouse implements MouseListener {

    private Clickable clickable;

    public Mouse(Clickable clickable){
        this.clickable = clickable;
    }



    public static Point getLocation(){return MouseInfo.getPointerInfo().getLocation();}


    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            clickable.leftPress(e.getX(), e.getY());
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            clickable.rightPress(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            clickable.leftRelease(e.getX(), e.getY());
        }
        else if(SwingUtilities.isRightMouseButton(e)){
            clickable.rightRelease(e.getX(), e.getY());
        }
    }

    public void mouseClicked(MouseEvent e){
        clickable.click(e.getX(), e.getY());
    }

    //Not used
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
