package eapli.base.app.backoffice.console.presentation.warehouseplant.dashboard;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.warehousemanagement.domain.AGVPosition;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class DashboardUI {
    static private Socket sock;
    static private InetAddress serverIP;
    static private ObjectOutputStream sOut;
    static private ObjectInputStream sIn;
    static private ArrayList<AGVPosition> agvList = new ArrayList<>();

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();


    public static void main(String[] args) throws Exception {


    }
}
