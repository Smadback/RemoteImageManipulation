package edu.tuc.bsvs.remoteimagemanipulation;

import edu.tuc.bsvs.remoteimagemanipulation.client.Client;
import edu.tuc.bsvs.remoteimagemanipulation.server.ServerProc;

import java.util.Scanner;

class RemoteImageManipulation {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean again;
        int intInput = -1;

        do {
            again = false;
            System.out.print("[1] SERVER or [2] CLIENT: ");
            String input = scan.nextLine();

            try {
                intInput = Integer.valueOf(input);
            } catch (Exception e) {
                System.out.println("Wrong input!");
                again = true;
            }
        } while(again);

        switch(intInput) {
            case 1:
                new ServerProc().start();
                break;
            case 2:
                new Client().start();
                break;
            case -1:
                System.out.println("An error occured.");
                break;
            default:
                break;
        }
    }

}
