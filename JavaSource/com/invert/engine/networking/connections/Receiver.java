package com.invert.engine.networking.connections;

import com.invert.engine.exceptions.UnableToCloseException;
import com.invert.engine.utils.GameLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receiver implements Runnable {

    private boolean running;
    private Socket socket;
    private Thread thread;
    private Data data;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int connectionId;
    private int receivedId = -777;

    public Receiver(Socket socket, int connectionId){
        this.socket = socket;
        this.connectionId = connectionId;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            data = new Data(-1);
        }catch (IOException ioe){
            GameLogger.logError("Unable to create Object Stream\n" + ioe.getMessage());
        }
    }

    public boolean isRunning(){return running;}
    public int getConnectionId(){return connectionId;}
    public int getReceivedId(){return receivedId;}


    public Data getData(){return data;}

    public void start(){
        running = true;
        thread = new Thread(this, "Receiver Thread");
        thread.start();
    }

    public void stop(){
        running = false;

        //close thread
        try{
            thread.join();
        }catch (InterruptedException ie){
            GameLogger.logError("Unable to close receiver thread\n" + ie.getMessage());
        }
    }

    private void end()throws ClassNotFoundException, IOException{
        GameLogger.logInfo("Preforming ending protocol in Receiver");

        if(!((boolean) in.readObject())){
            throw new UnableToCloseException("Cannot close receiver properly. Unable to preform ending protocol");
        }

        out.writeObject(true);
        out.flush();
    }


    public void run(){
        try {
            while (isRunning()) {
                data = (Data) in.readObject();
                receivedId = data.getId();
                out.writeObject(!isRunning());
                out.flush();
            }

            end();
        }catch (IOException ioe){
            if(ioe.getMessage() != null) {
                GameLogger.logWarning("IOE in receiver. Note, this might not be a critical error" + ioe.getMessage());
            }
        } catch (ClassNotFoundException cnfe) {
            GameLogger.logError("Class not found in receiver" + cnfe.getMessage());
        }finally {
            //close in
            try{
                in.close();
            }catch (IOException ioe){
                GameLogger.logError("Unable to close input stream in receiver" + ioe.getMessage());
            }

            //close out
            try{
                out.close();
            }catch (IOException ioe){
                GameLogger.logError("Unable to close output stream in receiver" + ioe.getMessage());
            }

            //close socket
            try{
                socket.close();
            }catch (IOException ioe){
                GameLogger.logError("Unable to close socket in receiver" + ioe.getMessage());
            }
        }
    }
}
