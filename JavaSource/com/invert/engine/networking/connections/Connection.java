package com.invert.engine.networking.connections;

/**
 * Created by Mathew on 7/12/2017.
 */
public class Connection {

    private int id;
    private Sender sender;
    private Receiver receiver;

    public Connection(Sender sender, Receiver receiver, int id){
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
        start();
    }


    public Receiver getReceiver(){return receiver;}
    public Sender getSender(){return sender;}

    public int getId(){return id;}

    public void start(){
        sender.start();
        receiver.start();
    }

    public void stop(){
        sender.stop();
        receiver.stop();
    }

    public Data getData(){return receiver.getData();}


    public boolean isRunning(){return sender.isRunning() && receiver.isRunning();}


}
