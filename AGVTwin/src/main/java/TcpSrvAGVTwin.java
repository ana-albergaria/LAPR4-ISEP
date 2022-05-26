import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class TcpSrvAGVTwin {
    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try{
            sock = new ServerSocket(2400);
            System.out.println("Server connection opened.");
        }
        catch(IOException ex){
            System.out.println("Failed to open server socket.");
            System.exit(1);
        }

        while(true){
            cliSock=sock.accept();
            new Thread(new TcpSrvAGVTwinThread(cliSock)).start();
        }
    }
}



class TcpSrvAGVTwinThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpSrvAGVTwinThread(Socket cli_s){
        s=cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try{
            clientIP=this.s.getInetAddress();
            System.out.println("[INFO] Nova conexão de cliente: " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + ".");
            sOut = new DataOutputStream(this.s.getOutputStream());
            sIn = new DataInputStream(this.s.getInputStream());

            System.out.println("I am client " + clientIP.getHostAddress() + " and I am finally connected to the server " + s.getLocalAddress() + "! :)");

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Problemas a Fechar o Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Fechado.\n\n");
        }
    }
}
