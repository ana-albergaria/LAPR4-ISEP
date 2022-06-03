package cli;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.io.util.Console;
import eapli.framework.validations.Preconditions;

import javax.net.ssl.*;
import javax.swing.plaf.PanelUI;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
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

        if (ipAddressOption.equalsIgnoreCase("Local") || ipAddressOption.equalsIgnoreCase("Local Server")) {
            ipAddress = "127.0.0.1";
        } else if (ipAddressOption.equalsIgnoreCase("Cloud") || ipAddressOption.equalsIgnoreCase("Cloud Server")) {
            ipAddress = Console.readLine("What is the Cloud Server's IP?");
        }

        if (args.length != 1) {
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
        SSLSocket sock = null;

        try {
            serverIP = InetAddress.getByName(this.ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.ip);
            System.exit(1);
        }

        try {
            System.setProperty("javax.net.debug", "all");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String password = "aabbcc";
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("cli/client-certificate.p12");
            keyStore.load(inputStream, password.toCharArray());

            // TrustManagerFactory ()
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            String password2 = "abcdefg";
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            InputStream inputStream1 = new FileInputStream("base.app.server.AgvManager/src/main/resources/srv/server-certificate.p12");
            trustStore.load(inputStream1, password2.toCharArray());
            trustManagerFactory.init(trustStore);
            X509TrustManager x509TrustManager = null;
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    break;
                }
            }

            if (x509TrustManager == null) throw new NullPointerException();

            // KeyManagerFactory ()
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            keyManagerFactory.init(keyStore, password.toCharArray());
            X509KeyManager x509KeyManager = null;
            for (KeyManager keyManager : keyManagerFactory.getKeyManagers()) {
                if (keyManager instanceof X509KeyManager) {
                    x509KeyManager = (X509KeyManager) keyManager;
                    break;
                }
            }
            if (x509KeyManager == null) throw new NullPointerException();

            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            sock = (SSLSocket) socketFactory.createSocket(this.ip, 3700);
            sock.setEnabledProtocols(new String[]{"TLSv1.2"});

            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }

        } catch (IOException | KeyStoreException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
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

                //>>>>>>> FAZER US5002
                byte[] optionMessage = {(byte) 0, (byte) 7, (byte) 0, (byte) 0};
                sOutData.write(optionMessage);
                sOutData.flush();
                //1. enviar sinal ao agv manager

                //...
                //4. fazer update dos agvs
                List<AGV> agvsToUpdate;

                ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());

                agvsToUpdate = (List<AGV>) sInObject.readObject();
                updateAgvStatus(agvsToUpdate);

                //5. enviar mensagem ao agv manager server a dizer
                //que os status foram alterados com sucesso
                //...
                sOutObject.writeObject(agvsToUpdate);
                sOutObject.flush();
                //>>>>>>> FIM DA US5002

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

