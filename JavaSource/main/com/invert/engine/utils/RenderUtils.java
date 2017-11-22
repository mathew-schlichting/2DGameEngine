package com.invert.engine.utils;

import com.invert.engine.output.display.Window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by Mathew on 6/27/2017.
 */
public class RenderUtils {

        private static int scrollX(int x) {
            return x + Window.getXScroll();
        }
        private static int scrollY(int y) {
            return y + Window.getYScroll();
        }
        private static int scale(double a) {
            return (int)(a * Window.getPixelSize());
        }


        public static void renderImage(Graphics2D g, BufferedImage image, int dx, int dy, int width, int height, int sx, int sy, int imageWidth, int imageHeight) {
            int drawX1 = scale((double) scrollX(dx));
            int drawX2 = scale((double) scrollX(dx + width));
            int drawY1 = scale((double) scrollY(dy));
            int drawY2 = scale((double) scrollY(dy + height));
            if (drawX1 < Window.getSize().getWidth() && drawY1 < Window.getSize().getHeight() && drawX2 > 0 && drawY2 > 0) {
                g.drawImage(image, drawX1, drawY1, drawX2, drawY2, sx, sy, sx + imageWidth, sy + imageHeight, (ImageObserver) null);
            }
        }

        public static void renderRectangle(Graphics2D g, Color color, int x, int y, int width, int height) {
            int drawX = scrollX(scale((double)x));
            int drawY = scrollY(scale((double)y));
            int drawWidth = scale((double)width) + 1;
            int drawHeight = scale((double)height) + 1;
            g.setColor(color);
            g.fillRect(drawX, drawY, drawWidth, drawHeight);
        }
    }
