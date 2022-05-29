package eapli.base.dashboard.application;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGVPosition;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GetPositions {
    static private Socket sock;
    static private InetAddress serverIP;

    public Iterable<AGVPosition> getPositions(int option){

        List<AGVPosition> positions = new ArrayList<>();

        try {
            serverIP = InetAddress.getByName(Application.settings().getServerIpKey());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("Connecting to "+ serverIP + ":" + 2807 );

        try {
            sock = new Socket(serverIP, 2807);
        } catch(IOException ex) {
            System.out.println("Failed to connect to connect to AGVManager server.");
            System.out.println("Application aborted.");
            System.exit(1);
        }

        try {
            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sIn = new DataInputStream(sock.getInputStream());

            byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOut.write(testMessage);
            sOut.flush();

            byte[] testResponse = sIn.readNBytes(4);
            if(testResponse[1]==2){
                byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                sOut.write(optionMessage);
                sOut.flush();

                ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());

                positions = (List<AGVPosition>) sInObject.readObject();

                byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                sOut.write(endMessage);
                sOut.flush();
                byte[] endResponse = sIn.readNBytes(4);
                if (endResponse[1] == 2) {
                    sock.close();
                }
            } else {
                throw new IllegalArgumentException("Test message wasn't successful.");
            }
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("Error accessing socket's streams. Aborted.");
            try { sock.close(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
            System.out.println("Application aborted.");
            System.exit(1);
        }



        try { sock.close(); } catch(IOException ex2) { System.out.println("Error closing socket."); }

        return positions;
    }
}
