package eapli.base.app.user.console.tcp;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.utils.MessageUtils;
import eapli.framework.validations.Preconditions;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Iterator;
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

    public static void main(String args[]) {
        if(args.length!=1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        new Thread(new TcpCliOrderThread(args[0])).start();

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
                Scanner in = new Scanner(System.in);

                ObjectInputStream sInputObject = new ObjectInputStream(sock.getInputStream());
                Iterable<ProductDTO> productCatalog = (Iterable<ProductDTO>) sInputObject.readObject();
                showProductCatalog(productCatalog);

                //receives message from server asking for product id
                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sInData);

                if(clientMessageUS[1] == 3) {
                    //System.out.println("[INFO] Codigo da US1501 (3) enviado pelo Servidor ao Cliente");
                    String questionServer = MessageUtils.getDataFromMessage(clientMessageUS,sInData);
                    System.out.print(questionServer);
                    String productId = in.nextLine();

                    MessageUtils.writeMessageWithData((byte) 3, productId, sOutData);
                }

                //receives confirmation from server that the product exists/does not exist
                clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sInData);

                if(clientMessageUS[1] == 3) {
                    String productExists = MessageUtils.getDataFromMessage(clientMessageUS,sInData);
                    System.out.println(productExists);
                }

                //receives message from server asking for quantity
                clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sInData);

                if(clientMessageUS[1] == 3) {
                    //System.out.println("[INFO] Codigo da US1501 (3) enviado pelo Servidor ao Cliente");
                    String questionServer = MessageUtils.getDataFromMessage(clientMessageUS,sInData);
                    System.out.print(questionServer);
                    String quantity = in.nextLine();

                    MessageUtils.writeMessageWithData((byte) 3, quantity, sOutData);
                }

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

    private void showProductCatalog(Iterable<ProductDTO> productCatalog) {
        System.out.println("######## PRODUCT CATALOG ########");

        Iterator<ProductDTO> listProducts = productCatalog.iterator();

        while(listProducts.hasNext()) {
            ProductDTO product = listProducts.next();
            System.out.println(product);
        }
        System.out.println();
    }
}