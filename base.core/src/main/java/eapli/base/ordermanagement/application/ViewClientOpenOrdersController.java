package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.dto.OrderDTO;

public class ViewClientOpenOrdersController {
    private final ViewClientOpenOrdersService service = new ViewClientOpenOrdersService();

    public Iterable<OrderDTO> allOpenOrders(){
        return service.allOpenOrders();
    }
}
