package edu.tuc.bsvs.remoteimagemanipulation.client;

import edu.tuc.bsvs.remoteimagemanipulation.server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Smadback on 07.06.2016.
 */
public class Client {

    public void start() {

        try {
            Registry registry = LocateRegistry.getRegistry();
            Server server = (Server) registry.lookup("Server");
            System.out.println(server.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
