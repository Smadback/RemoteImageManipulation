package edu.tuc.bsvs.remoteimagemanipulation.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Created by Smadback on 07.06.2016.
 */
public class ServerProc {

    public void start() {
        Scanner scan = new Scanner(System.in);
        int port;
        boolean again;

        do {
            System.out.print("[INPUT] Please specify the port for the server to run on (0 for the default port): ");
            again = false;
            try {
                // PORT AND IP INPUT
                port = Integer.valueOf(scan.nextLine());
                if (port == 0)
                    port = Registry.REGISTRY_PORT;

                System.out.println("[INFO] Starting server...");
                LocateRegistry.createRegistry(port);
                ServerImpl server = new ServerImpl();
                Server stub = (Server) UnicastRemoteObject.exportObject(server, 0);
                RemoteServer.setLog(System.out);

                Registry registry = LocateRegistry.getRegistry();
                System.setProperty("java.rmi.server.hostname", "localhost");
                registry.rebind("Server", stub);

                System.out.println("[INFO] Server started.");
            } catch (RemoteException e) {
                System.out.println("[ERROR] An error occured: " + e.getMessage());
                again = true;
            }
        } while (again);

    }

}
