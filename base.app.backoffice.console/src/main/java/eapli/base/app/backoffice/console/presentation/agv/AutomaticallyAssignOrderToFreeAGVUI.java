package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.warehousemanagement.application.AutomaticallyAssignOrdersToFreeAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class AutomaticallyAssignOrderToFreeAGVUI extends AbstractUI {

    private final AutomaticallyAssignOrdersToFreeAGVController controller = new AutomaticallyAssignOrdersToFreeAGVController();

    @Override
    protected boolean doShow(){
        TheOrder selectedOrder = null;
        AGV selectedAGV = null;
        List<TheOrder> ordersToAssign = controller.getOrdersToAssign();
        List<AGV> agvsAvailable = controller.getAGVsAvailable();
        if (ordersToAssign.isEmpty()){
            System.out.println("There are no orders waiting to be assigned.");
            return false;
        } else if (agvsAvailable.isEmpty()){
            System.out.println("There are no available AGVs.");
            return false;
        }
        int num = 1;
        do {
            selectedOrder = ordersToAssign.get(num-1);
            selectedOrder.setStatus(OrderStatus.valueOf(OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE));
            selectedAGV = agvsAvailable.get(num-1);
            selectedAGV.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
            System.out.printf("Selected AGV (ID: %d) successfully assigned to the selected Order (ID: %d). The selected Order (ID: %d) is now being prepared in the Warehouse!\n", selectedAGV.getAgvID(), selectedOrder.getOrderId(), selectedOrder.getOrderId());
            num++;
        } while (num<=ordersToAssign.size()||num<=agvsAvailable.size());
        return false;
    }

    @Override
    public String headline(){
        return "Automatically Assign Orders to Free AGVs";
    }

}
