package eapli.base.infrastructure.bootstrapers.demo.backoffice;

import eapli.base.infrastructure.bootstrapers.demo.DemoDataConstants;
import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

//importante
public class ProductCategoriesBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoriesBootstrapper.class);

    private CreateCategoryController controller;

    @Override
    public boolean execute(){
        register(DemoDataConstants.PRODUCT_CATEGORY_SHAMPOO,"These are some very nice shampoos.");
        register(DemoDataConstants.PRODUCT_CATEGORY_FISH,"These are some very nice fishes.");
        register(DemoDataConstants.PRODUCT_CATEGORY_JEANS,"These are some very nice jeans.");
        return true;
    }

    public void register(final String alphanumericCode, final String description){
        try{
            controller.createCategory(alphanumericCode,description);
            LOGGER.debug(alphanumericCode);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", alphanumericCode,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
