package eapli.base.persistence.impl.jpa;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaProductRepository extends BaseJpaRepositoryBase<Product, Code, Code>
    implements ProductRepository {
    JpaProductRepository() {
        super("uniqueInternalCode");
    }

    @Override
    public Optional<Product> ofIdentity() {
        return Optional.empty();
    }

    @Override
    public TypedQuery<Product> query(final String jpql, final Class<Product> classz) {
        ProductRepository repository = PersistenceContext.repositories().products();

        return repository.query(jpql, classz);
    }
}
