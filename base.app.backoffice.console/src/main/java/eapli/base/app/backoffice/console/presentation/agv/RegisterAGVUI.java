package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterAGVUI extends AbstractUI {

    private final RegisterAGVController controller = new RegisterAGVController();

    @Override
    protected boolean doShow() {
        String option = "";
        Long numDocks = Long.valueOf(0);
        AgvDockRepository dockRepository = PersistenceContext.repositories().agvDocks();
        Scanner read = new Scanner(System.in);
        do {
            final Long agvID = Console.readLong("AGV ID:");
            final String autonomyStatus = Console.readLine("Autonomy Status:");
            final String taskStatus = Console.readLine("Task Status:");
            final String modelID = Console.readLine("Model ID:");
            final String shortDescription = Console.readLine("Short Description:");
            final Double maxWeight = Console.readDouble("Max Weight:");
            /*final String agvDockID = Console.readLine("AGV Dock ID:");
            final Long beginSquareLength = Console.readLong("Begin Square Length:");
            final Long beginSquareWidth = Console.readLong("Begin Square Width:");
            final Long endSquareLength = Console.readLong("End Square Length:");
            final Long endSquareWidth = Console.readLong("End Square Width:");
            final Long depthSquareLength = Console.readLong("Depth Square Length:");
            final Long depthSquareWidth = Console.readLong("Depth Square Width:");
            final String accessibilityDir = Console.readLine("Accessibility Direction:");*/

            AutonomyStatus autonomy = new AutonomyStatus(autonomyStatus);
            TaskStatus task = new TaskStatus(taskStatus);
            //Accessibility accessibilityDirection = new Accessibility(accessibilityDir);

            Map<Integer, AgvDock> dockOptions = new HashMap<>();
            numDocks = dockRepository.count();
            AgvDock dock = null;
            int i = 1;

            System.out.println("To which dock do you want to associate the AGV?");
            for(AgvDock agvDock : dockRepository.findAll()){
                System.out.println(i + " - " + agvDock.getAgvDockID());
                dockOptions.put(i, agvDock);
                i++;
            }

            final Integer selectedDock = read.nextInt();

            for(Integer numberedDocks : dockOptions.keySet()){
                if(numberedDocks.equals(selectedDock)){
                    dock = dockOptions.get(numberedDocks);
                }
            }

            try {
                this.controller.registerAGV(agvID, autonomy, task, modelID, shortDescription, maxWeight, dock);
                System.out.println("AGV created with success!");
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("You tried to enter an AGV that already exists in the database.");
            }

            if(numDocks < 0){
                System.out.println("No more docks available, so it is not possible to register any more AGVs.");
            }else {
                option = Console.readLine("Do you want to register more AGVs? \n Yes - Y | No - N");
            }

        }while ((!option.equalsIgnoreCase("no") && !option.equalsIgnoreCase("n"))/* && (numDocks < 0)*/);

        return false;
    }

    @Override
    public String headline() {
        return "Register AGV";
    }
}
