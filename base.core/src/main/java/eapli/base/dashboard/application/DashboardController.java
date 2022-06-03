package eapli.base.dashboard.application;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.dashboard.domain.HTTPAgvRequest;
import eapli.base.dashboard.domain.HTTPServerAGVS;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DashboardController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final GetPositions positions = new GetPositions();
    private Iterable<AGVPosition> agvPositions;
    private Iterable<AGV> agvs;
    static private WarehousePlant plant;
    static private Iterable<AgvDock> docks;
    static private Iterable<Aisle> aisles;


    public void showDashboard(){
        HTTPServerAGVS server = new HTTPServerAGVS(agvPositions, agvs, plant, docks, aisles);
        server.setController(this);
        server.start();
    }


    public Iterable<AGVPosition> getPositions(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        agvPositions= positions.getPositions(option, ipAddress);

        return agvPositions;
    }

    public Iterable<AGV> getAgvs(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        agvs = positions.getAgvs(option, ipAddress);

        return agvs;
    }

    public WarehousePlant getPlant(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        plant = positions.getPlant(option, ipAddress);

        return plant;
    }

    public Iterable<AgvDock> getDocks(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        docks = positions.getDocks(option, ipAddress);

        return docks;
    }

    public Iterable<Aisle> getAisles(int option, String ipAddress){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        aisles = positions.getAisles(option, ipAddress);

        return aisles;
    }
}
