package eapli.base.dashboard.application;

import eapli.base.dashboard.domain.HTTPServerAGVS;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class DashboardController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final GetPositions positions = new GetPositions();
    private Iterable<AGVPosition> agvPositions;
    private Iterable<AGV> agvs;
    static private WarehousePlant plant;
    static private Iterable<AgvDock> docks;
    static private Iterable<Aisle> aisles;


    public void showDashboard(String ipAddress){
        HTTPServerAGVS server = new HTTPServerAGVS(ipAddress);
        server.setController(this);
        server.start();
    }


    public void getPositions(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        agvPositions= positions.getPositions(option, ipAddress);

    }

    public void getAgvs(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        agvs = positions.getAgvs(option, ipAddress);

    }

    public void getPlant(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        plant = positions.getPlant(option, ipAddress);

    }

    public void getDocks(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        docks = positions.getDocks(option, ipAddress);

    }

    public void getAisles(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        aisles = positions.getAisles(option, ipAddress);

    }
}
