package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, Code>
implements ProductRepository {

    static {
        InMemoryInitializer.init();
    }

}
