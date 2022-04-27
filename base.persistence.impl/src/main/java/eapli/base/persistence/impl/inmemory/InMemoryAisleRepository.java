package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAisleRepository extends InMemoryDomainRepository<Aisle, Long> implements AisleRepository {
    static {
        InMemoryInitializer.init();
    }
}
