package edu.tuc.bsvs.remoteimagemanipulation.server;

import java.rmi.RemoteException;

/**
 * Created by Smadback on 07.06.2016.
 */
public class ServerImpl implements Server {

    private int value;

    public ServerImpl() throws RemoteException {
        value = 5;
    }

    public int getValue() throws RemoteException {
        return value;
    }
}
