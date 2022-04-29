package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;

public class JpaAgvDockRepository extends BaseJpaRepositoryBase<AgvDock, String, String> implements AgvDockRepository {
    JpaAgvDockRepository(){super("agv dock id");}
}
