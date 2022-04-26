package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

public class JpaAGVRepository extends BaseJpaRepositoryBase<AGV, Long, Long> implements AGVRepository {

    JpaAGVRepository() {
        super("AGV ID");
    }
}
