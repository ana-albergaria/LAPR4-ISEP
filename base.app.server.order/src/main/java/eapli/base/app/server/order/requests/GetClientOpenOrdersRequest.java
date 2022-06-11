package eapli.base.app.server.order.requests;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingcartmanagement.application.OrderSrvController;
import org.hibernate.criterion.Order;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetClientOpenOrdersRequest extends OrderServerRequest{
    protected GetClientOpenOrdersRequest(OrderSrvController orderSrvController,
                                         byte request,
                                         ObjectOutputStream sOutObject,
                                         DataInputStream sIn,
                                         DataOutputStream sOut,
                                         byte[] clientMessageUS){
        super(orderSrvController, request, sOutObject, sIn, sOut, clientMessageUS);
    }

    @Override
    public void execute() {
        try {
            OrderStatus status = OrderStatus.valueOf(OrderStatus.Status.DELIVERED_BY_CARRIER);
            Iterable<OrderDTO> productCatalog = this.orderSrvController.allOpenOrders(status);
            sOutputObject.writeObject(productCatalog);
            sOutputObject.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] An error because of the ObjectOutputStream has occured");
        }
    }
}
