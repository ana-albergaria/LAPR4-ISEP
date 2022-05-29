package Positioning;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;

public class AGVCurrentPositions {
    private final AgvPositionRepository repository = PersistenceContext.repositories().positions();

    public Iterable<AGVPosition> getPositions(){
        return repository.findAll();
    }
}
