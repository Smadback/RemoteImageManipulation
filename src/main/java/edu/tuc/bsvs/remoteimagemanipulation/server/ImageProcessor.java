package edu.tuc.bsvs.remoteimagemanipulation.server;

import edu.tuc.bsvs.remoteimagemanipulation.objects.ImageRepresentation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessor extends Remote {

    ImageRepresentation manipulateImage(ImageRepresentation image) throws RemoteException;

}
