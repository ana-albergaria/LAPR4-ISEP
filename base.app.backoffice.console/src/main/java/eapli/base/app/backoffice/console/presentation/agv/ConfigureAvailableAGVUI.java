package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.ConfigureAvailableAGVController;
import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigureAvailableAGVUI extends AbstractUI {

    private final ConfigureAvailableAGVController controller = new ConfigureAvailableAGVController();

    @Override
    protected boolean doShow() {
        Map<AGV, List<String>> availableAGVsInfo = controller.getAvailableAGVInformations();

        for(AGV agv : availableAGVsInfo.keySet()){
            for(String info : availableAGVsInfo.get(agv)) {
                System.out.println(info);
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure Available AGV(s)";
    }
}
