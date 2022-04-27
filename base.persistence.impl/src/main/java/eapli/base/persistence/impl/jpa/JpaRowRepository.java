package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Row;
import eapli.base.warehousemanagement.repositories.RowRepository;

public class JpaRowRepository extends BaseJpaRepositoryBase<Row, Long, Long> implements RowRepository {
    JpaRowRepository(){super("row id");}
}
