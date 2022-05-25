package eapli.base.app.user.console.tcp;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.validations.Preconditions;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpCliOrder {
    static InetAddress serverIP;
    static Socket sock;

    static String address;

    public TcpCliOrder(String address) {
        Preconditions.nonEmpty(address, "Server IPv4/IPv6 address or DNS name is required");
        this.address = address;
        new Thread(new TcpCliOrderThread(address)).start();
    }

}

class TcpCliOrderThread implements Runnable {


    private final String ip;


    public TcpCliOrderThread(String ip) {
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
            sock = new Socket(this.ip, 9999); }
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

                /*============beginning of US1501============*/
                ObjectInputStream sInputObject = new ObjectInputStream(sock.getInputStream());
                Iterable<Product> voume = (Iterable<Product>) sInputObject.readObject();
                System.out.println(voume);

                String useFilters;
                byte[] data = new byte[300];
                Scanner in = new Scanner(System.in);
                System.out.print("Answer: ");
                useFilters = in.nextLine();
                //useFilters = "yes";
                data = useFilters.getBytes();

                byte[] clienteMessageUS = {(byte) 0, (byte) 3, (byte) useFilters.length(), (byte) 0};
                sOutData.write(clienteMessageUS, 0, 4);
                sOutData.write(data,0,useFilters.length());
                sOutData.flush();

                /*============end of US1501============*/



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
        } catch (IOException | ClassNotFoundException e) {
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