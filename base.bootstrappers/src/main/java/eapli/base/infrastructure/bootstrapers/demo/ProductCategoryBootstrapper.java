package eapli.base.infrastructure.bootstrapers.demo;

/*import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.ListProductCategoryService;
import eapli.base.productmanagement.domain.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.clientusermanagement.application.AcceptRefuseSignupFactory;
import eapli.base.clientusermanagement.application.AcceptRefuseSignupRequestController;
import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.myclientuser.application.SignupController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.HashSet;
import java.util.Set;

public class ProductCategoryBootstrapper implements Action{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryBootstrapper.class);

    final ListProductCategoryService listProductCategoryService = new ListProductCategoryService();

    private final CreateCategoryController controller = new CreateCategoryController();

    @Override
    public boolean execute() {
        register(TestDataConstants.PRODUCT_CATEGORY_SHAMPOO,"This is a category of very nice shampoos.");
        register(TestDataConstants.PRODUCT_CATEGORY_FISH,"This is a category of very nice fishes.");
        register(TestDataConstants.PRODUCT_CATEGORY_JEANS,"This is a categort of very nice jeans.");
        return true;
    }

    private ProductCategory register(final String alphanumericCode, final String description) {
        ProductCategory pc = null;
        try {
            pc = controller.createCategory(alphanumericCode,description);
            LOGGER.debug("»»» %s", alphanumericCode);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            pc = listProductCategoryService.find(AlphaNumericCode.valueOf(alphanumericCode)).orElseThrow(() -> e);
        }
        return pc;
    }
}
*/