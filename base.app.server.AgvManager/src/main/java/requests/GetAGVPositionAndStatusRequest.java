package requests;

import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.AGV;
import srv.TcpSrvAGVTwin;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GetAGVPositionAndStatusRequest extends AGVManagerServerRequest{
    private final int PORT = 2400;
    private final String IP_ADDRESS = "127.0.0.1";
    private Socket socket;
    public GetAGVPositionAndStatusRequest(final AGVManagerServerController ctrl,
                                          final byte request,
                                          final ObjectOutputStream sOutObject,
                                          final DataInputStream sIn,
                                          final DataOutputStream sOut,
                                          final byte[] clientMessageUS,
                                          final ObjectInputStream sInObject){
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public void execute() {
        //Iterable<AGV> agvsInTheSystem = this.agvManagerServerController.allAGVS();

        /*for(AGV agv : agvsInTheSystem){
            try {
                TcpSrvAGVTwin newAGV = new TcpSrvAGVTwin(agv, IP_ADDRESS, PORT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

        try{
            socket = new Socket(IP_ADDRESS, PORT);
        } catch (IOException e) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }
    }
}
