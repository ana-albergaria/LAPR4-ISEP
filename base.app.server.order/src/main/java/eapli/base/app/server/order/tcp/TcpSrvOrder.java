package eapli.base.app.server.order.tcp;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.repositories.ProductRepository;
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

    private static final ProductRepository productRepository = PersistenceContext.repositories().products();

    public TcpSrvOrderThread(Socket cli_s) {
        s = cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try {
            //new ViewProductCatalogUI().show();

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
                sOutputObject.writeObject(productRepository.findAll());
                sOutputObject.flush();




                int nChars;
                byte[] data = new byte[300];
                String useFilters = "";
                String moreFilters = "";
                List<String> catalog = new ArrayList<>();
                boolean isValidYesOrNo1 = false;
                boolean isValidYesOrNo2 = false;
                boolean isValidNumber = false;
                Integer filterOption;
                int numSelectedFilters = 0;

                Map<Integer, String> filters = new HashMap<>();
                Map<Integer, String> selectedFilters = new HashMap<>();
                filters.put(1, "Category");
                filters.put(2, "Brand");
                filters.put(3, "Description");
                filters.put(4, "All above");

                System.out.println("######## PRODUCT CATALOG ########");
                int i=0, j, m;
                System.out.println("Do you want to use filters? \n Yes - Y | No - N");

                /*byte[] clientMessageUS = sIn.readNBytes(4);

                if (clientMessageUS[1] == 3) {
                    System.out.println("Código Mensagem: " + clientMessageUS[1]);
                }

                byte[] clientMessageUS = sIn.readNBytes(4);
                System.out.println("Código mensagem: " + clientMessageUS[1]);*/


                //AQUI
                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                System.out.println("Código mensagem: " + clientMessageUS[1]);

                if (clientMessageUS[1] == 3) {
                    System.out.println("Recebeu mensagem código 3");
                    useFilters = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
                    System.out.println("Resposta do Cliente: " + useFilters);

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

