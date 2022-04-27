package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVBuilder;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class ConfigureAvailableAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository repository = PersistenceContext.repositories().agvs();

    private final AGVBuilder builder = new AGVBuilder();

    public List<String> getAvailableAGVInformations(){
        List<String> informations = new ArrayList<String>();

        informations.add(builder.getAgvID().toString());
        informations.add(builder.getModelID());
        informations.add(builder.getAGVDock().toString());
        informations.add(builder.getAutonomyStatus().toString());
        informations.add(builder.getTaskStatus().toString());

        return informations;
    }
}