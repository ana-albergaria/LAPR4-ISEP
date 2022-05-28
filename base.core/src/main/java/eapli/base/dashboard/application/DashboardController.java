package eapli.base.dashboard.application;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.dashboard.domain.HTTPAgvRequest;
import eapli.base.dashboard.domain.HTTPServerAGVS;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DashboardController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    static private Socket sock;
    static private InetAddress serverIP;

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();

    public void showDashboard(){

        HTTPServerAGVS server = new HTTPServerAGVS();
        server.start();
    }


    public Iterable<AGVPosition> getPositions(int option){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<AGVPosition> positions = new ArrayList<>();

        try { serverIP = InetAddress.getByName(serverIPProperties); }
        catch(UnknownHostException ex) {
            System.out.println("Invalid SERVER-ADDRESS.");
            System.exit(1);
        }

        System.out.println("Connecting to http://" + serverIP + ":" + serverPortProperties + "/");

        try {
            sock = new Socket(serverIP, serverPortProperties);
        } catch(IOException ex) {
            System.out.println("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
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

                byte[] serverResponse = sIn.readNBytes(4);
                if (serverResponse[1] == 2) {

                    ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());
                    ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());

                    positions = (Iterable<AGVPosition>) sInObject.readObject();

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();

                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        sock.close();
                    }
                } else {
                    throw new IllegalArgumentException("Couldn't retrieve the needed information!");
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
