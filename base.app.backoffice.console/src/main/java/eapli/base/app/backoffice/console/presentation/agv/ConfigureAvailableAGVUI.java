package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.warehousemanagement.application.ConfigureAvailableAGVController;
import eapli.base.warehousemanagement.application.RegisterAGVController;
import eapli.base.warehousemanagement.domain.AgvDock;
import eapli.base.warehousemanagement.domain.WarehousePlant;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class ConfigureAvailableAGVUI extends AbstractUI {

    private final ConfigureAvailableAGVController controller = new ConfigureAvailableAGVController();

    @Override
    protected boolean doShow() {
        List<String> informations = new ArrayList<>();

        informations = controller.getAvailableAGVInformations();

        for(String info : informations){
            System.out.println(info);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure Available AGV(s)";
    }
}
