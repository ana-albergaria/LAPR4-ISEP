package eapli.base.app.server.order.tcp;

import eapli.base.app.server.order.requests.OrderServerMessageParser;
import eapli.base.app.server.order.requests.OrderServerRequest;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.ListProductDTOService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.application.OrderSrvAddProductToShoppingCarController;
import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShopCarItemRepository;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.utils.MessageUtils;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;


public class TcpOrderSrv {
    static final int SERVER_PORT=10000;
    static final String TRUSTED_STORE= System.getProperty("user.dir") + "/certificates/serverOrder_J.jks";
    static final String KEYSTORE_PASS="forgotten";


    public static void main(String args[]) throws Exception {
        SSLServerSocket sock=null;
        Socket cliSock;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
        }
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

    private final OrderSrvAddProductToShoppingCarController ctrl = new OrderSrvAddProductToShoppingCarController();
    private final OrderServerMessageParser parser = new OrderServerMessageParser(ctrl);

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
            ObjectOutputStream sOutputObject = null; //initializing with null, because not all requests require this

            byte[] clientMessage = new byte[4];
            MessageUtils.readMessage(clientMessage, sIn);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Código de Teste (0) do Cliente recebido.");

                System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                MessageUtils.writeMessage((byte) 2, sOut);

                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                //for the requests that require ObjectOutputStream
                //IMPORTANT: it will be needed for Sprint D: show list of questionnaires and open orders
                if(clientMessageUS[1] == 4) {
                    sOutputObject = new ObjectOutputStream(this.s.getOutputStream());
                }


                //executing the appropriate request according to the message code
                final OrderServerRequest request = parser.parse(clientMessageUS[1], sOutputObject, sIn, sOut, clientMessageUS);
                request.execute();




                byte[] clientMessageEnd = new byte[4];
                MessageUtils.readMessage(clientMessageEnd, sIn);

                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Código de Fim (1) do Cliente recebido.");
                    System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                    MessageUtils.writeMessage((byte) 2, sOut);
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

