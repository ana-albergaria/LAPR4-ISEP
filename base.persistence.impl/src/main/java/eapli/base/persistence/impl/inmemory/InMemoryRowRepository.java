package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Row;
import eapli.base.warehousemanagement.repositories.RowRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRowRepository extends InMemoryDomainRepository<Row, Long> implements RowRepository {
    static {
        InMemoryInitializer.init();
    }
}
