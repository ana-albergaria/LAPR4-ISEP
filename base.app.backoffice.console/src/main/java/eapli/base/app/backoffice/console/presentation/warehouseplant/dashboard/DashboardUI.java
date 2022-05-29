package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.dashboard.application.DashboardController;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class DashboardUI extends AbstractUI {
    private final DashboardController controller = new DashboardController();

    @Override
    protected boolean doShow() {
        //this.controller.getAgvs(8);
        String ipAddressOption = Console.readLine("Do you want to connect to a Local Server or an Cloud Server? (Local | Cloud)");
        String ipAddress = "";

        if(ipAddressOption.equalsIgnoreCase("Local") || ipAddressOption.equalsIgnoreCase("Local Server")){
            ipAddress = "127.0.0.1";
        }else if(ipAddressOption.equalsIgnoreCase("Cloud") || ipAddressOption.equalsIgnoreCase("Cloud Server")){
            ipAddress = Console.readLine("What is the Cloud Server's IP?");
        }

        this.controller.getPositions(6, ipAddress);
        //this.controller.getAgvs(8, ipAddress);
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
