package eapli.base.app.server.order.tcp;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.ListProductDTOService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShopCarItemRepository;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.utils.MessageUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;


public class TcpOrderSrv {
    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(10000); }
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


    private final ListProductDTOService service = new ListProductDTOService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private final ShoppingCartRepository shoppingCarRepository = PersistenceContext.repositories().shoppingCarts();
    private Product product;
    private Optional<Client> client;
    private Optional<ShoppingCart> shoppingCar;

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

            byte[] clientMessage = new byte[4];
            MessageUtils.readMessage(clientMessage, sIn);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Código de Teste (0) do Cliente recebido.");

                System.out.println("[INFO] A Mandar Código de Entendido (2) ao Cliente.");
                MessageUtils.writeMessage((byte) 2, sOut);

                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                /*============Enviar produtos ao cliente============*/
                if(clientMessageUS[1] == 4) {
                    ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                    Iterable<ProductDTO> productCatalog = service.allProducts();
                    sOutputObject.writeObject(productCatalog);
                    sOutputObject.flush();
                }

                /*============Verificar se Produto Existe============*/
                if(clientMessageUS[1] == 3) {
                    String productUniqueInternalCode = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
                    Optional<Product> product = productRepository.ofIdentity(Code.valueOf(productUniqueInternalCode));
                    if(!product.isPresent()) {
                        MessageUtils.writeMessageWithData((byte) 3, "[FAILURE] Product not found! Please try again.", sOut);
                    } else {
                        MessageUtils.writeMessageWithData((byte) 3, "[SUCCESS] Product found!", sOut);
                    }
                }

                /*============Adicionar Produto ao Carrinho de Compras============*/
                if(clientMessageUS[1] == 5) {
                    String info = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
                    String[] array = info.split(" ");
                    String quantidade = array[0];
                    String email = array[1];
                    String productUniqueInternalCode = array[2];
                    product = productRepository.ofIdentity(Code.valueOf(productUniqueInternalCode)).get();
                    client = clientRepository.findByEmail(Email.valueOf(email));
                    ShopCarItem item = new ShopCarItem(Integer.parseInt(quantidade),product);
                    if(client.isPresent()) {
                        shoppingCar = shoppingCarRepository.findShoppingCartByClient(client.get());
                        if(shoppingCar.isPresent()) {
                            shoppingCar.get().addProductToShoppingCar(item);
                            shoppingCarRepository.save(shoppingCar.get());
                        } else {
                            ShoppingCart shoppingCar1 = new ShoppingCart(client.get());
                            shoppingCar1.addProductToShoppingCar(item);
                            shoppingCarRepository.save(shoppingCar1);
                        }
                    }

                }

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

