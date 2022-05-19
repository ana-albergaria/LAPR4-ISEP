import java.io.*;
import java.net.*;

class TcpSrvSum {
    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(9999); }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            new Thread(new TcpSrvSumThread(cliSock)).start();
        }
    }
}



class TcpSrvSumThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpSrvSumThread(Socket cli_s) { s=cli_s;}

    public void run() {
        long f,i,num,sum;
        InetAddress clientIP;

        clientIP=s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());
        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            //preencher código necessário à US

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();
        }
        catch(IOException ex) { System.out.println("IOException"); }
    }
}



