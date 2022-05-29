import eapli.base.productmanagement.dto.ProductDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class TcpSrvAGVTwin {

    //client: request
    //server: waiting for request + send response

    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try{
            sock = new ServerSocket(2400);
            System.out.println("Server connection opened.");
        }
        catch(IOException ex){
            System.out.println("Failed to open server socket.");
            System.exit(1);
        }

        while(true){
            cliSock=sock.accept();
            new Thread(new TcpSrvAGVTwinThread(cliSock)).start();
        }
    }
}



class TcpSrvAGVTwinThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    //developing the input communication module of the AGV digital twin
    //to accept requests from the "AGVManager"

    public TcpSrvAGVTwinThread(Socket cli_s){
        s=cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try{
            clientIP=this.s.getInetAddress();
            System.out.println("[INFO] Nova conexão de cliente: " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + ".");
            sOut = new DataOutputStream(this.s.getOutputStream());
            sIn = new DataInputStream(this.s.getInputStream());

            /*System.out.println("I am client " + clientIP.getHostAddress() + " and I am finally connected to the server " + s.getLocalAddress() + "! :)");

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();*/

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Código de Teste (0) do Cliente recebido.");

                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                sOut.write(serverMessage);
                sOut.flush();

                byte[] clientMessageUS = new byte[4];
                eapli.base.utils.MessageUtils.readMessage(clientMessageUS, sIn);

                //enviar a localização dos agvs
                if(clientMessageUS[1] == 6) {
                    ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                    //... locations = ....;

                    //sOutputObject.writeObject(locations);
                    sOutputObject.flush();
                }

                //receber o comando para is buscar produtos

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
