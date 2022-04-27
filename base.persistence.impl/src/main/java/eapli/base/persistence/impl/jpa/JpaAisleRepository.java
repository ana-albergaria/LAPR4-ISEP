package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.repositories.AisleRepository;

public class JpaAisleRepository extends BaseJpaRepositoryBase<Aisle, Long, Long> implements AisleRepository {
    JpaAisleRepository(){super("aisle id");}
}
