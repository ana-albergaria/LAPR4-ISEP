package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.AGVController;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.Square;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterAGVUI extends AbstractUI {

    private final AGVController controller = new AGVController();


    @Override
    protected boolean doShow() {
        String option = "";
        do {
            final String autonomyStatus = Console.readLine("Autonomy OrderStatus:");
            final String taskStatus = Console.readLine("Task OrderStatus:");
            final String modelID = Console.readLine("Model ID:");
            final String shortDescription = Console.readLine("Short Description:");
            final Double maxWeight = Console.readDouble("Max Weight:");
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
                this.controller.registerAGV(autonomy, task, modelID, shortDescription, maxWeight, beginSquare, endSquare, depthSquare, accessibilityDirection);
                System.out.println("Client created with success!");
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("You tried to enter a client which already exists in the database.");
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
