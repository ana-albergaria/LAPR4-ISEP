package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Money;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

public class ProductTest {

    private static final Code UNIQUE_INTERNAL_CODE = Code.valueOf("abcd.12345");
    private static final ProductCategory PRODUCT_CATEGORY = new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("description"));

    @Test
    public void ensureProductWithObrigatoryParameters(){
        new Product(UNIQUE_INTERNAL_CODE,Barcode.valueOf("123456789012"),ShortDescription.valueOf("Short description."), ExtendedDescription.valueOf("Very very very very very very very extended description."),
                Money.euros(1.5), Product.Status.AVAILABLE,Weight.valueOf(10),Volume.valueOf(5.3),Money.euros(3),PRODUCT_CATEGORY);
        assertTrue(true);
    }

    /*@Test
    public void ensureCanBuildProductWithObrigatoryParameters(){
        final Product product = new ProductBuilder()
                .codedAs(UNIQUE_INTERNAL_CODE)
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf(""))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf(""))
                .initialyPricedAs(Money.euros())
    }*/



}
