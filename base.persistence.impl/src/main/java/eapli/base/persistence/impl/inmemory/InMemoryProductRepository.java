package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, Code>
implements ProductRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Product> findAll(){
        ProductRepository repository = PersistenceContext.repositories().products();

        List<Product> list = new LinkedList<>();

        for(Product product : repository.findAll()){
            list.add(product);
        }

        return list;
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

    @Override
    public boolean containsOfIdentity(Code id) {
        return super.containsOfIdentity(id);
    }

    @Override
    public boolean contains(Product entity) {
        return super.contains(entity);
    }
}
