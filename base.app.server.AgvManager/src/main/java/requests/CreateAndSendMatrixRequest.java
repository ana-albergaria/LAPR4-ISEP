package requests;

import eapli.base.utils.CreateWarehouseMatrix;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.List;

public class CreateAndSendMatrixRequest extends AGVManagerServerRequest{
    private final int PORT = 2400;
    private final String IP_ADDRESS = "127.0.0.1";
    private SSLSocket socket;

    public CreateAndSendMatrixRequest(final AGVManagerServerController ctrl,
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
        Iterable<AGV> agvsInTheSystem = this.agvManagerServerController.allAGVS();
        Iterable<WarehousePlant> warehousePlants = this.agvManagerServerController.warehousePlant();
        List<AgvDock> agvDocks = (List<AgvDock>) this.agvManagerServerController.agvDocks();
        List<Aisle> aisles = (List<Aisle>) this.agvManagerServerController.aisles();
        List<AGVPosition> positions = (List<AGVPosition>) this.agvManagerServerController.positions();

        WarehousePlant warehousePlant = warehousePlants.iterator().next();

        String[][] matrix = CreateWarehouseMatrix.createAccordingWithSize(warehousePlant);

        CreateWarehouseMatrix.insertObstacles(matrix, agvDocks, aisles, positions);

        for(AGV agv : agvsInTheSystem){
            try{
                SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

                socket = (SSLSocket) sf.createSocket(IP_ADDRESS, PORT);

                socket.startHandshake();
                DataInputStream sIn = new DataInputStream(socket.getInputStream());
                DataOutputStream sOut = new DataOutputStream(socket.getOutputStream());

                boolean serverResponse = MessageUtils.testCommunicationWithServer(sOut, sIn);

                if(serverResponse){
                    MessageUtils.writeMessage((byte) 10, sOut);

                    byte[] willUpdateMatrix = new byte[4];
                    MessageUtils.readMessage(willUpdateMatrix, sIn);

                    if(willUpdateMatrix[1] == 11){
                        ObjectOutputStream matrixToUpdate = new ObjectOutputStream(socket.getOutputStream());

                        matrixToUpdate.writeObject(matrix);
                        matrixToUpdate.flush();

                        ObjectInputStream updatedMatrix = new ObjectInputStream(socket.getInputStream());
                        try {
                            matrix = (String[][]) updatedMatrix.readObject();
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

        try { // Send to Backoffice
            this.sOutputObject.writeObject(matrix);
            this.sOutputObject.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}