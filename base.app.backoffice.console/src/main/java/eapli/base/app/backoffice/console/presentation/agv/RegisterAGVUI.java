package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.Reader;
import java.util.*;

public class RegisterAGVUI extends AbstractUI {

    private final RegisterAGVController controller = new RegisterAGVController();

    @Override
    protected boolean doShow() {
        String option = "";
        Long numDocks = Long.valueOf(0);
        AgvDockRepository dockRepository = PersistenceContext.repositories().agvDocks();
        AGVRepository agvRepository = PersistenceContext.repositories().agvs();
        Scanner read = new Scanner(System.in);

        Long agvID;
        String autonomyStatus, taskStatus, modelID, shortDescription;
        Double maxWeight;
        AutonomyStatus autonomy;
        TaskStatus task;

        do {
            Map<Integer, AgvDock> dockOptions = new HashMap<>();
            AgvDock dock = null;

            Iterable<AgvDock> docks = dockRepository.findAll();
            List<AgvDock> selectedDocks = (List<AgvDock>) docks;
            List<String> dockIDs = new LinkedList<>();
            List<AGV> agvList = (List<AGV>) agvRepository.findAll();

            for(AgvDock dockOfAGV : docks){
                dockIDs.add(dockOfAGV.identity());
            }

            for(AGV agv : agvList){
                AgvDock theAGVDock = agv.getAgvDock();
                String id = theAGVDock.getAgvDockID();
                if(dockIDs.contains(id)){
                    selectedDocks.remove(theAGVDock);
                }
            }

            numDocks = (long) selectedDocks.size();

            int i = 1;

            if(!selectedDocks.isEmpty()) {
                agvID = Console.readLong("AGV ID:");
                autonomyStatus = Console.readLine("Autonomy Status:");
                taskStatus = Console.readLine("Task Status:");
                modelID = Console.readLine("Model ID:");
                shortDescription = Console.readLine("Short Description:");
                maxWeight = Console.readDouble("Max Weight:");

                autonomy = new AutonomyStatus(autonomyStatus);
                task = new TaskStatus(taskStatus);

                System.out.println("To which dock do you want to associate the AGV?");
                for(AgvDock agvDock : selectedDocks) {
                    System.out.println(i + " - " + agvDock.getAgvDockID());
                    dockOptions.put(i, agvDock);
                    i++;
                }

                final Integer selectedDock = read.nextInt();

                for (Integer numberedDocks : dockOptions.keySet()) {
                    if (numberedDocks.equals(selectedDock)) {
                        dock = dockOptions.get(numberedDocks);
                        numDocks--;
                    }
                }
            }else{
                System.out.println("No more available Docks. Try again later...");
                return false;
            }

            try {
                this.controller.registerAGV(agvID, autonomy, task, modelID, shortDescription, maxWeight, dock);
                System.out.println("AGV created with success!");
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("You tried to enter an AGV that already exists in the database.");
            }

            if(numDocks <= 0){
                System.out.println("No more docks available, so it is not possible to register any more AGVs.");
            }else {
                option = Console.readLine("Do you want to register more AGVs? \n Yes - Y | No - N");
            }

        }while ((!option.equalsIgnoreCase("no") && !option.equalsIgnoreCase("n")) && (numDocks < 0));

        return false;
    }

    @Override
    public String headline() {
        return "Register AGV";
    }
}
