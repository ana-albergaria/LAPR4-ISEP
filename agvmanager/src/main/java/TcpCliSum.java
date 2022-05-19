import java.io.*;
import java.net.*;

class TcpCliSum {
    static InetAddress serverIP;
    static Socket sock;

    public static void main(String args[]) throws Exception {
        if(args.length!=1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1); }

        try { serverIP = InetAddress.getByName(args[0]); }
        catch(UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1); }

        try { sock = new Socket(serverIP, 9999); }
        catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1); }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());


        //preencher código necessário à US


        sock.close();
    }
}

