package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.scheduling.config.Task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AssignOrderToFreeAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Map<Integer, TheOrder> showPaidOrdersList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<TheOrder> ordersAlreadyPaid = new LinkedList<>();
        Map<Integer, TheOrder> paidOrdersList = new HashMap<>();
        OrderStatus orderStatus = OrderStatus.valueOf(OrderStatus.Status.TO_BE_PREPARED);
        int i=1;

        ordersAlreadyPaid = orderRepository.findByOrderStatus(orderStatus);

        for(TheOrder order : ordersAlreadyPaid){
            paidOrdersList.put(i, order);
            i++;
        }

        return paidOrdersList;
    }

    public Map<Integer, AGV> showFreeAGVsList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        Iterable<AGV> agvsAvailable = new LinkedList<>();
        Map<Integer, AGV> freeAGVsList = new HashMap<>();
        int i=1;

        agvsAvailable = agvRepository.findByTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));

        for(AGV agv : agvsAvailable){
            freeAGVsList.put(i, agv);
            i++;
        }

        return freeAGVsList;
    }

    public TheOrder updateOrder(final TheOrder order){
        return orderRepository.save(order);
    }

    public AGV updateAGV(final AGV agv){
        return agvRepository.save(agv);
    }
}
