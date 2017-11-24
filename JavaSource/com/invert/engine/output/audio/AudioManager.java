package com.invert.engine.output.audio;

import com.invert.engine.utils.GameLogger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class AudioManager {

    private static AudioManager instance;
    private Sound[] music;
    private Sound[] sfx;

    private AudioManager(Music[] music, SoundEffect[] sfx){
        this.music = music;
        this.sfx = sfx;
    }


    public static void init(Music[] music, SoundEffect[] sfx){
        if(instance == null){
            instance = new AudioManager(music, sfx);
        }
    }

    public static void play(Sound sound){ sound.play(); }
    public static void playMusic(int i){ play(instance.music[i]); }
    public static void playSFX(int i){ play(instance.sfx[i]); }

    public static void close(Sound sound){ sound.close(); }
    public static void closeMusic(int i){ close(instance.music[i]); }
    public static void closeSFX(int i){ close(instance.sfx[i]); }

    public static void stop(Sound sound){ sound.stop(); }
    public static void stopMusic(int i){ stop(instance.music[i]); }
    public static void stopSFX(int i){ stop(instance.sfx[i]); }


    public static void closeAll(){
        for(int i=0;i<instance.music.length;i++){
            stopMusic(i);
            closeMusic(i);
        }

        for(int i=0;i<instance.sfx.length;i++){
            stopSFX(i);
            closeSFX(i);
        }
    }

    public static Clip loadClip(URL url){
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
        }catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            GameLogger.logError("Unable to load clip: " + url + "\n" + e.getMessage());
        }

        return clip;
    }



}
