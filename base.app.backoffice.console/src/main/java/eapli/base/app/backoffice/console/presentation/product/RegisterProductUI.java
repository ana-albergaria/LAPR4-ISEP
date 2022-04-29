package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class RegisterProductUI extends AbstractUI {

    private final RegisterProductController theController = new RegisterProductController();

    @Override
    protected boolean doShow(){
        final Iterable<ProductCategory> productCategories = theController.productCategories();
        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Select a product category", productCategories, new ProductCategoryPrinter());
        selector.show();
        final ProductCategory productCategory = selector.selectedElement();
        final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
        final String barcode = Console.readLine("Barcode: ");
        final String shortDescription = Console.readLine("Short Description: ");
        final String extendedDescription = Console.readLine("Extended Description: ");
        final double priceWithoutTaxes = Console.readDouble("Price Without Taxes: ");
        final String status = Console.readLine("Status (AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE): ");
        final double weight = Console.readDouble("Weight: ");
        final double volume = Console.readDouble("Volume: ");
        final double priceWithTaxes = Console.readDouble("Price With Taxes: ");
        final String technicalDescription = Console.readLine("Technical Description: ");
        final String brandName = Console.readLine("Brand Name: ");
        final String reference = Console.readLine("Reference: ");
        final String productionalCode = Console.readLine("Production Code: ");
        final Set<Photo> photos = new HashSet<>();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG, JPG, JPEG & SVG", "png", "jpg", "jpeg", "svg", "PNG", "JPG", "JEPG", "SVG"
        );
        chooser.setFileFilter(filter);
        String option = "yes";
        Photo photo;
        do {
            System.out.println("Select a photo.");
            int photoValue = chooser.showOpenDialog(null);
            if (photoValue == JFileChooser.APPROVE_OPTION) {
                photo = new Photo(chooser.getSelectedFile().getPath());
                photos.add(photo);
            }
            do {
                if (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no")){
                    System.out.println("Your answer was not valid.");
                }
                option = Console.readLine("Do you want to add another photo? Yes or no? ");
            } while (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no"));
        } while (option.equalsIgnoreCase("yes"));

        try{
            this.theController.registerProduct(uniqueInternalCode, barcode, shortDescription, extendedDescription,
                    priceWithoutTaxes, status, weight, volume, priceWithTaxes, productCategory, technicalDescription,
                    brandName, reference, productionalCode, photos);
            System.out.println("Product successfully created!");
        } catch (final IntegrityViolationException e){
            System.out.println("You tried to enter a product which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Product";
    }

}
