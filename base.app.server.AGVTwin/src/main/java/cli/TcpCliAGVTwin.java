package cli;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class TcpCliAGVTwin {

    //client: request
    //server: waiting for request + send response

    public static class ClientSocket {

        static InetAddress serverIP;
        static Socket sock;

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
                sock = new Socket(serverIP, port);
            } catch (IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            System.out.println("Connected to: " + serverIP + ":" + 2807);

            sOutData = new DataOutputStream(sock.getOutputStream());
            sInData = new DataInputStream(sock.getInputStream());
        }

        public DataOutputStream dataOutputStream() {
            return this.sOutData;
        }

        public DataInputStream dataInputStream() {
            return this.sInData;
        }

        public Socket sock() {
            return this.sock;
        }

        public void stop() throws IOException {
            sock.close();
        }

    }

    public void updateAgvsStatus(List<AGV> agvsToUpdate){
        try{
            final var socket = new ClientSocket();
            socket.connect(getAddress(),getPort());
            try{
                if (MessageUtils.testCommunicationWithServer(socket.sOutData, socket.sInData)){
                    MessageUtils.writeMessage((byte) 8, socket.sOutData);

                    byte[] clientMessageUS = new byte[4];
                    MessageUtils.readMessage(clientMessageUS,socket.sInData);
                    if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                        socket.stop();
                    } else {
                        System.out.println("==> ERROR: Erro no pacote do Servidor");

                    }

                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                }
            }catch (IOException e) {
                System.out.println("==> ERROR: Falha durante a troca de informação com o server");
            } finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
        }catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }
    }

    private int getPort() {
        // TODO read from config file
        return 2807;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
    }

    //metodos necessarios

}