import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.warehousemanagement.application.AutomaticallyAssignOrdersToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;

import java.io.*;
import java.net.*;
import java.util.List;

class TcpSrvSum {
    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(9999); }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            new Thread(new TcpSrvSumThread(cliSock)).start();
        }
    }
}



class TcpSrvSumThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpSrvSumThread(Socket cli_s) { s=cli_s;}

    private final AutomaticallyAssignOrdersToFreeAGVController controller = new AutomaticallyAssignOrdersToFreeAGVController(); //-> used in US4002

    public void run() {
        long f,i,num,sum;
        InetAddress clientIP;

        clientIP=s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());
        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            //preencher código necessário à US

            //==============================
            //====== INICIO DA US4002 ======
            //==============================
            TheOrder selectedOrder;
            AGV selectedAGV;
            List<TheOrder> ordersToAssign = controller.getOrdersToAssign();
            List<AGV> agvsAvailable = controller.getAGVsAvailable();
            if (ordersToAssign.isEmpty()){
                System.out.println("There are no orders waiting to be assigned.");
            } else if (agvsAvailable.isEmpty()){
                System.out.println("There are no available AGVs.");
            }
            int number = 0;
            int ordersToAssignSize = ordersToAssign.size();
            int agvsAvailableSize = agvsAvailable.size();
            do {
                selectedOrder = ordersToAssign.get(number);
                selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                controller.updateOrder(selectedOrder);
                selectedAGV = agvsAvailable.get(number);
                selectedAGV.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
                controller.updateAGV(selectedAGV);
                System.out.printf("AGV (ID: %d) successfully assigned to the Order (ID: %d). The Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
                number++;
            } while (number+1<=ordersToAssignSize && number+1<=agvsAvailableSize);
            //=============================
            //======= FIM DA US4002 =======
            //=============================


            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();
        }
        catch(IOException ex) { System.out.println("IOException"); }
    }
}



