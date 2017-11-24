package com.invert.engine.networking.client;

import com.invert.engine.networking.Lobby;
import com.invert.engine.networking.connections.*;

import java.net.Socket;

import static com.invert.engine.Constants.ALL_DATA;

/**
 * Created by Mathew on 7/12/2017.
 */
public abstract class Client implements Lobby{

    private Connection connection;

    public Client(Socket in, Socket out, int id){
        Receiver receiver = new Receiver(in, id);
        Sender sender = new ClientSender(this, out, id);


        connection = new Connection(sender, receiver, id);

        afterAdd();
    }

    public boolean isRunning(){return connection.isRunning();}

    public void stop(){
        connection.stop();
    }


    public Data[] getAllData(){
        Data data = connection.getData();
        if(data.getType() == ALL_DATA){
            return ((AllData) connection.getData()).getData();
        }
        return new Data[0];
    }


}
