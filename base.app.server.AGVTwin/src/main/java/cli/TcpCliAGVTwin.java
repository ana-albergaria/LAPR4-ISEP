package cli;

import eapli.base.utils.MessageUtils;
import eapli.framework.validations.Preconditions;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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

    public boolean updateAgvsStatus(){
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
            return true;
        }catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
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

    /*static String address;

    public TcpCliAGVTwin(String address){
        Preconditions.nonEmpty(address, "Server IPv4/IPv6 address or DNS name is required");
        this.address=address;
        new Thread(new TcpCliAGVTwinThread(address)).start();
    }

    public static void main(String args[]) {
        if(args.length!=1){
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }
        new Thread(new TcpCliAGVTwinThread(args[0])).start();

    }


}

class TcpCliAGVTwinThread implements Runnable {
    private final String ip;

    public TcpCliAGVTwinThread(String ip) {
        this.ip = ip;
    }

    public void run() {

        InetAddress serverIP = null;
        Socket sock = null;

        try {
            serverIP = InetAddress.getByName(this.ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.ip);
            System.exit(1);
        }

        try {
            sock = new Socket(this.ip, 2807); }
        catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.ip + ":" + 2020);

        try {

            DataOutputStream sOutData = new DataOutputStream(sock.getOutputStream());
            DataInputStream sInData = new DataInputStream(sock.getInputStream());

            //Mandar um pedido para o servido -> codigo: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(clienteMessage);
            sOutData.flush();


            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = sInData.readNBytes(4);
            if (serverMessage[1] == 2) {

                //US5002
                //FAZER SEMPRE, PORQUE OS STATUS DOS AGVS TÊM DE ESTAR ATUALIZADOS
                //pedir ao agvmanager as tasks em que os agvs estão free

                //mudar os agvs dessas tasks para occupied


                //Mandar um pedido para o servidor -> codigo: 1 (Fim)
                byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                sOutData.write(clienteMessageEnd);
                sOutData.flush();

                byte[] serverMessageEnd = sInData.readNBytes(4);
                if (serverMessageEnd[1] == 2) {
                    sock.close();

                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");

                }
            } else {
                System.out.println("==> ERROR: Erro no pacote do Servidor");
            }
        } catch (IOException e) {
            System.out.println("==> ERROR: Falha durante a troca de informação com o server");
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                System.out.println("==> ERROR: Falha a fechar o socket");
            }
        }
    }*/

    //metodos necessarios

}