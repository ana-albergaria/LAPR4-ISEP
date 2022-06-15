package requests;

import eapli.base.warehousemanagement.application.AGVManagerServerController;

import java.io.*;

public class AGVManagerServerMessageParser {

    private final AGVManagerServerController agvManagerServerController;

    public AGVManagerServerMessageParser(final AGVManagerServerController agvManagerServerController) {
        this.agvManagerServerController = agvManagerServerController;
    }

    public AGVManagerServerController getAgvManagerServerController() {
        return agvManagerServerController;
    }

    public AGVManagerServerRequest parse(final byte messageRequest,
                                    final ObjectOutputStream sOutObject,
                                    final DataInputStream sIn,
                                    final DataOutputStream sOut,
                                    final byte[] clientMessageUS,
                                    final ObjectInputStream sInObject) throws IOException {

        AGVManagerServerRequest request = null;

        if(messageRequest == 6) { //Get the AGVStatus
            request = new GetAGVStatusRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 8) { //Get the AGVPosition
            request = new GetAGVPosition(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 10){ //Get the WarehousePlant
            request = new GetWarehousePlantRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 11) { //Get all the AGVDocks
            request = new GetAGVDocksRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 12){ //Get all the Aisles
            request = new GetAislesRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(request == null)
            throw new UnsupportedOperationException("The request " + messageRequest + " is not valid - unable to parse.");

        return request;
    }
}
