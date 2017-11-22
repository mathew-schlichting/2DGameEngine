package com.invert.engine.networking.client;

import com.invert.engine.networking.connections.Data;
import com.invert.engine.networking.connections.Sender;

import java.net.Socket;

/**
 * Created by Mathew on 7/12/2017.
 */
public class ClientSender extends Sender{

    private Client client;

    public ClientSender(Client client, Socket socket, int connectionId) {
        super(socket, connectionId);
        this.client = client;
    }

    public Data getData(){
        return client.getData();
    }

    public void afterClose(){
        client.afterClose(getConnectionId());
    }



}
