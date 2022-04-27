package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Set;

public class RegisterProductController {

    private ListProductCategoryService svc;

    private ProductCategoryRepository productCategoryRepository;

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    private final ProductRepository repository = PersistenceContext.repositories().products();

    public Product registerProduct(final String uniqueInternalCode, final String shortDescription, final String extendedDescription,
                                   final double priceWithoutTaxes, final String status, final Double weight, final Double volume, final double priceWithTaxes, final ProductCategory productCategory){
        return registerProduct(uniqueInternalCode, shortDescription, extendedDescription, priceWithoutTaxes, status, weight, volume, priceWithTaxes, productCategory,
                null, null, null, null, null);
    }

    public Product registerProduct(final String uniqueInternalCode, final String shortDescription, final String extendedDescription,
                                   final double priceWithoutTaxes, final String status, final double weight, final double volume,
                                   final double priceWithTaxes, final ProductCategory productCategory, final String technicalDescription,
                                   final String brandName, final String reference, final String productionCode, final Set<Photo> photos){
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
