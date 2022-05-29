package srv;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.application.AutomaticallyAssignOrdersToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class TcpSrvAgvManager {
    static ServerSocket sock;

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

    private final AutomaticallyAssignOrdersToFreeAGVController ordersToAGVController = new AutomaticallyAssignOrdersToFreeAGVController(); //-> used in US4002
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    public void run() {
        //long f,i,num,sum;
        InetAddress clientIP;

        clientIP=s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());
        try{
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 4) { //Por exemplo, codigo 4 = Ligar ao AGV Manager e pedir posições do AGV
                //TcpCliAgvManager tcpCliAgvManager = new TcpCliAgvManager(AGV_DIGITAL_TWIN_SERVER_ADDRESS, AGV_DIGITAL_TWIN_SERVER_PORT);

            } else {
                System.out.println("[ERROR] Pacote do Cliente invalido.");
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
                        ordersToAGVController.updateAGV(agv);
                    }
                }
                for (AGV agv : agvRepository.findAll()) {
                    if (taskRepository.findByAgv(agv)==null){
                        if (Objects.equals(agv.getAutonomyStatus(), AutonomyStatus.valueOf("0h"))) {
                            agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.CHARGING));
                        } else {
                            agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
                        }
                        ordersToAGVController.updateAGV(agv);
                    }
                }
            }

            s.close();
        }catch(IOException ex){
            System.out.println("IOException");
        }
    }
}



