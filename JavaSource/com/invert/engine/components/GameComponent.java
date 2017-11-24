package com.invert.engine.components;

import com.invert.engine.output.display.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Mathew on 5/28/2017.
 */
public abstract class GameComponent extends Canvas {

    private GameEngine engine;


    public GameComponent(double gameHertz, double fps, Dimension size, BufferedImage icon, BufferedImage cursor, boolean fullScreen, double pixelSize){
        engine = new GameEngine(this, gameHertz, fps);
        new Window(size, icon, cursor, this, fullScreen, pixelSize);
    }


    public void init(){this.createBufferStrategy(3);}
    public void start(){engine.start();}
    public void stop(){engine.stop();Window.close();}

    public final void render(float interpolation){
        BufferStrategy bs = this.getBufferStrategy();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        render(g, interpolation);
        g.dispose();
        bs.show();
    }


    public abstract void tick();
    public abstract void render(Graphics2D g, float interpolation);

}