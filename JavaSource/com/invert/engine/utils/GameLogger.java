package com.invert.engine.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLogger {

    private static Logger logger = Logger.getLogger("Game Logger");




    public static void logInfo(String message){
        log(Level.INFO, message);
    }

    public static void logWarning(String message){
        log(Level.WARNING, message);
    }

    public static void logError(String message){
        log(Level.SEVERE, message);
    }

    public static void log(Level level, String message){
        logger.log(level, message);
    }
}
