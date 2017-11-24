package com.invert.engine.output.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Music implements Sound{

    private boolean repeat;
    private MusicLoopListener listener;
    private Clip clip;
    private int frame;

    public Music(Clip clip, boolean repeat, int frame){
        this.repeat     = repeat;
        this.clip = clip;
        this.frame = frame;

        this.listener = new MusicLoopListener();
        clip.addLineListener(listener);
    }

    public void play(){
        clip.setFramePosition(frame);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void close(){
        clip.close();
    }

    private class MusicLoopListener implements LineListener {
        @Override
        public void update(LineEvent event) {
            if(repeat) {
                play();
            }
        }
    }
}
