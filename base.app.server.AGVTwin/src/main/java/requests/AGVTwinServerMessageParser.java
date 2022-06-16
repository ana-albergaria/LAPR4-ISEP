package requests;

import eapli.base.warehousemanagement.application.AGVTwinServerController;

import java.io.*;

public class AGVTwinServerMessageParser {
    private final AGVTwinServerController agvTwinServerController;

    public AGVTwinServerMessageParser(final AGVTwinServerController agvTwinServerController) {
        this.agvTwinServerController = agvTwinServerController;
    }

    public AGVTwinServerController getAgvTwinServerController() {
        return agvTwinServerController;
    }

    public AGVTwinServerRequest parse(final byte messageRequest,
                                         final ObjectOutputStream sOutObject,
                                         final DataInputStream sIn,
                                         final DataOutputStream sOut,
                                         final byte[] clientMessageUS,
                                         final ObjectInputStream sInObject) throws IOException {

        AGVTwinServerRequest request = null;

        if(messageRequest == 6) { //Get the AGVStatus
            request = new SendAGVStatusRequest(agvTwinServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(request == null)
            throw new UnsupportedOperationException("The request " + messageRequest + " is not valid - unable to parse.");

        return request;
    }
}
