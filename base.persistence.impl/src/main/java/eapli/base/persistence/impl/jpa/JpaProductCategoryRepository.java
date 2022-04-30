package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaProductCategoryRepository extends BaseJpaRepositoryBase<ProductCategory, AlphaNumericCode, AlphaNumericCode>
implements ProductCategoryRepository {
    //comentario
    JpaProductCategoryRepository() {
        super("alphanumericCode");
    }

    @Override
    public Optional<ProductCategory> findByAlphanumericCode(AlphaNumericCode code) {
        final Map<String, Object> params = new HashMap<>();
        params.put("code",code);
        return matchOne("e.productcategory.alphanumericcode=:code", params);
    }
}
