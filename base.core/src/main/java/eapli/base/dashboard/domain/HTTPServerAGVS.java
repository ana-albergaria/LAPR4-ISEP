package eapli.base.dashboard.domain;

import eapli.base.dashboard.application.DashboardController;
import eapli.base.utils.CreateWarehouseMatrix;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServerAGVS extends Thread{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static private ServerSocket sock;
    static  private Iterable<AGVPosition> positions;
    static private Iterable<AGV> allAgvs;
    static private WarehousePlant plant;
    static private Iterable<AgvDock> docks;
    static private Iterable<Aisle> aisles;

    static final int PORT = 55090;

    private static DashboardController controller;

    public HTTPServerAGVS(Iterable<AGVPosition> agvPositions, Iterable<AGV> agvs, WarehousePlant warehousePlant, Iterable<AgvDock> agvDocks, Iterable<Aisle> allAisles) {
        positions=agvPositions;
        allAgvs=agvs;
        plant = warehousePlant;
        docks = agvDocks;
        aisles = allAisles;
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

    public static synchronized  String getMatrix(){
        String[][] matrix = CreateWarehouseMatrix.createAccordingWithSize(plant);
        CreateWarehouseMatrix.insertObstacles(matrix, docks, aisles);

        String buildInHtml = "<table>";
        for(int i=0; i<matrix.length; i++){
            buildInHtml = buildInHtml +"<tr>";
            for(int j=0; j<matrix[0].length; j++){
                buildInHtml = buildInHtml + "<td>" + matrix[i][j] + "</td>";

            }
            buildInHtml = buildInHtml +"</tr>";
        }
        buildInHtml = buildInHtml +"</table>";

        return buildInHtml;
    }

    public static synchronized String showPositions( ) {

        String buildInHtml;
        /*for(AGVPosition pos: positions) {
            for(AGV agv: allAgvs){
                if(agv.getAgvID().equals(pos.agvID())){
                    textHtml = textHtml + "<tr class=\"active-row\">" +
                            "<td>" + pos.agvID() + "</td>" +
                            "<td>" + pos.lSquare() + "</td>" +
                            "<td>" + pos.wSquare() + "</td>" +
                            "<td>test</td>";
                }
            }
        }*/

        buildInHtml = "<table><tr class=\"active-row\">" +
                "<td>isto</td>" +
                "<td>é</td>" +
                "<td>um</td>" +
                "<td>test</td></table>";
        return buildInHtml;
    }


}
