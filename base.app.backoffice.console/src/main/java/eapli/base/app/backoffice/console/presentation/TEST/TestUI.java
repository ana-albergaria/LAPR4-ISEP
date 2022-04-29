package eapli.base.app.backoffice.console.presentation.TEST;

import eapli.base.app.backoffice.console.presentation.product.ProductCategoryPrinter;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashSet;
import java.util.Set;


public class TestUI extends AbstractUI {

    private final RegisterProductController theController = new RegisterProductController();

    @Override
    protected boolean doShow(){

        final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
        final double priceWithoutTaxes = Console.readDouble("Price Without Taxes: ");
        final double weight = Console.readDouble("OrderWeight: ");
        final double volume = Console.readDouble("Volume: ");
        final double priceWithTaxes = Console.readDouble("Price With Taxes: ");

        try{
            this.theController.test(Code.valueOf(uniqueInternalCode), Money.euros(priceWithoutTaxes), Money.euros(priceWithTaxes), Volume.valueOf(volume), Weight.valueOf(weight));
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
