package control_system;

import eapli.base.warehousemanagement.domain.AGV;

public class ControlSystem {
    private final Double AGV_SPEED = 1.00;
    private AGV currentAGV;

    public ControlSystem(AGV agv){
        this.currentAGV=agv;
    }
}
