package eapli.base.app.server.order.requests;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingcartmanagement.application.OrderSrvAddProductToShoppingCarController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetProductsCatalogRequest extends OrderServerRequest {

    public GetProductsCatalogRequest(final OrderSrvAddProductToShoppingCarController ctrl,
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
            Iterable<ProductDTO> productCatalog = this.ctrlShopCart.allProducts();
            sOutputObject.writeObject(productCatalog);
            sOutputObject.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] An error because of the ObjectOutputStream has occured");
        }
    }
}
