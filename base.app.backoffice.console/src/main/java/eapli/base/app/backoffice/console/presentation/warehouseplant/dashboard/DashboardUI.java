package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.dashboard.application.DashboardController;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class DashboardUI extends AbstractUI {
    private final DashboardController controller = new DashboardController();

    @Override
    protected boolean doShow() {
        this.controller.getPositions(6);
        //this.controller.getAgvs(8);
        this.controller.showDashboard();

        URI uri;
        try {
            uri = new URI("http:127.0.0.1:55090/");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "AGV Position Dashboard";
    }
}
