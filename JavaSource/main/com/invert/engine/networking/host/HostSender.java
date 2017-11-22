package com.invert.engine.networking.host;

import com.invert.engine.networking.connections.Data;
import com.invert.engine.networking.connections.Sender;

import java.net.Socket;

/**
 * Created by Mathew on 7/12/2017.
 */
public class HostSender extends Sender {

    private Host host;

    public HostSender(Host host, Socket socket, int connectionId) {
        super(socket, connectionId);
        this.host = host;
    }

    public Data getData(){
        return host.getData();
    }

    public void afterClose(){host.afterClose(host.getReceiver(getConnectionId()).getReceivedId());}

}
