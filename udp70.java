import java.util.Scanner;
import java.net.*;

public class udp70 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DatagramSocket skt = null;
        try {
            skt = new DatagramSocket(3000);
            System.out.println("Server is ready");
            while (true) {
                byte buffer[] = new byte[1024];
                DatagramPacket req = new DatagramPacket(buffer, buffer.length);
                skt.receive(req);
                String msg = new String(req.getData(), 0, req.getLength());
                System.out.println("Client: " + msg);
                System.out.print("Server: ");
                String m = in.nextLine();
                byte sendMsg[] = m.getBytes();
                DatagramPacket reply = new DatagramPacket(
                    sendMsg, sendMsg.length, req.getAddress(), req.getPort()
                );
                skt.send(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (skt != null && !skt.isClosed()) {
                skt.close();
            }
            in.close();
        }
    }
}