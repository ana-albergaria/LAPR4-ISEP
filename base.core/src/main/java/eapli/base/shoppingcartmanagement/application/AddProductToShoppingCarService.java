package eapli.base.shoppingcartmanagement.application;

import eapli.base.productmanagement.dto.ProductDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class AddProductToShoppingCarService {

    private static class ClientSocket {
        private Socket sock;
        private InetAddress serverIP;

        public void connect(final String address, final int port) throws IOException {

            try {
                serverIP = InetAddress.getByName(address);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified: " + serverIP);
                System.exit(1);
            }

            try {
                sock = new Socket(serverIP, port); }
            catch(IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            System.out.println("Connected to: " + serverIP + ":" + 9999);
        }

        public Socket sock() {
            return this.sock;
        }

        public void stop() throws IOException {
            sock.close();
        }

    }



    public boolean allProducts(){
        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                DataOutputStream sOutData = new DataOutputStream(socket.sock().getOutputStream());
                DataInputStream sInData = new DataInputStream(socket.sock().getInputStream());

                //Mandar um pedido para o servido -> codigo: 0 (Teste)
                byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOutData.write(clienteMessage);
                sOutData.flush();

                //Esperar a resposta do servidor a dizer que entendeu a mensagem
                byte[] serverMessage = sInData.readNBytes(4);
                if (serverMessage[1] == 2) {

                    byte[] clientMessage = {(byte) 0, (byte) 4, (byte) 0, (byte) 0};
                    sOutData.write(clientMessage);
                    sOutData.flush();

                    // mostrar os produtos existentes
                    ObjectInputStream sInputObject = new ObjectInputStream(socket.sock().getInputStream());
                    Iterable<ProductDTO> productCatalog = (Iterable<ProductDTO>) sInputObject.readObject();

                    System.out.println("######## Produtos Existentes em Catálogo ########");

                    Iterator<ProductDTO> listProducts = productCatalog.iterator();

                    while(listProducts.hasNext()) {
                        ProductDTO produto = listProducts.next();
                        System.out.println(produto);
                    }
                    System.out.println();


                    //Mandar um pedido para o servido -> codigo: 1 (Fim)
                    byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOutData.write(clienteMessageEnd);
                    sOutData.flush();



                    byte[] serverMessageEnd = sInData.readNBytes(4);
                    if (serverMessageEnd[1] == 2) {
                        socket.stop();

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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean findByUniqueInternalCode(String productUniqueInternalCode) {
        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                DataOutputStream sOutData = new DataOutputStream(socket.sock().getOutputStream());
                DataInputStream sInData = new DataInputStream(socket.sock().getInputStream());

                //Mandar um pedido para o servido -> codigo: 0 (Teste)
                byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOutData.write(clienteMessage);
                sOutData.flush();

                //Esperar a resposta do servidor a dizer que entendeu a mensagem
                byte[] serverMessage = sInData.readNBytes(4);
                if (serverMessage[1] == 2) {

                    //enviar produto escolhido e verificar se existe
                    eapli.base.utils.MessageUtils.writeMessageWithData((byte) 3, productUniqueInternalCode, sOutData);
                    byte[] clientMessageUS = new byte[4];
                    eapli.base.utils.MessageUtils.readMessage(clientMessageUS, sInData);

                    if(clientMessageUS[1] == 3) {
                        String productExists = eapli.base.utils.MessageUtils.getDataFromMessage(clientMessageUS,sInData);
                        if(!productExists.equals("[SUCCESS] Product found!")){
                            //Mandar um pedido para o servido -> codigo: 1 (Fim)
                            byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                            sOutData.write(clienteMessageEnd);
                            sOutData.flush();

                            byte[] serverMessageEnd = sInData.readNBytes(4);
                            if (serverMessageEnd[1] == 2) {
                                socket.stop();

                            } else {
                                System.out.println("==> ERROR: Erro no pacote do Servidor");

                            }
                            return false;
                        }
                    }

                    //Mandar um pedido para o servido -> codigo: 1 (Fim)
                    byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOutData.write(clienteMessageEnd);
                    sOutData.flush();

                    byte[] serverMessageEnd = sInData.readNBytes(4);
                    if (serverMessageEnd[1] == 2) {
                        socket.stop();

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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean addProductToShoppingCarService( String uniqueInternalCode, int quantidade, String clientEmail) {

        try {

            final var socket = new ClientSocket();
            socket.connect(getAddress(), getPort());

            try {

                DataOutputStream sOutData = new DataOutputStream(socket.sock().getOutputStream());
                DataInputStream sInData = new DataInputStream(socket.sock().getInputStream());

                //Mandar um pedido para o servido -> codigo: 0 (Teste)
                byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOutData.write(clienteMessage);
                sOutData.flush();

                //Esperar a resposta do servidor a dizer que entendeu a mensagem
                byte[] serverMessage = sInData.readNBytes(4);
                if (serverMessage[1] == 2) {

                    String info = quantidade + " " + clientEmail + " " + uniqueInternalCode;
                    eapli.base.utils.MessageUtils.writeMessageWithData((byte) 5, info, sOutData);

                    //Mandar um pedido para o servido -> codigo: 1 (Fim)
                    byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOutData.write(clienteMessageEnd);
                    sOutData.flush();

                    byte[] serverMessageEnd = sInData.readNBytes(4);
                    if (serverMessageEnd[1] == 2) {
                        socket.stop();
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
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private int getPort() {
        // TODO read from config file
        return 9999;
    }

    private String getAddress() {
        // TODO read from config file
        return "localhost";
    }
}
