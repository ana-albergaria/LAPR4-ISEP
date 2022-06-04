package srv;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;
import protocol.KnockKnockProtocol;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;

class TcpSrvAgvManager {
    static ServerSocket sock;

    private String ipAddress;
    private Integer port;
    private InetAddress address;
    private Socket socket;

    public TcpSrvAgvManager(String ipAddress, int port) throws IOException {
        this.ipAddress = ipAddress;
        this.port = port;
        this.address = InetAddress.getByName(this.ipAddress);
        this.socket = new Socket(this.address, this.port);
        new Thread(new TcpSrvAGVTwinThread(socket)).start();
    }

    public static void main(String args[]) throws Exception {

        //==============================
        //====== INICIO DA US4002 ======
        //==============================
        OrderRepository orderRepository = PersistenceContext.repositories().orders();
        TaskRepository taskRepository = PersistenceContext.repositories().tasks();
        AGVRepository agvRepository = PersistenceContext.repositories().agvs();
        TheOrder selectedOrder;
        AGV selectedAGV;
        Iterable<TheOrder> ordersToAssign = orderRepository.findByOrderStatus(OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED));
        List<TheOrder> ordersToAssignList = new ArrayList<>();
        ordersToAssign.forEach(ordersToAssignList::add);
        ordersToAssignList.sort(Comparator.comparing(TheOrder::getCreatedOn));
        Iterable<AGV> agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
        List<AGV> agvsAvailableList = new ArrayList<>();
        agvsAvailable.forEach(agvsAvailableList::add);
        if (ordersToAssignList.isEmpty()){
            System.out.println("[ASSIGN AGVS TO ORDERS]: There are no orders waiting to be assigned.");
        } else if (agvsAvailableList.isEmpty()){
            System.out.println("[ASSIGN AGVS TO ORDERS]: There are no available AGVs.");
        } else {
            int number = 0;
            int ordersToAssignSize = ordersToAssignList.size();
            int agvsAvailableSize = agvsAvailableList.size();
            do {
                selectedOrder = ordersToAssignList.get(number);
                selectedAGV = agvsAvailableList.get(number);
                taskRepository.save(new TheTask(selectedAGV,selectedOrder));
                selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                orderRepository.save(selectedOrder);
                System.out.printf("[ASSIGN AGVS TO ORDERS]: AGV (ID: %d) successfully assigned to the Order (ID: %d). The Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
                number++;
            } while (number + 1 <= ordersToAssignSize && number + 1 <= agvsAvailableSize);
        }
        //=============================
        //======= FIM DA US4002 =======
        //=============================

        Socket cliSock;

        try{
            sock = new ServerSocket(3700);

            System.out.println("Server connection opened!");
        }
        catch(IOException ex){
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true){
            cliSock=sock.accept();
            new Thread(new TcpSrvAgvManagerThread(cliSock)).start();
        }
    }
}


class TcpSrvAgvManagerThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    private final String AGV_DIGITAL_TWIN_SERVER_ADDRESS = "10.9.22.167";
    private final Integer AGV_DIGITAL_TWIN_SERVER_PORT = 2400;

    public TcpSrvAgvManagerThread(Socket cli_s){
        s=cli_s;
    }

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvPositionRepository agvPositionRepository = PersistenceContext.repositories().positions();
    private final PlantRepository warehousePlantRepository = PersistenceContext.repositories().plants();
    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisles();

    public void run() {
        InetAddress clientIP;
        List<AGV> updatedAGVList = new LinkedList<>();

        clientIP=s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());

        try{

            /*
            // Get the keystore
            System.setProperty("javax.net.debug", "all");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String password = "abcdefg";
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("server/certificate-server.p12");
            keyStore.load(inputStream, password.toCharArray());

            // TrustManagerFactory
            String password2 = "aabbcc";
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            InputStream inputStream1 = new FileInputStream("base.app.server.AGVTwin/src/main/resources/cli/client-certificate.p12");
            trustStore.load(inputStream1, password2.toCharArray());
            trustManagerFactory.init(trustStore);
            X509TrustManager x509TrustManager = null;
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    break;
                }
            }

            if (x509TrustManager == null) throw new NullPointerException();


            // KeyManagerFactory ()
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            keyManagerFactory.init(keyStore, password.toCharArray());
            X509KeyManager x509KeyManager = null;
            for (KeyManager keyManager : keyManagerFactory.getKeyManagers()) {
                if (keyManager instanceof X509KeyManager) {
                    x509KeyManager = (X509KeyManager) keyManager;
                    break;
                }
            }
            if (x509KeyManager == null) throw new NullPointerException();

            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(3700);
            serverSocket.setNeedClientAuth(true);
            serverSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            SSLSocket socket = (SSLSocket) serverSocket.accept();

            // InputStream and OutputStream Stuff
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;

            // Initiate conversation with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
            */

            clientIP=s.getInetAddress();
            System.out.println("[INFO] New client connection from " + clientIP.getHostAddress() +
                    ", port number " + this.s.getPort());

            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Client's Test Code (0) has been received.");

                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] Sending the Acknowledge Code (2) to the Client.");
                sOut.write(serverMessage);
                sOut.flush();

                byte[] clientMessageUS = new byte[4];
                MessageUtils.readMessage(clientMessageUS, sIn);

                if (clientMessageUS[1] == 6) { //Por exemplo, codigo 6 = Ligar ao AGV Manager e pedir posições do AGV
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                    Iterable<AGVPosition> agvPositionIterable = agvPositionRepository.findAll();
                    List<AGVPosition> list = (List<AGVPosition>) agvPositionIterable;
                    objectOutputStream.writeObject(list);
                    objectOutputStream.flush();
                }

                if(clientMessageUS[1] == 7){ //usado na US5001 e US5002
                    ObjectOutputStream sendAGVsToChangeList = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream getAGVsChangedList = new ObjectInputStream(s.getInputStream());

                    List<AGV> agvsToChange = new LinkedList<>();

                    for (AGV agv : taskRepository.findAllAGV()) {
                        if (Objects.equals(agv.getTaskStatus(), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE))){
                            agvsToChange.add(agv);
                        }
                    }

                    sendAGVsToChangeList.writeObject(agvsToChange);
                    sendAGVsToChangeList.flush();

                    updatedAGVList = (List<AGV>) getAGVsChangedList.readObject();

                    for(AGV updatedAGV : updatedAGVList){
                        agvRepository.save(updatedAGV);
                    }
                }


                //2. US5002: colocar AGVS designados a uma task como occupied
                if (clientMessageUS[1] == 8){
                    ObjectOutputStream sendAGVsChangedList = new ObjectOutputStream(s.getOutputStream());
                    //3. enviar lista ao agv twin client

                    List<AGV> allAGVsUpdated = (List<AGV>) agvRepository.findAll();

                    sendAGVsChangedList.writeObject(allAGVsUpdated);
                    sendAGVsChangedList.flush();
                }

                if(clientMessageUS[1] == 9){
                    ObjectOutputStream sendWarehousePlant = new ObjectOutputStream(s.getOutputStream());

                    Iterable<WarehousePlant> warehousePlantIterable = (Iterable<WarehousePlant>) warehousePlantRepository.findAll();
                    WarehousePlant warehousePlant = warehousePlantIterable.iterator().next();

                    sendWarehousePlant.writeObject(warehousePlant);
                    sendWarehousePlant.flush();
                }

                if(clientMessageUS[1] == 10){
                    ObjectOutputStream sendAGVDocksList = new ObjectOutputStream(s.getOutputStream());

                    List<AgvDock> agvDocksList = (List<AgvDock>) agvDockRepository.findAll();

                    sendAGVDocksList.writeObject(agvDocksList);
                    sendAGVDocksList.flush();
                }

                if(clientMessageUS[1] == 11){
                    ObjectOutputStream sendAislesList = new ObjectOutputStream(s.getOutputStream());

                    List<Aisle> aisles = (List<Aisle>) aisleRepository.findAll();

                    sendAislesList.writeObject(aisles);
                    sendAislesList.flush();
                }

                byte[] clientMessageEnd = sIn.readNBytes(4);
                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Client's End Code (1) has been received.");
                    byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    System.out.println("[INFO] Sending the Acknowledge Code (2) to the Client.");
                    sOut.write(serverMessageEnd);
                    sOut.flush();
                    System.out.println("[INFO] Client " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " has disconnected.");
                } else {
                    System.out.println("[ERROR] Client's TCP is not valid.");
                }

            } else {
                System.out.println("[ERROR] Client's TCP is not valid.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();/*
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();*/
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Trouble Closing the Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Closed.\n\n");
        }
    }
}



