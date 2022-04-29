package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;

public class JpaProductCategoryRepository extends BaseJpaRepositoryBase<ProductCategory, String, String>
implements ProductCategoryRepository {
    //comentario
    JpaProductCategoryRepository() {
        super("alphanumericCode");
    }
}
