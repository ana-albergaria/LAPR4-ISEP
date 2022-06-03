package cli;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.io.util.Console;
import eapli.framework.validations.Preconditions;

import javax.swing.plaf.PanelUI;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TcpCliAGVTwin {

    //client: request
    //server: waiting for request + send response

    static InetAddress serverIP;
    static Socket sock;

    static String address;

    public TcpCliAGVTwin(String address){
        Preconditions.nonEmpty(address, "Server IPv4/IPv6 address or DNS name is required");
        this.address=address;
        new Thread(new TcpCliAGVTwinThread(address)).start();
    }

    public static void main(String args[]) {
        String ipAddressOption = eapli.framework.io.util.Console.readLine("Do you want to connect to a Local Server or an Cloud Server? (Local | Cloud)");
        String ipAddress = "";

        if(ipAddressOption.equalsIgnoreCase("Local") || ipAddressOption.equalsIgnoreCase("Local Server")){
            ipAddress = "127.0.0.1";
        }else if(ipAddressOption.equalsIgnoreCase("Cloud") || ipAddressOption.equalsIgnoreCase("Cloud Server")){
            ipAddress = Console.readLine("What is the Cloud Server's IP?");
        }

        if(args.length!=1){
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        new Thread(new TcpCliAGVTwinThread(ipAddress)).start();

    }

}

class TcpCliAGVTwinThread implements Runnable {
    private String ip;

    public TcpCliAGVTwinThread(String ip) {
        this.ip = ip;
    }

    public void run() {

        InetAddress serverIP = null;
        Socket sock = null;

        try {
            serverIP = InetAddress.getByName(this.ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.ip);
            System.exit(1);
        }

        try {
            sock = new Socket(this.ip, 3700);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.ip + ", port:" + 3700);

        try {

            DataOutputStream sOutData = new DataOutputStream(sock.getOutputStream());
            DataInputStream sInData = new DataInputStream(sock.getInputStream());

            //Mandar um pedido para o servidor -> codigo: 0 (Teste)
            byte[] testMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(testMessage);
            sOutData.flush();

            byte[] testResponse = sInData.readNBytes(4);

            //Esperar a resposta do servidor a dizer que entendeu a mensagem

            if (testResponse[1] == 2) {

                //=========================================================
                //>>>>>>> US5001 e US5002

                byte[] optionMessage = {(byte) 0, (byte) 7, (byte) 0, (byte) 0};
                sOutData.write(optionMessage);
                sOutData.flush();

                List<AGV> agvsToUpdate;

                ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());

                agvsToUpdate = (List<AGV>) sInObject.readObject(); //input (US5001)
                updateAgvStatus(agvsToUpdate);

                sOutObject.writeObject(agvsToUpdate);
                sOutObject.flush(); //output (US5002)

                //>>>>>>> FIM DA US5001 e US5002
                //=========================================================

                //Mandar um pedido para o servidor -> codigo: 1 (Fim)
                byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                sOutData.write(clienteMessageEnd);
                sOutData.flush();

                byte[] serverMessageEnd = sInData.readNBytes(4);
                if (serverMessageEnd[1] == 2) {
                    sock.close();
                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                }

            } else {
                System.out.println("==> ERROR: Erro no pacote do Servidor");
            }
        } catch (IOException e) {
            System.out.println("==> ERROR: Falha durante a troca de informação com o server");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                System.out.println("==> ERROR: Falha a fechar o socket");
            }
        }
    }

    private void updateAgvStatus(Iterable<AGV> agvs){
        for (AGV agv: agvs) {
            agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
        }
    }

}

