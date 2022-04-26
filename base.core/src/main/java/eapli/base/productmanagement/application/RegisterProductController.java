package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.ListProductCategoryService;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterProductController {

    private ListProductCategoryService svc;

    private ProductCategoryRepository productCategoryRepository;

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ProductRepository repository = PersistenceContext.repositories().products();

    public Product registerProduct(final String uniqueInternalCode, final String shortDescription, final String extendedDescription,
                                   final Double priceWithoutTaxes, final String status, final Double weight, final Double volume, final Double tax, final String productCategory){
        return registerProduct(/*atributos obrigatorios + null*/);
    }

    public Product registerProduct(/*atributos*/){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);

        final var newProduct = new ProductBuilder()
                //+atributos
                .build();

        return repository.save(newProduct);
    }

    public Iterable<ProductCategory> productCategories(){
        return svc.allProductCategories();
    }

}
