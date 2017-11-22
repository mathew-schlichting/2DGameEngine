package com.invert.engine.exceptions;

/**
 * Created by Mathew on 5/28/2017.
 */
public class StillRunningException extends RuntimeException {

    public StillRunningException(String message){
        super(message);
    }
}
