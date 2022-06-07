package eapli.base.dashboard.domain;

import eapli.base.dashboard.application.DashboardController;
import eapli.base.dashboard.application.GetPositions;
import eapli.base.utils.CreateWarehouseMatrix;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServerAGVS extends Thread{
    private static String ipAddress;
    private static final GetPositions getPositions = new GetPositions();
    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static private ServerSocket sock;
    static  private Iterable<AGVPosition> positions;
    static private Iterable<AGV> allAgvs;
    static private WarehousePlant plant;
    static private Iterable<AgvDock> docks;
    static private Iterable<Aisle> aisles;

    static final int PORT = 55090;

    private static DashboardController controller;

    public HTTPServerAGVS(String ip) {
        ipAddress=ip;
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
            HTTPAgvRequest req=new HTTPAgvRequest(cliSock, BASE_FOLDER, ipAddress);
            req.start();
        }
    }

    public static synchronized  String getMatrix(String ip){
        plant = getPositions.getPlant(9, ip);
        docks = getPositions.getDocks(10, ip);
        aisles = getPositions.getAisles(11, ip);
        String[][] matrix = CreateWarehouseMatrix.createAccordingWithSize(plant);
        CreateWarehouseMatrix.insertObstacles(matrix, docks, aisles);

        int width = Math.toIntExact(plant.warehouseWidth().width());
        int length = Math.toIntExact(plant.warehouseLength().length());

        String buildInHtml = "<table>";
        for(int i=0; i<width; i++){
            buildInHtml = buildInHtml +"<tr>";
            for(int j=0; j<length; j++){
                buildInHtml = buildInHtml + "<td>" + matrix[i][j] + "</td>";

            }
            buildInHtml = buildInHtml +"</tr>";
        }
        buildInHtml = buildInHtml +"</table>";

        return buildInHtml;
    }

    public static synchronized String showPositions(String ip) {
        positions = getPositions.getPositions(6, ip);
        allAgvs = getPositions.getAgvs(8, ip);

        String buildInHtml = "<table>";
        for(AGVPosition pos: positions) {
            for(AGV agv: allAgvs){
                if(agv.getAgvID().equals(pos.agvID())){
                    buildInHtml = buildInHtml + "<tr class=\"active-row\">" +
                            "<td>" + pos.agvID() + "</td>" +
                            "<td>" + pos.lSquare() + "</td>" +
                            "<td>" + pos.wSquare() + "</td>" +
                            "<td>" + agv.getTaskStatus().toString() + "</td>";
                }
            }
        }

        buildInHtml = buildInHtml + "</table>";
        return buildInHtml;
    }


}
