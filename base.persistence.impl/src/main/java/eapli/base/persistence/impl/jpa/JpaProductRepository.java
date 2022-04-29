package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

public class JpaProductRepository extends BaseJpaRepositoryBase<Product, Code, Code>
    implements ProductRepository {
    JpaProductRepository() {
        super("uniqueInternalCode");
    }
}
