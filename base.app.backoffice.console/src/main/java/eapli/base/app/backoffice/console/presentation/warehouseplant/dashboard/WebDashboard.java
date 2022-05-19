package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class WebDashboard {
    static private Socket sock;
    static private InetAddress serverIP;
    static private int serverPort;
    static private DataOutputStream sOut;
    static private DataInputStream sIn;

    public static void main(String[] args) throws Exception {
        if(args.length!=2) {
            System.out.println("Server address and port number required at command line.");
            System.out.println("Usage: java WebDashboard {SERVER-ADDRESS} {SERVER-PORT-NUMBER}");
            System.exit(1);
        }

        try { serverIP = InetAddress.getByName(args[0]); }
        catch(UnknownHostException ex) {
            System.out.println("Invalid SERVER-ADDRESS.");
            System.exit(1);
        }

        try { serverPort = Integer.parseInt(args[1]); }
        catch(NumberFormatException ex) {
            System.out.println("Invalid SERVER-PORT.");
            System.exit(1);
        }

        ObjectInputStream sIn = new ObjectInputStream(sock.getInputStream());

        Object positionList = sIn.readObject();

        HTTPMessage request = new HTTPMessage();
        request.setRequestMethod("GET");
        request.setURI("agvs");

        System.out.println("Connecting to http://" + args[0] + ":" + serverPort + "/");

        


        try { sock.close(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
    }
}
