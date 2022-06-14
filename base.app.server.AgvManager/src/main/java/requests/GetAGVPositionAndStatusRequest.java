package requests;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import srv.TcpSrvAGVTwin;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

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
    public Iterable<Object> execute() {
        List<Object> agvStatusList = new LinkedList<>();
        Iterable<AGV> agvsInTheSystem = this.agvManagerServerController.allAGVS();

        for(AGV agv : agvsInTheSystem){
            try{
                socket = new Socket(IP_ADDRESS, PORT);
                DataInputStream sIn = new DataInputStream(socket.getInputStream());
                DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());

                boolean serverResponse = MessageUtils.testCommunicationWithServer(sOut, sIn);

                if(serverResponse){
                    MessageUtils.writeMessage((byte) 6, sOut);

                    byte[] wantsToReceiveAGV = new byte[4];
                    MessageUtils.readMessage(wantsToReceiveAGV, sIn);

                    if(wantsToReceiveAGV[1] == 7){
                        ObjectOutputStream sendAGV = new ObjectOutputStream(socket.getOutputStream());

                        sendAGV.writeObject(agv);
                        sendAGV.flush();

                        ObjectInputStream receivedAgvStatus = new ObjectInputStream(socket.getInputStream());
                        try {
                            TaskStatus agvStatus = (TaskStatus) receivedAgvStatus.readObject();
                            agvStatusList.add(agvStatus);
                        }catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    MessageUtils.writeMessage((byte) 1, sOut);
                }
            } catch (IOException e) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }
        }

        return agvStatusList;
    }
}
