package eapli.base.app.backoffice.console.presentation.client;


import eapli.base.app.backoffice.console.presentation.route_planner.AgvRoute;
import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.Square;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterClientUI extends AbstractUI {

    private final RegisterClientController theController = new RegisterClientController();

    @Override
    protected boolean doShow() {


        AGV agv = PersistenceContext.repositories().agvs().ofIdentity(334L).get();
        Iterable<TheTask> listTask = PersistenceContext.repositories().tasks().findByAgv(agv);
        TheTask task = PersistenceContext.repositories().tasks().findByAgv(agv).iterator().next();
        AGVPosition agvPosition = new AGVPosition(new Square(1L,1L), agv);
        String[][] matrix =
                {
                        { "1", "X", "6", "5", "5", "1", "1", "1", "7", "X" },
                        { "3", "6", "2", "X", "6", "5", "7", "2", "6", "6" },
                        { "1", "3", "6", "1", "1", "1", "7", "1", "X", "5" },
                        { "7", "5", "6", "3", "1", "3", "3", "1", "1", "7" },
                        { "3", "X", "6", "X", "7", "2", "6", "5", "X", "X" },
                        { "3", "2", "5", "1", "2", "5", "1", "2", "3", "X" },
                        { "4", "2", "2", "2", "5", "2", "3", "7", "7", "3" },
                        { "7", "2", "X", "3", "5", "2", "2", "3", "6", "3" },
                        { "5", "1", "X", "2", "6", "X", "6", "7", "3", "7" },
                        { "1", "X", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "8", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "7", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "6", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "5", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "4", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "3", "1", "7", "5", "3", "6", "5", "3", "9" },
                        { "1", "2", "1", "7", "5", "3", "6", "5", "3", "9" },
                };

        AgvRoute route = new AgvRoute(agv,agvPosition,task,matrix);


        System.out.println(route.computeFinalRoute());


        Calendar birthDate = null;

        Client.Gender gender = null;

        final String firstNames = Console.readLine("First Names:");
        final String surnames = Console.readLine("Surnames:");
        final String email = Console.readLine("Email:");
        final String phoneNumber = Console.readLine("Phone Number:");
        final String vat = Console.readLine("VAT:");

        List<List<String>> addresses = new ArrayList<>();
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

        String option = Console.readLine("Do you want to insert more addresses?\n (yes|no)\n");

        while (option.equalsIgnoreCase("yes")){

            address = new ArrayList<>();

            streetName = Console.readLine("Street Name:");
            address.add(streetName);
            doorNumber = Console.readLine("Door Number:");
            address.add(doorNumber);
            postalCode = Console.readLine("Postal Code:");
            address.add(postalCode);
            city = Console.readLine("City:");
            address.add(city);
            country = Console.readLine("Country:");
            address.add(country);

            addresses.add(address);

            option = Console.readLine("Do you want to insert more addresses?\n (yes|no)\n");
        }

        option = Console.readLine("Do you want to insert the birth date?\n (yes|no)\n");

        if(option.equalsIgnoreCase("yes")){
            birthDate = Console.readCalendar("Birth Date:","yyyy/MM/dd");
        }

        option = Console.readLine("Do you want to specify the gender?\n (yes|no)\n");

        int i = 1;

        if(option.equalsIgnoreCase("yes")){

            System.out.println("Genders:");
            for (Client.Gender options : Client.Gender.values()) {
                System.out.printf("%d. %s%n", i , options.name());
                i++;
            }

            int optionGender = Console.readInteger("Select the option:") - 1;

            if (optionGender >= i ||  optionGender < 0) {
                throw new UnsupportedOperationException("Invalid Option");
            }

            gender = Client.Gender.values()[optionGender];
        }

        try {
            this.theController.registerClient(firstNames,surnames,email,phoneNumber,vat,addresses,birthDate,gender);
            System.out.println("Client created with success!");
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
