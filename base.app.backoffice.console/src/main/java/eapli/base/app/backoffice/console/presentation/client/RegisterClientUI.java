package eapli.base.app.backoffice.console.presentation.client;

import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterClientUI extends AbstractUI {

    private final RegisterClientController theController = new RegisterClientController();

    @Override
    protected boolean doShow() {

        //Registar Cliente apenas com os atributos obrigatórios
        final String firstNames = Console.readLine("First Name(s): ");
        final String surnames = Console.readLine("Surname(s): ");
        final String vat = Console.readLine("VAT: ");
        final String email = Console.readLine("E-mail: ");
        final String phoneNumber = Console.readLine("Phone Number: ");

        try {
            this.theController.registerClient(firstNames, surnames, email, phoneNumber, vat);
            System.out.println("Cliente criado com sucesso!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a client which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Client";
    }
}
