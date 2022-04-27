package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterAGVUI extends AbstractUI {

    private final RegisterAGVController controller = new RegisterAGVController();

    private final AGVBuilder builder = new AGVBuilder();

    @Override
    protected boolean doShow() {
        String option = "";
        do {
            final Long agvID = Console.readLong("AGV ID:");
            final String autonomyStatus = Console.readLine("Autonomy Status:");
            final String taskStatus = Console.readLine("Task Status:");
            final String modelID = Console.readLine("Model ID:");
            final String shortDescription = Console.readLine("Short Description:");
            final Double maxWeight = Console.readDouble("Max Weight:");
            final String agvDockID = Console.readLine("AGV Dock ID:");
            final Integer beginSquareLength = Console.readInteger("Begin Square Length:");
            final Integer beginSquareWidth = Console.readInteger("Begin Square Width:");
            final Integer endSquareLength = Console.readInteger("End Square Length:");
            final Integer endSquareWidth = Console.readInteger("End Square Width:");
            final Integer depthSquareLength = Console.readInteger("Depth Square Length:");
            final Integer depthSquareWidth = Console.readInteger("Depth Square Width:");
            final String accessibilityDir = Console.readLine("Accessibility Direction:");

            AutonomyStatus autonomy = new AutonomyStatus(autonomyStatus);
            TaskStatus task = new TaskStatus(taskStatus);
            Square beginSquare = new Square(beginSquareLength, beginSquareWidth);
            Square endSquare = new Square(endSquareLength, endSquareWidth);
            Square depthSquare = new Square(depthSquareLength, depthSquareWidth);
            Accessibility accessibilityDirection = new Accessibility(accessibilityDir);

            try {
                this.controller.registerAGV(agvID, autonomy, task, modelID, shortDescription, maxWeight, agvDockID, beginSquare, endSquare, depthSquare, accessibilityDirection);
                System.out.println("AGV created with success!");
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("You tried to enter an AGV that already exists in the database.");
            }

            option = Console.readLine("Do you want to register more AGVs? \n Yes - Y | No - N");
        }while (option != "yes" || option != "y");

        return false;
    }

    @Override
    public String headline() {
        return "Register AGV";
    }
}
