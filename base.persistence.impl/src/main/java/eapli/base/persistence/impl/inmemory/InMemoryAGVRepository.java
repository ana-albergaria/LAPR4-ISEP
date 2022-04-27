package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, Long> implements AGVRepository {
    static {
        InMemoryInitializer.init();
    }
}
