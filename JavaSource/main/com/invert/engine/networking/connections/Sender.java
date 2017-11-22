package com.invert.engine.networking.connections;

import com.invert.engine.exceptions.UnableToCloseException;
import com.invert.engine.utils.GameLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Mathew on 7/12/2017.
 */
public abstract class Sender implements Runnable{

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running;
    private Thread thread;
    private int connectionId;

    public Sender(Socket socket, int connectionId){
        this.socket = socket;
        this.connectionId = connectionId;

        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }catch (IOException ioe){
            GameLogger.logError("Unable to create object stream\n" + ioe.getMessage());
        }
    }

    public boolean isRunning(){return running;}
    public int getConnectionId(){return connectionId;}


    public void start(){
        running = true;
        thread = new Thread(this, "Sender Thread");
        thread.start();
    }

    public void stop(){
        running = false;

        //close thread
        try{
            thread.join();
        }catch (InterruptedException ie){
            // TODO: 7/12/2017
        }
    }

    public abstract Data getData();
    public abstract void afterClose();

    public void end()throws IOException, ClassNotFoundException{
        GameLogger.logInfo("Preforming ending protocol in Sender");

        //writes true to make end properly
        out.writeObject(true);
        out.flush();

        //if this isn't true then it didn't hit end in receiver
        if(!((boolean) in.readObject())){
            throw new UnableToCloseException("Cannot close sender properly. Unable to preform ending protocol");
        }
    }

    public void run(){
        try{
            while(isRunning()){
                out.writeObject(getData());
                out.flush();
                if((boolean) in.readObject()){
                    running = false;
                }
                Thread.sleep(8);
            }

            end();
        }catch (IOException ioe){
            if(ioe.getMessage() != null && ioe.getMessage().trim().length() > 0)
                GameLogger.logError("IOE in sender Note, this might not be an error\n" + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            GameLogger.logError("Class not found in sender" + cnfe.getMessage());
        } catch (InterruptedException ie){
            GameLogger.logError("Unable to sleep in sender" + ie.getMessage());
        }finally {

            //close out
            try{
                out.close();
            }catch (IOException ioe){
                // TODO: 7/12/2017
            }

            //close in
            try{
                in.close();
            }catch (IOException ioe){
                // TODO: 7/12/2017
            }

            //close socket
            try{
                socket.close();
            }catch (IOException ioe){
                // TODO: 7/12/2017
            }
        }
        afterClose();
    }

}
