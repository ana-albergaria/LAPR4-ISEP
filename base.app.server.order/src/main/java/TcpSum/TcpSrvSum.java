package TcpSum;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

class TcpSrvSum {
    static ServerSocket sock;

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(10999); }
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

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

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

            Iterator<Product> products = productRepository.findAll().iterator();
            while(products.hasNext()) {
                Product element = products.next();
                System.out.println( element );
            }

            do {
                sum=0;
                do {
                    num=0; f=1; for(i=0;i<4;i++) {num=num+f*sIn.read(); f=f*256;}
                    sum=sum+num;
                }
                while(num>0);
                num=sum; for(i=0;i<4;i++) {sOut.write((byte)(num%256)); num=num/256; }
            }
            while(sum>0);

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();
        }
        catch(IOException ex) { System.out.println("IOException"); }
    }
}
