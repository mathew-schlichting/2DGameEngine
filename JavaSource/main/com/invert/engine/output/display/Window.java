package com.invert.engine.output.display;

import com.invert.engine.components.GameComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Window {

    private static JFrame frame;
    private static int xScroll, yScroll;
    private static GameComponent gameComponent;
    private static double pixelSize;

    public Window(Dimension size, BufferedImage icon, BufferedImage cursor, GameComponent gameCanvas, boolean fullScreen, double pixelSize){
        if(size == null)
            throw new IllegalArgumentException("Size is null\nLocation: Window()");
        if(gameCanvas == null)
            throw new IllegalArgumentException("Component is null\nLocation: Window()");

        Window.gameComponent = gameCanvas;
        Window.pixelSize = pixelSize;


        frame = new JFrame("");
        frame.setIconImage(icon);

        //handle fullscreen
        if (fullScreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        if(cursor != null) {
            Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "Custom cursor");
            frame.setCursor(customCursor);
        }

        //set size
        frame.setMinimumSize(size);
        frame.setPreferredSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null);


        //set close operation
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                gameComponent.stop();
            }
        });




        frame.setVisible(true);
        frame.add(gameCanvas);
    }



    public static void center(int x, int y) {
        setXScroll(Window.getSize().width / 2 - x);
        setYScroll(Window.getSize().height / 2 - y);
    }
    public static Dimension getSize(){
        return frame.getSize();
    }
    public static double getPixelSize(){return pixelSize;}
    public static void setXScroll(int x){xScroll = x;}
    public static void setYScroll(int y){yScroll = y;}
    public static int getXScroll(){return xScroll;}
    public static int getYScroll(){return yScroll;}
    public static void setTitle(String title){frame.setTitle(title);}
    public static void close(){
        frame.dispose();
    }
}
