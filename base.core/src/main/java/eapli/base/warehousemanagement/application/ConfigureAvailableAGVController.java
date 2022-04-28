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

    public Map<AGV, List<String>> getAvailableAGVInformations(){
        Map<AGV, List<String>> availableAGVsInfo = new HashMap<>();
        List<String> informations = new ArrayList<String>();

        for(AGV agv : repository.findAll()){
            informations.add(agv.getAgvID().toString());
            informations.add(agv.getModelID().toString());
            informations.add(agv.getAgvID().toString());
            informations.add(agv.getAutonomyStatus().toString());
            informations.add(agv.getTaskStatus().toString());

            availableAGVsInfo.put(agv, informations);

            informations.clear();
        }

        return availableAGVsInfo;
    }
}