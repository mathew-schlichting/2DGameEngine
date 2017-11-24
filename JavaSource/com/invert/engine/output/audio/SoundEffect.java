package com.invert.engine.output.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundEffect implements Sound{

    private Clip clip;
    private boolean repeat;
    private SFXLoopListener listener;
    private int frame;




    public SoundEffect(Clip clip, boolean repeat, int frame){
        this.repeat     = repeat;
        this.clip = clip;
        this.frame = frame;

        this.listener = new SFXLoopListener();
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

    private class SFXLoopListener implements LineListener {
        @Override
        public void update(LineEvent event) {
            if(repeat) {
                play();
            }
        }
    }
}
