package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The controller for the use case "automaticcaly assign orders to free AGVs".
 *
 * @author Marta Ribeiro 1201592
 */
public class AutomaticallyAssignOrdersToFreeAGVController {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();

    public List<TheOrder> getOrdersToAssign(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        Iterable<TheOrder> ordersToAssign = orderRepository.findByOrderStatus(OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED));
        List<TheOrder> ordersToAssignList = new ArrayList<>();
        ordersToAssign.forEach(ordersToAssignList::add);
        ordersToAssignList.sort(Comparator.comparing(TheOrder::getCreatedOn));
        return ordersToAssignList;
    }

    public List<AGV> getAGVsAvailable(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        Iterable<AGV> agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
        List<AGV> agvsAvailableList = new ArrayList<>();
        agvsAvailable.forEach(agvsAvailableList::add);
        return agvsAvailableList;
    }

    public void updateOrder(final TheOrder order){
        orderRepository.save(order);
    }

    public void updateAGV(final AGV agv){
        agvRepository.save(agv);
    }

    public TheTask registerTask(final AGV agv, final TheOrder order){
        TheTask newTask = new TheTask(agv,order);
        return taskRepository.save(newTask);
    }

}
