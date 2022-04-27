package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAgvDockRepository extends InMemoryDomainRepository<AgvDock, Long> implements AgvDockRepository {
    static {
        InMemoryInitializer.init();
    }
}
