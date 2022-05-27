package eapli.base.app.user.console;

import eapli.base.app.user.console.presentation.ClientUserBaseUI;
import eapli.base.app.user.console.tcp.TcpCliOrder;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.io.util.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AddProductShoppingCartUI extends ClientUserBaseUI {
    @Override
    protected boolean doShow() {
        ClientRepository clientRepository = PersistenceContext.repositories().clients();
        Optional<Client> client = clientRepository.findByEmail(Email.valueOf("1201518@isep.ipp.pt"));
        System.out.println(client);


        ProductRepository productRepository = PersistenceContext.repositories().products();
        Product product = productRepository.ofIdentity(Code.valueOf("lmsp.00001")).get();
        OrderItem orderItem = new OrderItem(3, product);
        List<OrderItem> list = new ArrayList<>();
        list.add(orderItem);
        ShoppingCart shoppingCart = new ShoppingCart(client.get(), list);
        ShoppingCartRepository shoppingCartRepository = PersistenceContext.repositories().shoppingCarts();
        shoppingCartRepository.save(shoppingCart);

        String address = Console.readLine("IPv4 Address: ");
        try {
            new TcpCliOrder(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
