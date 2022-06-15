package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.PlantRepository;

public class AGVManagerServerController {
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvs();
    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();
    private final PlantRepository warehousePlantRepository = PersistenceContext.repositories().plants();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisles();

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
}
