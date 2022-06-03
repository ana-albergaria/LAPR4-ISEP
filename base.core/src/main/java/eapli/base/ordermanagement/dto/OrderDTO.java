package eapli.base.ordermanagement.dto;

import java.io.Serializable;

/**
 * DTO for orders.
 *
 * @author Ana Albergaria
 *
 */
public class OrderDTO implements Serializable {

    private final long orderId;

    private final String orderDate;

    private final String clientName;

    private final String status;

    public OrderDTO(final long id, final String date, final String customerName, final String status){
        this.orderId = id;
        this.orderDate = date;
        this.clientName = customerName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "------ Order ------\n" +
                "Id: " + orderId +
                "\nDate: " + orderDate +
                "\nClient: " + clientName +
                "\nStatus='" + status;
    }
}
