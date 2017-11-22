package com.invert.engine.input.mouse;

/**
 * Created by Mathew on 5/29/2017.
 */
public interface Clickable {

    public void leftPress(int x, int y);
    public void leftRelease(int x, int y);
    public void rightPress(int x, int y);
    public void rightRelease(int x, int y);
    public void click(int x, int y);
}
