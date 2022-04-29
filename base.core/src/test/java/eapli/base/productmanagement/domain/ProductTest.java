package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Money;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

public class ProductTest {

    private static final Code UNIQUE_INTERNAL_CODE = Code.valueOf("abcd.12345");
    private static final ProductCategory PRODUCT_CATEGORY = new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("description"));

    @Test
    public void ensureDishWithObrigatoryParameters(){
        new Product(UNIQUE_INTERNAL_CODE,ShortDescription.valueOf("Short description."), ExtendedDescription.valueOf("Very very very very very very very extended description."),
                Money.valueOf(1.5,"EUR"), Product.Status.AVAILABLE,Weight.valueOf(10),Volume.valueOf(5.3),Money.valueOf(3,"EUR"),PRODUCT_CATEGORY);
        assertTrue(true);
    }



}
