package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVBuilder;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository repository = PersistenceContext.repositories().agvs();

    public AGV registerAGV(final String autonomyStatus, final String taskStatus, final String modelID, final String shortDescription,
                           final Double maxWeight, final Integer beginSquare, final Integer endSquare, final Integer depthSquare, final String accessibilityDirection){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        final var newAGV = new AGVBuilder()
                .withAutonomy(autonomyStatus)
                .withTask(taskStatus)
                .withModel(modelID)
                .withDescription(shortDescription)
                .withMaxWeight(maxWeight)
                .build();

        return repository.save(newAGV);
    }
}
