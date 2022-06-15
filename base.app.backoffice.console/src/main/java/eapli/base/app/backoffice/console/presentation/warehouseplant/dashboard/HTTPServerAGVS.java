package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.utils.CreateWarehouseMatrix;
import eapli.base.warehousemanagement.domain.*;
import org.springframework.scheduling.config.Task;

import javax.net.ssl.SSLServerSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class HTTPServerAGVS extends Thread{
    private static String ipAddress;
    private static final GetPositions getPositions = new GetPositions();
    static private final String BASE_FOLDER = "base.app.backoffice.console/src/main/java/eapli/base/app/backoffice/console/presentation/warehouseplant/dashboard/www";
    static private ServerSocket sock;
    static private SSLServerSocket socket;
    static  private Iterable<AGVPosition> positions;
    static private Map<Long, TaskStatus> allAgvsStatus;
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
        //SSLSocket cliSock1;

        //System.setProperty("javax.net.ssl.keyStore", "base.app.backoffice.console/src/main/java/eapli/base/app/backoffice/console/presentation/warehouseplant/dashboard/server.jks");
        //System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            //SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            //socket = (SSLServerSocket) sslF.createServerSocket(PORT);
            sock = new ServerSocket(PORT);
            System.out.println("HTTP Server connection opened.");
        }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            //cliSock1= (SSLSocket) socket.accept();
            HTTPAgvRequest req=new HTTPAgvRequest(cliSock, BASE_FOLDER, ipAddress);
            req.start();
        }
    }

    public static synchronized  String getMatrix(String ip){
        plant = getPositions.getPlant(10, ip);
        docks = getPositions.getDocks(11, ip);
        aisles = getPositions.getAisles(12, ip);
        positions = getPositions.getPositions(8, ip);
        String[][] matrix = CreateWarehouseMatrix.createAccordingWithSize(plant);
        CreateWarehouseMatrix.insertObstacles(matrix, docks, aisles, positions);

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
        positions = getPositions.getPositions(8, ip);
        allAgvsStatus = getPositions.getAgvStatus(6, ip);
        int counter = 0;

        String buildInHtml = "<table>";
        for(AGVPosition pos: positions) {
            for(Long id: allAgvsStatus.keySet()){
                if(Objects.equals(id, pos.agvID())){
                    buildInHtml = buildInHtml + "<tr class=\"active-row\">" +
                            "<td>" + pos.agvID() + "</td>" +
                            "<td>" + pos.lSquare() + "</td>" +
                            "<td>" + pos.wSquare() + "</td>" +
                            "<td>" + allAgvsStatus.get(id) + "</td>";
                }
            }

        }

        buildInHtml = buildInHtml + "</table>";
        return buildInHtml;
    }


}
