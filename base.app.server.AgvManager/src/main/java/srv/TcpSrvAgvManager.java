package srv;
import cli.TcpCliAGVTwin;
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

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvPositionRepository agvPositionRepository = PersistenceContext.repositories().positions();

    private final TcpCliAGVTwin service = new TcpCliAGVTwin();

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
                Iterable<TheOrder> ordersToAssign = orderRepository.findByOrderStatus(OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED));
                List<TheOrder> ordersToAssignList = new ArrayList<>();
                ordersToAssign.forEach(ordersToAssignList::add);
                ordersToAssignList.sort(Comparator.comparing(TheOrder::getCreatedOn));
                Iterable<AGV> agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
                List<AGV> agvsAvailableList = new ArrayList<>();
                agvsAvailable.forEach(agvsAvailableList::add);
                if (ordersToAssignList.isEmpty()){
                    System.out.println("There are no orders waiting to be assigned.");
                } else if (agvsAvailableList.isEmpty()){
                    System.out.println("There are no available AGVs.");
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
                        System.out.printf("AGV (ID: %d) successfully assigned to the Order (ID: %d). The Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
                        number++;
                    } while (number + 1 <= ordersToAssignSize && number + 1 <= agvsAvailableSize);
                    service.updateAgvsStatus();
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



