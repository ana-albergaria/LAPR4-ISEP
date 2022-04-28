package eapli.base.ordermanagement.application;

import eapli.base.clientmanagement.domain.Address;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.application.ListProductService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RegisterClientOrderController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final ListProductService svcProducts = new ListProductService();

    private Client client;
    private Product product;

    public TheOrder registerOrder(List<List<String>> addresses, Shipment shipment, Payment payment, TheOrder.SourceChannel sourceChannel, Calendar interactionDate, String additionalCommentText, Map<String, Integer> items) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        OrderAddress billingAddress = new OrderAddress(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2),
                addresses.get(0).get(3), addresses.get(0).get(4));

        OrderAddress deliveryAddress = new OrderAddress(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2),
                addresses.get(1).get(3), addresses.get(1).get(4));

        AdditionalComment additionalComment = new AdditionalComment(additionalCommentText);

        TheOrder order = new TheOrder(client, billingAddress, deliveryAddress, shipment, payment, sourceChannel, interactionDate, additionalComment, authz.session().get().authenticatedUser());

        return orderRepository.save(order);
    }

    public TheOrder registerOrder(List<List<String>> addresses, Shipment shipment, Payment payment, TheOrder.SourceChannel sourceChannel, Calendar interactionDate, Map<String, Integer> items) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        OrderAddress billingAddress = new OrderAddress(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2),
                addresses.get(0).get(3), addresses.get(0).get(4));

        OrderAddress deliveryAddress = new OrderAddress(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2),
                addresses.get(1).get(3), addresses.get(1).get(4));

        TheOrder order = new TheOrder(client, billingAddress, deliveryAddress, shipment, payment, sourceChannel, interactionDate, authz.session().get().authenticatedUser());

        return orderRepository.save(order);
    }


    public boolean verifyClientById(Long clientId) {
        Optional<Client> chosenClient = clientRepository.ofIdentity(clientId);
        if(chosenClient.isPresent())
            client = chosenClient.get();
        return client != null;
    }

    public boolean verifyProductById(Code code) {
        Optional<Product> chosenProduct = svcProducts.findProductById(code);
        if(chosenProduct.isPresent())
            product = chosenProduct.get();
        return product != null;
    }
}
