package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.ProductCategory;

public interface ProductCategoryRepository {

    Iterable<ProductCategory> findAll();

}
