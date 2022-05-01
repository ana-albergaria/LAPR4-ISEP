package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.repositories.DomainRepository;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.Optional;

public interface ProductRepository extends DomainRepository<Code, Product> {

    @Override
    Iterable<Product> findAll();

    Optional<Product> ofIdentity();

    TypedQuery<Product> query(final String jpql, final Class<Product> classz);
}
