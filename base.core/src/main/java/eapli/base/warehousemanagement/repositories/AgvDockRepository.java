package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.framework.domain.repositories.DomainRepository;

public interface AgvDockRepository extends DomainRepository<String, AgvDock> {
    @Override
    Iterable<AgvDock> findAll();
}
