package eapli.base.productmanagement.domain;

import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListProductCategoryService {

    private ProductCategoryRepository repo;

    private AuthorizationService authorizationService;

    public Iterable<ProductCategory> allProductCategories(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);
        return repo.findAll();
    }

}
