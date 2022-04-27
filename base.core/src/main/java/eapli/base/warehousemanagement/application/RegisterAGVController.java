package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVRepository repository = PersistenceContext.repositories().agvs();

    public AGV registerAGV(final Long agvID,final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final String modelID, final String shortDescription,
                           final Double maxWeight, final String agvDockID, final Square beginSquare, final Square endSquare, final Square depthSquare, final Accessibility accessibilityDirection){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        final var newAGV = new AGVBuilder()
                .withID(agvID)
                .withAutonomy(autonomyStatus)
                .withTask(taskStatus)
                .withModel(modelID, shortDescription, maxWeight)
                .withAGVDock(agvDockID, beginSquare, endSquare, depthSquare, accessibilityDirection)
                .build();

        return repository.save(newAGV);
    }
}
