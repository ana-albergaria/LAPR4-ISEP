package eapli.base.shoppingcartmanagement.application;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.ListProductDTOService;
import eapli.base.productmanagement.application.ListProductService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Optional;

public class AddProductShoppingCartController {

    private final ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private final ShoppingCartRepository shoppingCartRepository = PersistenceContext.repositories().shoppingCarts();
    private final ListProductDTOService productDTOService = new ListProductDTOService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    private Product product;

    public Long getShoppingCartFromClient(String email) {
        ShoppingCart theShoppingCart;

        Optional<Client> client = clientRepository.findByEmail(Email.valueOf(email));
        if(client.isPresent()) {
            Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findShoppingCartByClient(client.get());
            if(!shoppingCart.isPresent()) {
                theShoppingCart = new ShoppingCart(client.get());
            }
            return shoppingCart.get().identity();
        }
        return null;
    }

    public boolean verifyProductById(String code) {
        Optional<Product> chosenProduct = productRepository.ofIdentity(Code.valueOf(code));
        if(chosenProduct.isPresent())
            product = chosenProduct.get();
        return product != null;
    }

    public Iterable<ProductDTO> showProductCatalog() {
        return productDTOService.allProducts();
    }


}
