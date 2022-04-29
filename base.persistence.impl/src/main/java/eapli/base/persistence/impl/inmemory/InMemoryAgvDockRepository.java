package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAgvDockRepository extends InMemoryDomainRepository<AgvDock, String> implements AgvDockRepository {
    static {
        InMemoryInitializer.init();
    }
}
