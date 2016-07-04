package edu.tuc.bsvs.remoteimagemanipulation;

import edu.tuc.bsvs.remoteimagemanipulation.client.Client;
import edu.tuc.bsvs.remoteimagemanipulation.server.Server;
import org.apache.commons.cli.*;

class RemoteImageManipulation {

    /**
     * Let the user decide if he wants to start a server or a client
     * @param args
     */
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("s", false, "start as a server");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(cmd != null && cmd.hasOption("s")) {
            new Server().start();
        } else {
            new Client().show();
        }
    }

}
