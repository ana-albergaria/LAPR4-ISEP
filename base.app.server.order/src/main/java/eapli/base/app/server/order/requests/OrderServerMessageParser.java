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

public class OrderServerMessageParser {

    private final OrderSrvAddProductToShoppingCarController ctrlShopCart;

    public OrderServerMessageParser(final OrderSrvAddProductToShoppingCarController ctrlShopCart) {
        this.ctrlShopCart = ctrlShopCart;
    }

    public OrderSrvAddProductToShoppingCarController getCtrlShopCart() {
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
