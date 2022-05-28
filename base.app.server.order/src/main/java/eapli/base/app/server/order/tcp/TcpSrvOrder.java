package eapli.base.app.server.order.tcp;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.application.AddProductShoppingCartController;
import eapli.base.utils.MessageUtils;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TcpSrvOrder {
    static ServerSocket sock;

    //static AppSettings app = Application.settings();
    //static final int serverPortProperties = app.getServerPortKey();

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

    private final AddProductShoppingCartController theController = new AddProductShoppingCartController();

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

                /*============beginning of US1501============*/
                //ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                Iterable<ProductDTO> productCatalog = theController.showProductCatalog();
                sOutputObject.writeObject(productCatalog);
                sOutputObject.flush();

                //asks for product id to client
                MessageUtils.writeMessageWithData((byte) 3, "Product ID: ", sOut);

                //receives product id from client
                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                if(clientMessageUS[1] == 3) {
                    String productId = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
                    System.out.println(productId);
                    //CHAMAR MÉTODO CONTROLLER QUE VAI BUSCAR O PRODUCT
                }

                //asks for quantity of product to client
                MessageUtils.writeMessageWithData((byte) 3, "Quantity: ", sOut);

                //receives product id from client
                clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                if(clientMessageUS[1] == 3) {
                    String quantity = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
                    System.out.println(quantity);
                    //CHAMAR MÉTODO CONTROLLER QUE VAI BUSCAR O PRODUCT
                }



                /*============end of US1501============*/



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

