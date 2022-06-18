package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.*;

public class AGVManagerServerController {
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();
    private final PlantRepository warehousePlantRepository = PersistenceContext.repositories().plants();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisles();
    private final AgvPositionRepository agvPositionRepository = PersistenceContext.repositories().positions();
    private final TaskRepository taskRepository = PersistenceContext.repositories().tasks();

    public Iterable<AGV> allAGVS(){
        return agvRepository.findAll();
    }

    public Iterable<WarehousePlant> warehousePlant(){
        return warehousePlantRepository.findAll();
    }

    public Iterable<AgvDock> agvDocks(){
        return agvDockRepository.findAll();
    }

    public Iterable<Aisle> aisles(){
        return aisleRepository.findAll();
    }

    public Iterable<AGVPosition> positions(){
        return agvPositionRepository.findAll();
    }

    public Iterable<TheTask> taskByAGV(AGV agv) {
        return this.taskRepository.findByAgv(agv);
    }
}
