package eapli.base.app.server.order.requests;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.application.OrderSrvAddProductToShoppingCarController;
import eapli.base.utils.MessageUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

public class VerifyIfProductExistsRequest extends OrderServerRequest {


    public VerifyIfProductExistsRequest(final OrderSrvAddProductToShoppingCarController ctrl,
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
