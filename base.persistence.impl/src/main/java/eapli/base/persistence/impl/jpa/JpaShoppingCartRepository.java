package eapli.base.persistence.impl.jpa;

import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;

public class JpaShoppingCartRepository extends BaseJpaRepositoryBase<ShoppingCart, Long, Long>
implements ShoppingCartRepository {

    JpaShoppingCartRepository() {
        super("shoppingCartId");
    }
}
