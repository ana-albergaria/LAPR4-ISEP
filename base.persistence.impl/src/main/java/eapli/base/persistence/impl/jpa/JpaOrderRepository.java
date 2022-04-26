package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;

public class JpaOrderRepository extends BaseJpaRepositoryBase<TheOrder, Long, Long>
    implements OrderRepository {

    JpaOrderRepository() {
        super("order id");
    }


}
