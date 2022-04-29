package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductCategoryRepository extends DomainRepository<AlphaNumericCode, ProductCategory> {

    Iterable<ProductCategory> findAll();

}
