package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class VerifyIfProductExistsRequest extends OrderServerRequest {


    public VerifyIfProductExistsRequest(final OrderSrvController ctrl,
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
            this.ctrlShopCart.verifyIfProductExists(clientMessageUS,sIn,sOut);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
