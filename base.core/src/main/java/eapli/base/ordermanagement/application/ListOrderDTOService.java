package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.application.ListProductDTOService;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 *
 * @author Ana Albergaria
 *
 */
public class ListOrderDTOService {
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<OrderDTO> findOrdersWithStatus(OrderStatus status) {

        final Iterable<TheOrder> orders = this.orderRepository.findByOrderStatus(status);


        // transform for the presentation layer
        final List<OrderDTO> ret = new ArrayList<>();

        orders.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
