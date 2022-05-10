package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.warehousemanagement.application.AssignOrderToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.presentation.console.AbstractUI;
import org.hibernate.criterion.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssignOrderToFreeAGVUI extends AbstractUI {
    private final AssignOrderToFreeAGVController controller = new AssignOrderToFreeAGVController();
    private Scanner read = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        boolean isValidOrderOption = false, isValidAGVOption = false;
        int orderOption, agvOption, j = 0, k = 0;

        TaskStatus occupied = new TaskStatus("occupied");

        TheOrder selectedOrder = null;
        AGV selectedAGV = null;

        Map<Integer, Long> paidOrdersList = controller.showPaidOrdersList();
        List<Integer> orderOptionsNum = new LinkedList<>();
        Iterable<TheOrder> orders = controller.findAllOrders();

        Map<Integer, Long> freeAGVsList = controller.showFreeAGVsList();
        List<Integer> agvOptionsNum = new LinkedList<>();
        Iterable<AGV> agvs = controller.findAllAGVs();

        if(paidOrdersList.size() > 0) {
            for (int i = 0; i < paidOrdersList.size(); i++) {
                orderOptionsNum.add(i, i + 1);
            }

            System.out.println("Select one order ready to be prepared: ");

            for (Integer paidOrderNumber : paidOrdersList.keySet()) {
                System.out.printf("%d - Order number: %d\n", paidOrderNumber, paidOrdersList.get(paidOrderNumber));
            }


            do {
                if (j > 0) {
                    System.out.println("Please choose a valid option.");
                }
                orderOption = read.nextInt();

                if (orderOptionsNum.contains(orderOption)) {
                    isValidOrderOption = true;
                }
                j++;
            } while (!isValidOrderOption);


            for (TheOrder order : orders) {
                if (order.getOrderId().equals(paidOrdersList.get(orderOption))) {
                    selectedOrder = order;
                    selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
                }
            }

            if(freeAGVsList.size() > 0) {
                for (int i = 0; i < freeAGVsList.size(); i++) {
                    agvOptionsNum.add(i, i + 1);
                }

                System.out.println("Select one AGV to be assigned to the selected order: ");

                for (Integer freeAGVNumber : freeAGVsList.keySet()) {
                    System.out.printf("%d - AGV ID: %d\n", freeAGVNumber, freeAGVsList.get(freeAGVNumber));
                }

                do {
                    if (k > 0) {
                        System.out.println("Please choose a valid option.");
                    }
                    agvOption = read.nextInt();

                    if (agvOptionsNum.contains(agvOption)) {
                        isValidAGVOption = true;
                    }
                    k++;
                } while (!isValidAGVOption);

                for (AGV agv : agvs) {
                    if (agv.getAgvID().equals(freeAGVsList.get(agvOption))) {
                        selectedAGV = agv;
                        selectedAGV.setTaskStatus(occupied);
                    }
                }
            }else{
                System.out.println("All AGVs are occupied right now...");
                return false;
            }
        }else {
            System.out.println("There are no orders in queue to be prepared in the Warehouse, so it is impossible to assign an AGV...");
            return false;
        }

        if (selectedAGV != null && selectedOrder != null) {
            System.out.printf("Selected AGV (%d) successfully assigned to the selected Order (%d). The selected Order (%d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId());
        } else {
            System.out.println("Error assigning the selected AGV to the selected Order. Try again.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Assign Paid Order to Free AGV";
    }
}