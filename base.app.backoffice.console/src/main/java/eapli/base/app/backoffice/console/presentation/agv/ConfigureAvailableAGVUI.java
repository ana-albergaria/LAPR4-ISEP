package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.AGVController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ConfigureAvailableAGVUI extends AbstractUI {

    private final AGVController controller = new AGVController();


    @Override
    protected boolean doShow() {
        final String autonomyStatus = Console.readLine("Autonomy Status:");
        final String taskStatus = Console.readLine("Task Status:");
        final String modelID = Console.readLine("Model ID:");
        final String shortDescription = Console.readLine("Short Description:");
        final Double maxWeight = Console.readDouble("Max Weight:");
        final Integer beginSquare = Console.readInteger("Begin Square:");
        final Integer endSquare = Console.readInteger("End Square:");
        final Integer depthSquare = Console.readInteger("Depth Square:");
        final String accessibilityDirection = Console.readLine("Accessibility Direction:");

        try {
            this.controller.registerAGV(autonomyStatus, taskStatus, modelID, shortDescription, maxWeight, beginSquare, endSquare, depthSquare, accessibilityDirection);
            System.out.println("Client created with success!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a client which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure Available AGV(s)";
    }
}
