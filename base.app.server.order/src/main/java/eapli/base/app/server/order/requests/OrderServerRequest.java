package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvAddProductToShoppingCarController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

public abstract class OrderServerRequest {
    protected final byte request;
    protected final OrderSrvAddProductToShoppingCarController ctrlShopCart;
    protected final ObjectOutputStream sOutputObject;
    protected final DataInputStream sIn;
    protected final DataOutputStream sOut;
    protected final byte[] clientMessageUS;

    protected OrderServerRequest(final OrderSrvAddProductToShoppingCarController ctrlShopCart,
                                 final byte request,
                                 final ObjectOutputStream sOutObject,
                                 final DataInputStream sIn,
                                 final DataOutputStream sOut,
                                 final byte[] clientMessageUS) {
        this.request = request;
        this.ctrlShopCart = ctrlShopCart;
        this.sOutputObject = sOutObject;
        this.sIn = sIn;
        this.sOut = sOut;
        this.clientMessageUS = clientMessageUS;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract void execute();
}
