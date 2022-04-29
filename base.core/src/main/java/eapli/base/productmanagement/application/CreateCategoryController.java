package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.CategoryDescription;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CreateCategoryController {
    private ProductCategoryRepository repository;
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public void createCategory(String alphaNumericCode, String description){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        AlphaNumericCode code = new AlphaNumericCode(alphaNumericCode);
        CategoryDescription categoryDescription = new CategoryDescription(description);

        ProductCategory category = new ProductCategory(code, categoryDescription);
        repository.save(category);
    }
}
