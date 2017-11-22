package com.invert.engine.input.keyboard;

/**
 * Created by Mathew on 5/29/2017.
 */
public abstract class ActionKey extends Key {

    public ActionKey(int keyCode){
        super(keyCode);
    }

    @Override
    public boolean isActionKey(){return true;}


    public abstract void press();
    public abstract void release();
}
