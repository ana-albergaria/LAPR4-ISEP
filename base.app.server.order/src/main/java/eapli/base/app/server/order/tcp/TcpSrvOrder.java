package eapli.base.app.server.order.tcp;

import eapli.base.AppSettings;
import eapli.base.Application;

import java.io.*;
import java.net.*;

public class TcpSrvOrder {
    static ServerSocket sock;

    static AppSettings app = Application.settings();
    static final int serverPortProperties = app.getServerPortKey();

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(9999); }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            new Thread(new TcpSrvOrderThread(cliSock)).start();
        }
    }
}



class TcpSrvOrderThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpSrvOrderThread(Socket cli_s) {
        s = cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try {
            clientIP = this.s.getInetAddress();
            System.out.println("[INFO] Nova conexão de cliente: " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + ".");

            DataInputStream sIn = new DataInputStream(this.s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.s.getOutputStream());

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Código de Teste (0) do Cliente recebido.");

                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                sOut.write(serverMessage);
                sOut.flush();



                byte[] clientMessageEnd = sIn.readNBytes(4);
                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Código de Fim (1) do Cliente recebido.");
                    byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                    sOut.write(serverMessageEnd);
                    sOut.flush();
                    System.out.println("[INFO] Cliente " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + " desconectado.");
                } else {
                    System.out.println("[ERROR] Pacote do Cliente invalido.");
                }

            } else {
                System.out.println("[ERROR] Pacote do Cliente invalido.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Problemas a Fechar o Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Fechado.\n\n");
        }

    }

}

