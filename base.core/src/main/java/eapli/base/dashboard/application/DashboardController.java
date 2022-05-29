package eapli.base.dashboard.application;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.dashboard.domain.HTTPAgvRequest;
import eapli.base.dashboard.domain.HTTPServerAGVS;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.AGVPosition;
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
    Iterable<AGVPosition> agvPositions;

    public void showDashboard(){
        HTTPServerAGVS server = new HTTPServerAGVS(agvPositions);
        server.setController(this);
        server.start();
    }


    public Iterable<AGVPosition> getPositions(int option){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);

        agvPositions= positions.getPositions(option);

        return agvPositions;
    }
}
