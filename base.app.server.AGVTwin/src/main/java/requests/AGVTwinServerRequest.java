package requests;

import eapli.base.warehousemanagement.application.AGVTwinServerController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class AGVTwinServerRequest {
    protected final byte request;
    protected final AGVTwinServerController agvTwinServerController;
    protected final ObjectOutputStream sOutputObject;
    protected final DataInputStream sIn;
    protected final DataOutputStream sOut;
    protected byte[] clientMessageUS;
    protected final ObjectInputStream sInObject;

    protected AGVTwinServerRequest(final AGVTwinServerController agvTwinServerController,
                                      final byte request,
                                      final ObjectOutputStream sOutObject,
                                      final DataInputStream sIn,
                                      final DataOutputStream sOut,
                                      final byte[] clientMessageUS,
                                      final ObjectInputStream sInObject) {
        this.request = request;
        this.agvTwinServerController = agvTwinServerController;
        this.sOutputObject = sOutObject;
        this.sIn = sIn;
        this.sOut = sOut;
        this.clientMessageUS = clientMessageUS;
        this.sInObject = sInObject;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract void execute();
}
