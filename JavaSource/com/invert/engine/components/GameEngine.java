package com.invert.engine.components;

import com.invert.engine.exceptions.StillRunningException;
import com.invert.engine.exceptions.UnableToCloseException;
import com.invert.engine.utils.GameLogger;

/**
 * Created by Mathew on 5/28/2017.
 */
public class GameEngine {

    private GameThread thread;
    private static boolean running;


    public GameEngine(GameComponent gameComponent, double gameHertz, double fps){
        thread = new GameThread(gameComponent, gameHertz, fps);
        GameLogger.logInfo("Game Engine Created");
    }


    public static boolean isRunning(){return running;}

    public void start() {
        if (isRunning()) {
            throw new StillRunningException("Game thread is still running");
        }
        else {
            running = true;
            thread.start();
        }
    }

    public void stop(){
        if(!isRunning()) {
            throw new UnableToCloseException("Game thread isn't running");
        }
        else {
            running = false;
            thread.stop();
        }
    }
}
