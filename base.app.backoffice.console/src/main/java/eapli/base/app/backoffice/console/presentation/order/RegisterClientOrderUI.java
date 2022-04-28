package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.app.backoffice.console.presentation.client.RegisterClientUI;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.application.RegisterClientOrderController;
import eapli.base.ordermanagement.domain.Payment;
import eapli.base.ordermanagement.domain.Shipment;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterClientOrderUI extends AbstractUI {

    private static final Integer NUM_ADDRESSES = 2;

    private final RegisterClientOrderController theController = new RegisterClientOrderController();


    @Override
    protected boolean doShow() {


        final Long clientId = Console.readLong("Cliend ID: ");

        boolean clientExist = this.theController.verifyClientById(clientId);
        if(!clientExist) {
            System.out.println("The client is not registered in the system. Proceed to the client registration.");
            new RegisterClientUI().show();
        }

        List<List<String>> addresses = new ArrayList<>();


        for (int i = 0; i < NUM_ADDRESSES; i++) {
            String typeOfAddress = (i==0) ? "\n>> BILLING ADDRESS" : ">> DELIVERY ADDRESS";
            System.out.println(typeOfAddress);
            
            List<String> address = new ArrayList<>();

            String streetName = Console.readLine("Street Name:");
            address.add(streetName);
            String doorNumber = Console.readLine("Door Number:");
            address.add(doorNumber);
            String postalCode = Console.readLine("Postal Code:");
            address.add(postalCode);
            String city = Console.readLine("City:");
            address.add(city);
            String country = Console.readLine("Country:");
            address.add(country);
            addresses.add(address);
        }

        int i = 1;
        System.out.println("\n>> SHIPMENT:");
        for (Shipment options : Shipment.values()) {
            System.out.printf("%d. %s | %s%n", i , options.name(), options.cost());
            i++;
        }

        int optionShipment = Console.readInteger("Select the option:") - 1;

        if (optionShipment >= i ||  optionShipment < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        Shipment shipment = Shipment.values()[optionShipment];

        int j = 1;
        System.out.println("\n>> PAYMENT:");
        for (Payment options : Payment.values()) {
            System.out.printf("%d. %s%n", j , options.name());
            j++;
        }

        int optionPayment = Console.readInteger("Select the option:") - 1;

        if (optionPayment >= j ||  optionPayment < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        Payment payment = Payment.values()[optionPayment];

        System.out.println(payment.text());

        int k = 1;
        System.out.println("\n>> SOURCE CHANNEL:");
        for (TheOrder.SourceChannel options : TheOrder.SourceChannel.values()) {
            System.out.printf("%d. %s%n", k , options.name());
            k++;
        }

        int optionSourceChannel = Console.readInteger("Select the option:") - 1;

        if (optionSourceChannel >= k ||  optionSourceChannel < 0) {
            throw new UnsupportedOperationException("Invalid Option");
        }

        TheOrder.SourceChannel sourceChannel = TheOrder.SourceChannel.values()[optionSourceChannel];

        Calendar interactionDate = Console.readCalendar("InteractionDate:","yyyy/MM/dd");

        String option = Console.readLine("Do you want to insert an additional comment?\n (yes|no)\n");

        String additionalText = "";

        if(option.equalsIgnoreCase("yes")){
            additionalText = Console.readLine("Additional Comment: ");
        }

        try {
            if(additionalText.isEmpty())
                this.theController.registerOrder(addresses, shipment, payment, sourceChannel, interactionDate);
            else
                this.theController.registerOrder(addresses, shipment, payment, sourceChannel, interactionDate, additionalText);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter an order which already exists in the database.");
        }





        return false;
    }

    @Override
    public String headline() {
        return "Register Order on Behalf of a Costumer";
    }
}
