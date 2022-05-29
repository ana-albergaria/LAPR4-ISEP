package eapli.base.dashboard.domain;

import eapli.base.dashboard.application.DashboardController;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HTTPServerAGVS extends Thread{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static private ServerSocket sock;
    static  private Iterable<AGVPosition> positions;

    static final int PORT = 55090;

    private static DashboardController controller;

    public HTTPServerAGVS(Iterable<AGVPosition> agvPositions) {
        positions=agvPositions;
    }

    public void setController(DashboardController ctrl){
        controller = ctrl;
    }

    public static void main(String[] args) throws IOException {
        Socket cliSock;

        try {
            sock = new ServerSocket(PORT);
            System.out.println("HTTP Server connection opened.");
        }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            HTTPAgvRequest req=new HTTPAgvRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    public static synchronized String showPositions( ) {
        StringBuilder htmlString = new StringBuilder();

        for(AGVPosition pos: positions) {
            htmlString.append("<tr class=\"active-row\">" +
                    "<td>" + pos.agvID() + "</td>" +
                    "<td>" + pos.lSquare() + "</td>" +
                    "<td>" + pos.wSquare() + "</td>" +
                    "</tr>");
        }
        return htmlString.toString();
    }


}
