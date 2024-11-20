import java.util.Scanner;
import java.net.*;

public class udpclient70 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DatagramSocket skt;

        try {
            skt = new DatagramSocket();
            InetAddress host = InetAddress.getByName("127.0.0.1"); // Server address
            int port = 3000; // Server port

            while (true) {
                System.out.print("Client: ");
                String msg = in.nextLine();
                byte[] b = msg.getBytes();

                // Send the message to the server
                DatagramPacket req = new DatagramPacket(b, b.length, host, port);
                skt.send(req);

                // Buffer to receive the server's reply
                byte[] buffer = new byte[1024];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                // Receive the server's reply
                skt.receive(reply);
                String response = new String(reply.getData(), 0, reply.getLength());
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}
