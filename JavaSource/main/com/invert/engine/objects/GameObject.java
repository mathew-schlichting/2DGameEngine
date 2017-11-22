package com.invert.engine.objects;

import com.invert.engine.output.display.Window;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Mathew on 6/27/2017.
 */
public abstract class GameObject implements GameObjectI, Serializable {

    public static final long serialVersionUID = 1L;

    private int x, y;
    private int width, height;
    private int lastX, lastY;
    private BufferedImage image;


    public GameObject(BufferedImage image, int x, int y, int width, int height){
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        lastX = x;
        lastY = y;
    }


    public boolean contains(int x, int y, int width, int height){
        return !(   x + width  <= this.x     ||
                    y + height <= this.y     ||
                    x >= this.x + this.width ||
                    y >= this.y + this.height    );
    }


/************   Gets   ************************************/
    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getCenterX(){return (int) x+width/2;}
    public int getCenterY(){return (int) y+height/2;}
    public BufferedImage getImage() {return image;}
/************   Sets   ************************************/
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setImage(BufferedImage image) {this.image = image;}
/**********************************************************/


    public void tick(){
        lastX = (int) Math.floor(x);
        lastY = (int) Math.floor(y);
    }

    private void renderObject(Graphics2D g, float interpolation){
        int drawX = (int) (((int) Math.floor(x) - lastX) * interpolation + lastX);
        int drawY = (int) (((int) Math.floor(y) - lastY) * interpolation + lastY);

        g.drawImage(image, drawX, drawY, (drawX + width), (drawY + height), 0, 0, image.getWidth(), image.getHeight(), null);
        //RenderUtils.renderImage(g, image, drawX, drawY, width, height, 0, 0, image.getWidth(), image.getHeight());
    }

    public void render(Graphics2D g, float interpolation){
        renderObject(g, interpolation);
    }

    public void renderScroll(Graphics2D g, float interpolation){
        AffineTransform old = g.getTransform();
        g.translate(Window.getXScroll(), Window.getYScroll());
        renderObject(g, interpolation);
        g.setTransform(old);
    }

    public void renderCenter(Graphics2D g, float interpolation){
        int drawX = (int) (((int) Math.floor(x) - lastX) * interpolation + lastX);
        int drawY = (int) (((int) Math.floor(y) - lastY) * interpolation + lastY);

        Window.center(drawX, drawY);
        renderScroll(g, interpolation);
    }

}
