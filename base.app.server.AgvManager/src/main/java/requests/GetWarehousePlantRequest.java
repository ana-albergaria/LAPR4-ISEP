package requests;

import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.WarehousePlant;

import java.io.*;

public class GetWarehousePlantRequest extends AGVManagerServerRequest{

    public GetWarehousePlantRequest(final AGVManagerServerController ctrl,
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
        Iterable<WarehousePlant> warehousePlants = this.agvManagerServerController.warehousePlant();

        WarehousePlant warehousePlant = warehousePlants.iterator().next();

        try {
            this.sOutputObject.writeObject(warehousePlant);
            this.sOutputObject.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
