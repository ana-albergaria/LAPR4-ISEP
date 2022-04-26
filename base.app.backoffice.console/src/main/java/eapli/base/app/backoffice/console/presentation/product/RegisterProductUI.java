package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;


public class RegisterProductUI extends AbstractUI {

    private final RegisterProductController theController = new RegisterProductController();

    @Override
    protected boolean doShow(){
        final Iterable<ProductCategory> productCategories = theController.productCategories();
        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Select a product category", productCategories, new ProductCategoryPrinter());
        selector.show();
        final ProductCategory productCategory = selector.selectedElement();
        final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
        final String shortDescription = Console.readLine("Short Description: ");
        final String extendedDescription = Console.readLine("Extended Description: ");
        final Double priceWithoutTaxes = Double.valueOf(Console.readLine("Price Without Taxes: "));
        final String status = Console.readLine("Status: ");
        final Double weight = Double.valueOf(Console.readLine("OrderWeight: "));
        final Double volume = Double.valueOf(Console.readLine("Volume: "));
        final Double tax = Double.valueOf(Console.readLine("Tax: "));
        try{
            this.theController.registerProduct(uniqueInternalCode, shortDescription, extendedDescription,
                    priceWithoutTaxes, status, weight, volume, tax, productCategory);
            System.out.println("Product successfully created!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e){
            System.out.println("You tried to enter a product which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Product";
    }

}
