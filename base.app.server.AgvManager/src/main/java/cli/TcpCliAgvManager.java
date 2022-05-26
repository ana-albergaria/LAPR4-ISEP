package cli;

import java.io.*;
import java.net.*;

public class TcpCliAgvManager{
    static InetAddress serverIP;
    static Socket sock;

    static String address;

    public TcpCliAgvManager(String address, Integer port){
        if(address.length() != 1){
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        new Thread(new TcpCliAgvManagerThread(address, port)).start();
    }
}

class TcpCliAgvManagerThread implements Runnable {
    private final String ip;
    private final Integer port;

    public TcpCliAgvManagerThread(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
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
            sock = new Socket(this.ip, port); }
        catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.ip);

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

                //Mandar um pedido para o servido -> codigo: 1 (Fim)
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
    }
}