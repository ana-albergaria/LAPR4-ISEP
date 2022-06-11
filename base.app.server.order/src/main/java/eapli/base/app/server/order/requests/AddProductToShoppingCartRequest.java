package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddProductToShoppingCartRequest extends OrderServerRequest {

    public AddProductToShoppingCartRequest(final OrderSrvController ctrl,
                                        final byte request,
                                        final ObjectOutputStream sOutObject,
                                        final DataInputStream sIn,
                                        final DataOutputStream sOut,
                                        final byte[] clientMessageUS) {
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS);
    }

    @Override
    public void execute() {
        try {
            this.orderSrvController.addProductToShoppingCart(clientMessageUS, sIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
