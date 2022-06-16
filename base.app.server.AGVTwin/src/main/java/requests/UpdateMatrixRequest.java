package requests;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVTwinServerController;

import java.io.*;

public class UpdateMatrixRequest extends AGVTwinServerRequest{
    public UpdateMatrixRequest(final AGVTwinServerController agvTwinServerController,
                                final byte request,
                                final ObjectOutputStream sOutObject,
                                final DataInputStream sIn,
                                final DataOutputStream sOut,
                                final byte[] clientMessageUS,
                                final ObjectInputStream sInObject) {
        super(agvTwinServerController, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }
    @Override
    public void execute() {
        try {
            String[][] receivedMatrix = (String[][]) this.sInObject.readObject();

            this.sOutputObject.writeObject(receivedMatrix);
            this.sOutputObject.flush();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
