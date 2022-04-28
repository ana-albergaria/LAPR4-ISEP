package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigureAvailableAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository repository = PersistenceContext.repositories().agvs();

    public Map<AGV, String> getAvailableAGVInformations(){
        Map<AGV, String> availableAGVsInfo = new HashMap<>();


        for(AGV agv : repository.findAll()){
            availableAGVsInfo.put(agv, agv.toString()
                    + agv.getModelID().toString()
                    //+ agv.getAgvID().toString() -- AGV Dock ID, ainda não implementado.
                    + agv.getAutonomyStatus().toString()
                    + agv.getTaskStatus().toString());
        }

        return availableAGVsInfo;
    }
}