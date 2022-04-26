package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<TheOrder, Long> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }
}
