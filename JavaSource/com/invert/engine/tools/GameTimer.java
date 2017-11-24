package com.invert.engine.tools;

import java.awt.*;

/**
 * Created by Mathew on 7/16/2017.
 */
public class GameTimer {

    private long startTime, length;

    public GameTimer(int seconds){
        this.length = seconds*1000;
    }
    public GameTimer(int minutes, int seconds){
        this(60*minutes + seconds);
    }


    public int getLength(){
        return (int) (length/1000);
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public boolean isTimeUp(){return startTime+length < System.currentTimeMillis();}


    public String toString(){
        int minutes, seconds, millis;
        String min, sec, mil;
        long delta;

        delta = (startTime+length) - System.currentTimeMillis();

        millis = (int) (delta%1000);
        seconds = (int) ((delta/1000)%60);
        minutes = (int) ((delta/1000)/60);

        if(millis < 0)
            millis = 0;
        if(seconds < 0)
            seconds = 0;
        if(minutes < 0)
            minutes = 0;


        min = "" + minutes;
        sec = "00" + seconds;
        mil = "000" + millis;

        return min+":"+sec.substring(sec.length()-2)+"."+mil.substring(mil.length()-3);
    }
}
