package com.invert.engine.networking.host;

import com.invert.engine.networking.Lobby;
import com.invert.engine.networking.connections.AllData;
import com.invert.engine.networking.connections.Connection;
import com.invert.engine.networking.connections.Data;
import com.invert.engine.networking.connections.Receiver;
import com.invert.engine.objects.GameObjectI;
import com.invert.engine.utils.CommonUtil;
import com.invert.engine.utils.GameLogger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Mathew on 7/12/2017.
 */
public abstract class Host implements Runnable, Lobby{

    private ArrayList<Connection> connections;
    private ServerSocket serverSocket;
    private boolean listening;
    private Thread thread;
    private int port;
    private ConnectionManager connectionManager;

    public Host(int port){
        try{
            this.port = port;
            serverSocket = new ServerSocket(port);
            connections = new ArrayList<>();
            start();
        }catch (IOException ioe){
            GameLogger.logError("Unable to create server socket\n" + ioe.getMessage());
        }
    }


    private void start(){
        GameLogger.logInfo("Starting Host...");
        listening = true;
        thread = new Thread(this);
        thread.start();

        connectionManager = new ConnectionManager();
    }

    public void stop(){
        listening = false;

        for(int i=0;i<connections.size();i++){
            connections.get(i).stop();
        }

        connectionManager.stop();


        Socket socket = null;
        //hit server to close server thing
        try{
            socket = new Socket(InetAddress.getLocalHost(), port);
        }catch (IOException ioe){
            // TODO: 7/12/2017
        }


        //close newly made socket
        try{
            if(socket != null) {
                socket.close();
            }
        }catch (IOException ioe){
            // TODO: 7/12/2017
        }

        //stop thread
        try{
            thread.join();
        }catch (InterruptedException ie){
            // TODO: 7/12/2017
        }
    }

    public Data[] getAllData(){
        Data[] result;
        result = new Data[connections.size()+1];

        result[0] = getHostData();
        for(int i=1;i<result.length;i++){
            result[i] = connections.get(i-1).getData();
        }

        return result;
    }


    public Receiver getReceiver(int connectionId){
        for(int i=0;i<connections.size();i++){
            if(connections.get(i).getId() == connectionId){
                return connections.get(i).getReceiver();
            }
        }
        return null;
    }


    public abstract Data getHostData();

    public Data getData(){return new AllData(getAllData());}


    public void run(){
        Socket in, out;
        int id;

        while(listening){
            try{
                out = serverSocket.accept();
                if(listening){
                    in = serverSocket.accept();
                    if(listening){
                        GameLogger.logInfo("Adding connection");
                        id = CommonUtil.createId();
                        connections.add(new Connection(new HostSender(this, out, id), new Receiver(in, id), id));
                        GameLogger.logInfo("Connection added successfully. Id: " + id);
                        afterAdd();
                    }
                }
            }catch (IOException ioe){
                // TODO: 7/12/2017
            }
            finally{
                try{
                    serverSocket.close();
                }catch (IOException ioe){
                    GameLogger.logError("Unable to close server socket\n" + ioe.getMessage());
                }
            }
        }
    }


    private class ConnectionManager implements Runnable{

        private Thread thread;

        private ConnectionManager(){
            thread = new Thread(this);
            thread.start();
        }

        private void stop(){
            try{
                thread.join();
            }catch (InterruptedException ie){
                GameLogger.logError("Unable to close connection manager" + ie.getMessage());
            }
        }

        public void run(){
            while(listening){
                for(int i=0;i<connections.size();){
                    if(connections.get(i).isRunning()){
                        i++;
                    }
                    else{
                        connections.remove(i);
                    }
                }
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ie){
                    GameLogger.logError("Unable to sleep in connection manager" + ie.getMessage());
                }
            }
        }

    }
}
