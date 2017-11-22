package com.invert.engine.networking;

import com.invert.engine.networking.connections.Data;

/**
 * Created by Mathew on 7/13/2017.
 */
public interface Lobby {
    public Data[] getAllData();
    public Data getData();
    public void stop();
    public void afterClose(int receivedId);
    public void afterAdd();
}
