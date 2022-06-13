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

        // TODO Do NOT choose request 15 as it is a subrequest of request 14!

        AGVManagerServerRequest request = null;

        if(messageRequest == 6) {
            request = new GetAGVPositionAndStatusRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }
        if(messageRequest == 7) {
            request = new SentAGVPositionAndStatusRequest(agvManagerServerController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }
        if(messageRequest == 8) {

        }

        if(messageRequest == 9){

        }

        if(messageRequest == 10){

        }

        if(messageRequest == 11) {

        }

        if(request == null)
            throw new UnsupportedOperationException("The request " + messageRequest + " is not valid - unable to parse.");

        return request;
    }
}
