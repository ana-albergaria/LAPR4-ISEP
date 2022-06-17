package eapli.base.app.backoffice.console.presentation.client;


import eapli.base.app.backoffice.console.presentation.route_planner.AgvRoute;
import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.Square;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.springframework.scheduling.config.Task;

import java.awt.geom.Point2D;
import java.util.*;

public class RegisterClientUI extends AbstractUI {

    private final RegisterClientController theController = new RegisterClientController();

    @Override
    protected boolean doShow() {

        Iterator<AgvDock> docks = PersistenceContext.repositories().agvDocks().findAll().iterator();
        while(docks.hasNext()) {
            AgvDock dock = docks.next();
            System.out.println(dock);
            System.out.println(dock.beginSquare().wSquare());
            System.out.println(dock.beginSquare().lSquare());
        }

        AGV agv = PersistenceContext.repositories().agvs().ofIdentity(334L).get();
        //Iterable<TheTask> listTask = PersistenceContext.repositories().tasks().findByAgv(agv);
        //TheTask task = PersistenceContext.repositories().tasks().findByAgv(agv).iterator().next();
        TheTask task2 = PersistenceContext.repositories().tasks().ofIdentity(230L).get();
        AGVPosition agvPosition = new AGVPosition(new Square(1L,1L), agv);
        String[][] matrix =
                {
                        { "X", "X", "X", "X", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "A1", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D1", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "D5", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "A2", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D3", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "D6", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "D4", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "A4", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                        { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
                };

        AgvRoute route = new AgvRoute(agv,agvPosition,task2,matrix);


        LinkedList<Point2D> finalRoute = route.computeFinalRoute();
        System.out.println(finalRoute);
        /*for (Point2D point : finalRoute) {
            System.out.println(matrix[(int) point.getX()][(int) point.getY()]);
        }*/


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
