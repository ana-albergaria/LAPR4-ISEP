package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.Order;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JpaOrderRepository extends BaseJpaRepositoryBase<TheOrder, Long, Long>
    implements OrderRepository {

    JpaOrderRepository() {
        super("orderId");
    }

    @Override
    public Iterable<OrderStatus> findAllOrderStatus(){
        final TypedQuery<TheOrder> query = entityManager().createQuery(
                "SELECT o FROM TheOrder o",
                TheOrder.class);

        Iterable<TheOrder> orders = query.getResultList();
        List<OrderStatus> filteredByOrderStatusList = new LinkedList<>();

        for (TheOrder order : orders){
            if (!filteredByOrderStatusList.contains(order.getOrderStatus())){
                filteredByOrderStatusList.add(order.getOrderStatus());
            }
        }
        return filteredByOrderStatusList;
    }

    @Override
    public Iterable<TheOrder> findByOrderStatus(OrderStatus orderStatus){
        final Map<String, Object> params = new HashMap<>();
        params.put("orderStatus", orderStatus);
        return match("e.status=:orderStatus", params);
    }
}
