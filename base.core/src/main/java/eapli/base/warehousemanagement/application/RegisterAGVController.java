/*package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AgvDockRepository;
import eapli.base.warehousemanagement.repositories.SquareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterAGVController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();

    private final AGVRepository agvRepostitory = PersistenceContext.repositories().agvs();

    private final SquareRepository squareRepository = PersistenceContext.repositories().squares();

    public AGV registerAGV(final Long agvID,final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final String modelID, final String shortDescription,
                           final Double maxWeight, final String agvDockID, final int beginSquareLength, final int beginSquareWidth, final int endSquareLength, final int endSquareWidth,
                           final int depthSquareLength, final int depthSquareWidth, final Accessibility accessibilityDirection){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        final var beginSquare = new Square(beginSquareLength, beginSquareWidth);
        final var endSquare = new Square(endSquareLength, endSquareWidth);
        final var depthSquare = new Square(depthSquareLength, depthSquareWidth);

        squareRepository.save(beginSquare);
        squareRepository.save(endSquare);
        squareRepository.save(depthSquare);

        final var newAGVDock = new AgvDockBuilder()
                .hasDockID(agvDockID)
                .hasBegin(beginSquare)
                .hasEnd(endSquare)
                .hasDepth(depthSquare)
                .hasAccessibility(accessibilityDirection)
                .build();

        agvDockRepository.save(newAGVDock);

        final var newAGV = new AGV(agvID, autonomyStatus, taskStatus, modelID, shortDescription, maxWeight, newAGVDock);

        return agvRepostitory.save(newAGV);
    }
}*/