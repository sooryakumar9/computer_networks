import java.net.*;
import java.io.*;
import java.util.Scanner;

public class tcpclient {
    public static void main(String[] args) {
        Socket s;
        while (true) {
            try {
                s = new Socket("192.168.68.10", 2000);
                OutputStream ostream = s.getOutputStream();
                System.out.println("Enter filename");
                Scanner input = new Scanner(System.in);
                String fname = input.nextLine();
                if(fname == "END"){
                    System.out.println("Ending...");
                    input.close();
                    System.exit(0);
                }
                PrintWriter pwrite = new PrintWriter(ostream, true);
                pwrite.println(fname);
                
                InputStream istream = s.getInputStream();
                Scanner cRead = new Scanner(new InputStreamReader(istream));
                
                if (cRead.hasNext()) {
                    while (cRead.hasNext()) {
                        System.out.println(cRead.nextLine());
                    }
                } else {
                    System.out.println("No content received from server.");
                }
                pwrite.close();
                cRead.close();
                s.close();

            } catch (Exception e) {
                System.out.println("File Reading Ended...");
                System.exit(0);
            }
        }
    }
}