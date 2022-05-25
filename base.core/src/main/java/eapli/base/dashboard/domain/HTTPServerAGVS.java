package eapli.base.dashboard.domain;

import eapli.base.warehousemanagement.domain.AGVPosition;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HTTPServerAGVS {
    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static private ServerSocket sock;

    static final int PORT = 55090;

    public static void main(String[] args) throws IOException {
        Socket cliSock;

        if(args.length!=1) {
            System.out.println("Local port number required at the command line.");
            System.exit(1);
        }

        accessesCounter=0;

        try { sock = new ServerSocket(Integer.parseInt(args[0])); }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + args[0]);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            HTTPAgvRequest req=new HTTPAgvRequest(cliSock, BASE_FOLDER);
            req.start();
            incAccessesCounter();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED
    private static int accessesCounter;

    private static synchronized void incAccessesCounter() { accessesCounter++; }

    public static synchronized String showPositions( ) {
        StringBuilder s = new StringBuilder();

        List<AGVPosition> positions = new ArrayList<>();

        for(AGVPosition pos: positions) {
            s.append("<tr class=\"active-row\">" +
                    "<td>" + pos.getAGVID() + "</td>" +
                    "<td>" + pos.getLSquare() + "</td>" +
                    "<td>" + pos.getWSquare() + "</td>" +
                    "</tr>");
        }
        return s.toString();
    }


}
