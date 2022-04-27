package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.repositories.ShelfRepository;

public class JpaShelfRepository extends BaseJpaRepositoryBase<Shelf, Long, Long> implements ShelfRepository {
    JpaShelfRepository(){super("shelf id");}
}
