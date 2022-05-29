package eapli.base.dashboard.application;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;

import java.awt.font.FontRenderContext;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GetPositions {

    private static class ClientSocket {
        private Socket sock;
        private InetAddress serverIP;
        private DataOutputStream sOutData;
        private DataInputStream sInData;

        public void connect(final String address, final int port) throws IOException {

            try {
                serverIP = InetAddress.getByName(address);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified: " + serverIP);
                System.exit(1);
            }

            try {
                sock = new Socket(serverIP, port); }
            catch(IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            System.out.println("Connected to: " + serverIP + ":" + port);

            sOutData = new DataOutputStream(sock.getOutputStream());
            sInData = new DataInputStream(sock.getInputStream());
        }

        public void stop() throws IOException {
            sock.close();
        }

    }



    public Iterable<AGVPosition> getPositions(int option){
        List<AGVPosition> positions = new ArrayList<>();

        try {
            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {
                DataOutputStream sOut = new DataOutputStream(socket.sock.getOutputStream());
                DataInputStream sIn = new DataInputStream(socket.sock.getInputStream());

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.sock.getInputStream());

                    positions = (List<AGVPosition>) sInObject.readObject();

                    for(AGVPosition pos: positions){
                        System.out.println(pos.toString());

                    }

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            } finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        Square posSquare = new Square(1L, 3L);
        AgvDock dockTest = new AgvDock("D1", new Square(1L, 2L), new Square(2L, 3L), new Square(3L, 4L), new Accessibility("-l"));
        AGV agvTest = new AGV(10L, new AutonomyStatus("1D"), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED), "refaerfa",
                "short description 1", 18.0, dockTest);
        AGVPosition pos = new AGVPosition(posSquare, agvTest);
        positions.add(pos);

        return positions;
    }

    public Iterable<AGV> getAgvs(int option){

        List<AGV> agvs = new ArrayList<>();

        try {
            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());
            try {
                DataOutputStream sOut = new DataOutputStream(socket.sock.getOutputStream());
                DataInputStream sIn = new DataInputStream(socket.sock.getInputStream());

                byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(testMessage);
                sOut.flush();

                byte[] testResponse = sIn.readNBytes(4);
                if(testResponse[1]==2){
                    byte[] optionMessage = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                    sOut.write(optionMessage);
                    sOut.flush();

                    ObjectInputStream sInObject = new ObjectInputStream(socket.sock.getInputStream());

                    agvs = (List<AGV>) sInObject.readObject();

                    byte[] endMessage = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOut.write(endMessage);
                    sOut.flush();
                    byte[] endResponse = sIn.readNBytes(4);
                    if (endResponse[1] == 2) {
                        socket.stop();
                    }
                } else {
                    throw new IllegalArgumentException("Test message wasn't successful.");
                }
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println("Error accessing socket's streams. Aborted.");
                try { socket.stop(); } catch(IOException ex2) { System.out.println("Error closing socket."); }
                System.out.println("Application aborted.");
                System.exit(1);
            }finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

        } catch (IOException e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }

        return agvs;
    }

    private int getPort() {
        // TODO read from config file
        return 3700;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
        //return "192.168.1.5"; -> ipv4 do terminal local windows
    }
}
