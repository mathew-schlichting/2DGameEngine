package com.invert.engine.objects;

import java.awt.*;

/**
 * Created by Mathew on 6/27/2017.
 */
public interface GameObjectI {

    public void tick();
    public void render(Graphics2D g, float v);
}
