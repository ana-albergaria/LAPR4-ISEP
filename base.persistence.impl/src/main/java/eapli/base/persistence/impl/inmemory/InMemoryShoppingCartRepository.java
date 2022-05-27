package eapli.base.persistence.impl.inmemory;

import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryShoppingCartRepository extends InMemoryDomainRepository<ShoppingCart, Long>
    implements ShoppingCartRepository {

    static {
        InMemoryInitializer.init();
    }

}
