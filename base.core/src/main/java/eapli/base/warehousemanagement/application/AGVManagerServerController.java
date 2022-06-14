package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

public class AGVManagerServerController {
    public AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    public Iterable<AGV> allAGVS(){
        return agvRepository.findAll();
    }
}
