package srv;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AutomaticallyAssignOrdersToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;

import java.io.*;
import java.net.*;
import java.util.Objects;

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
        Socket cliSock;

        try{
            sock = new ServerSocket(2807);
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

    //private final AutomaticallyAssignOrdersToFreeAGVController ordersToAGVController = new AutomaticallyAssignOrdersToFreeAGVController(); //-> used in US4002
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvPositionRepository agvPositionRepository = PersistenceContext.repositories().positions();

    public void run() {
        //long f,i,num,sum;
        InetAddress clientIP;

        clientIP=s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());

        try{
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

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());

                if (clientMessageUS[1] == 6) { //Por exemplo, codigo 6 = Ligar ao AGV Manager e pedir posições do AGV
                    Iterable<AGVPosition> agvPositionIterable = agvPositionRepository.findAll();

                    byte[] agvPositionMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, };
                    //objectOutputStream.write();

                }

                //==============================
                //====== INICIO DA US4002 ======
                //==============================
                /*TheOrder selectedOrder;
                AGV selectedAGV;
                List<TheOrder> ordersToAssign = ordersToAGVController.getOrdersToAssign();
                List<AGV> agvsAvailable = ordersToAGVController.getAGVsAvailable();
                if (ordersToAssign.isEmpty()){
                    System.out.println("There are no orders waiting to be assigned.");
                } else if (agvsAvailable.isEmpty()){
                    System.out.println("There are no available AGVs.");
                } else {
                    int number = 0;
                    int ordersToAssignSize = ordersToAssign.size();
                    int agvsAvailableSize = agvsAvailable.size();
                    do {
                        selectedOrder = ordersToAssign.get(number);
                        selectedAGV = agvsAvailable.get(number);
                        ordersToAGVController.registerTask(selectedAGV,selectedOrder);
                        selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                        ordersToAGVController.updateOrder(selectedOrder);
                        //apagado:
                        //selectedAGV.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
                        //ordersToAGVController.updateAGV(selectedAGV);
                        //porque a atualização dos status vai ser feita na us5002
                        System.out.printf("AGV (ID: %d) successfully assigned to the Order (ID: %d). The Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
                        number++;
                    } while (number + 1 <= ordersToAssignSize && number + 1 <= agvsAvailableSize);
                }*/
                //=============================
                //======= FIM DA US4002 =======
                //=============================

                //US5002: colocar AGVS designados a uma task como occupied
                //e AGVS não designados a tasks como free ou charging
                if (clientMessage[1] == 8){
                    for (AGV agv : taskRepository.findAllAGV()) {
                        if (Objects.equals(agv.getTaskStatus(), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE))){
                            agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
                            //ordersToAGVController.updateAGV(agv);
                            agvRepository.save(agv);
                        }
                    }
                    for (AGV agv : agvRepository.findAll()) {
                        if (taskRepository.findByAgv(agv)==null){
                            if (Objects.equals(agv.getAutonomyStatus(), AutonomyStatus.valueOf("0h"))) {
                                agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.CHARGING));
                            } else {
                                agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
                            }
                            //ordersToAGVController.updateAGV(agv);
                            agvRepository.save(agv);
                        }
                    }
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

        } catch (IOException e) {
            e.printStackTrace();
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



