package requests;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVManagerServerController;

import java.io.*;

public class SentAGVPositionAndStatusRequest extends AGVManagerServerRequest{

    public SentAGVPositionAndStatusRequest(final AGVManagerServerController ctrl,
                                           final byte request,
                                           final ObjectOutputStream sOutObject,
                                           final DataInputStream sIn,
                                           final DataOutputStream sOut,
                                           final byte[] clientMessageUS,
                                           final ObjectInputStream sInObject) {
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public Iterable<Object> execute() {
        try {
            String status = MessageUtils.getDataFromMessage(clientMessageUS, sIn);
            MessageUtils.writeMessageWithData((byte) 7, status, sOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
