package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.TheRow;
import eapli.base.warehousemanagement.repositories.RowRepository;

public class JpaRowRepository extends BaseJpaRepositoryBase<TheRow, Long, Long> implements RowRepository {
    JpaRowRepository(){super("row id");}
}
