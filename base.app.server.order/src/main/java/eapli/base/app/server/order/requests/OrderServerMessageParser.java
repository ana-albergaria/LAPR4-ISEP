package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OrderServerMessageParser {

    private final OrderSrvController ctrlShopCart;

    public OrderServerMessageParser(final OrderSrvController ctrlShopCart) {
        this.ctrlShopCart = ctrlShopCart;
    }

    public OrderSrvController getCtrlShopCart() {
        return ctrlShopCart;
    }

    public OrderServerRequest parse(final byte messageRequest,
                                    final ObjectOutputStream sOutObject,
                                    final DataInputStream sIn,
                                    final DataOutputStream sOut,
                                    final byte[] clientMessageUS) throws IOException {

        OrderServerRequest request = null;

        if(messageRequest == 4) {
            request = new GetProductsCatalogRequest(ctrlShopCart, messageRequest, sOutObject, sIn, sOut, clientMessageUS);
        }
        if(messageRequest == 3) {
            request = new VerifyIfProductExistsRequest(ctrlShopCart, messageRequest, sOutObject, sIn, sOut, clientMessageUS);
        }
        if(messageRequest == 5) {
            request = new AddProductToShoppingCartRequest(ctrlShopCart, messageRequest, sOutObject, sIn, sOut, clientMessageUS);
        }

        if(request == null)
            throw new UnsupportedOperationException("The request " + messageRequest + " is not valid - unable to parse.");

        return request;
    }
}
