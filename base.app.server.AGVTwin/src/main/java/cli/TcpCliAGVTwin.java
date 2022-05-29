package cli;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.validations.Preconditions;

import javax.swing.plaf.PanelUI;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TcpCliAGVTwin {

    static Socket sock;
    static InetAddress serverIP;
    static String address;

    //client: request
    //server: waiting for request + send response

    public TcpCliAGVTwin(String address){
        Preconditions.nonEmpty(address, "Server IPv4/IPv6 address or DNS name is required");
        this.address=address;
        new Thread(new TcpCliAGVTwinThread(address)).start();
    }

    public static void main(String args[]) {
        if(args.length!=1){
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }
        new Thread(new TcpCliAGVTwinThread(args[0])).start();

    }

}

class TcpCliAGVTwinThread implements Runnable {
    private String ip;

    public TcpCliAGVTwinThread(String ip) {
        this.ip = ip;
    }

    private Socket s;

    public TcpCliAGVTwinThread(Socket cli_s) {
        s = cli_s;
    }

    public void run() {

        InetAddress serverIP;


        Socket sock = null;

        try {
            serverIP = InetAddress.getByName(this.ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.ip);
            System.exit(1);
        }

        try {
            sock = new Socket(this.ip, 2807);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.ip + ":" + 2807);

        try {

            DataOutputStream sOutData = new DataOutputStream(sock.getOutputStream());
            DataInputStream sInData = new DataInputStream(sock.getInputStream());
            InetAddress clientIP = s.getInetAddress();
            DataInputStream sIn = new DataInputStream(s.getInputStream());

            //Mandar um pedido para o servido -> codigo: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(clienteMessage);
            sOutData.flush();

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = sInData.readNBytes(4);


            byte[] clientMessageUS = new byte[4];
            MessageUtils.readMessage(clientMessageUS, sIn);

            if (clientMessageUS[1] == 8) {


                String agvsToUpdate = MessageUtils.getDataFromMessage(clientMessageUS, sIn);

                /*List<AGV> agvsToChange = new LinkedList<>();

                for (AGV agv : taskRepository.findAllAGV()) {
                    if (Objects.equals(agv.getTaskStatus(), TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE))){
                        agvsToChange.add(agv);
                        //agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED));
                        //agvRepository.save(agv);
                    }
                }
                for (AGV agv : agvRepository.findAll()) {
                    if (taskRepository.findByAgv(agv)==null){
                        if (Objects.equals(agv.getAutonomyStatus(), AutonomyStatus.valueOf("0h"))) {
                            //agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.CHARGING));
                            agvsToChange.add(agv);
                        } else {
                            //agv.setTaskStatus(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE));
                            agvsToChange.add(agv);
                        }
                        //agvRepository.save(agv);
                    }
                }*/

            }


            if (serverMessage[1] == 2) {

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
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                System.out.println("==> ERROR: Falha a fechar o socket");
            }
        }
    }

}

