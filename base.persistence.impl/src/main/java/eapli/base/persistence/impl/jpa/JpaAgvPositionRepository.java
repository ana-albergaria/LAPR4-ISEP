package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;

public class JpaAgvPositionRepository extends BaseJpaRepositoryBase<AGVPosition, Long, Long> implements AgvPositionRepository {
    JpaAgvPositionRepository() {
        super("AGV Position ID");
    }
}
