package com.invert.engine.components;

import com.invert.engine.output.display.Window;
import com.invert.engine.exceptions.UnableToCloseException;
import com.invert.engine.utils.GameLogger;

public class GameThread implements Runnable{

    private Thread          thread;
    private GameComponent   gameComponent;
    private final double    timeBetweenTicks,
                            timeBetweenRenders,
                            fps,
                            gameHertz;


    public GameThread(GameComponent gameComponent, double gameHertz, double fps){
        this.gameComponent = gameComponent;
        this.fps = fps;
        this.gameHertz = gameHertz;
        this.timeBetweenTicks = 1000000000 / gameHertz;
        this.timeBetweenRenders = 1000000000 / fps;
    }


    public synchronized void start(){
        this.thread = new Thread(this);
        this.thread.start();
    }


    public synchronized void stop(){
        try{
            this.thread.join();
        }catch(InterruptedException e){
            throw new UnableToCloseException("Unable to end game thread. Please contact an admin.\n" + e.getMessage());
        }
    }

    @Override
    public void run(){
        gameLoop();
    }


    private void gameLoop() {
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        double lastTickTime = System.nanoTime();
        double lastRenderTime;
        int lastSecondTime = 0;
        int frameCount = 0;

        while (GameEngine.isRunning()) {
            double now = System.nanoTime();
            int updateCount = 0;

            while( now - lastTickTime > timeBetweenTicks && updateCount < MAX_UPDATES_BEFORE_RENDER ) {
                gameComponent.tick();
                lastTickTime += timeBetweenTicks;
                updateCount++;
            }

            if ( now - lastTickTime > timeBetweenTicks) {
                lastTickTime = now - timeBetweenTicks;
            }

            float interpolation = Math.min(1.0f, (float) ((now - lastTickTime) / timeBetweenTicks) );
            gameComponent.render(interpolation);
            lastRenderTime = now;


            frameCount++;
            int thisSecond = (int) (lastTickTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                Window.setTitle("FPS: " + frameCount);
                frameCount = 0;
                lastSecondTime = thisSecond;
            }



            while( now - lastRenderTime < timeBetweenRenders && now - lastTickTime < timeBetweenTicks) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                }
                catch(InterruptedException ie) {
                    GameLogger.logError("Unable to sleep in Game Thread\n" + ie.getMessage());
                }
                now = System.nanoTime();
            }
        }
    }
}
