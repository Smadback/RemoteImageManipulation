package edu.tuc.bsvs.remoteimagemanipulation.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Smadback on 07.06.2016.
 */
public interface Server extends Remote {

    int getValue() throws RemoteException;

}
