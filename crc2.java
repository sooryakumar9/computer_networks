import java.util.Scanner;

public class crc2 {
    public static int t[] = new int[128], cs[] = new int[128], a, e, c, g[] = {1, 0, 1, 1};
    static final int n = g.length;

    static void xor() {
        for (c = 1; c < n; c++)
            cs[c] = ((cs[c] == g[c]) ? 0 : 1);
    }

    static void crc() {
        for (e = 0; e < n; e++)
            cs[e] = t[e];
        do {
            if (cs[0] == 1)
                xor();
            for (c = 0; c < n - 1; c++)
                cs[c] = cs[c + 1];
            cs[c] = t[e++];
        } while (e <= a + n - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of dataword:");
        a = in.nextInt();
        System.out.println("Enter dataword:");
        for (int i = 0; i < a; i++)
            t[i] = in.nextInt();
        for (e = a; e < a + n - 1; e++)
            t[e] = 0;
        System.out.println("Modified data word:");
        for (int i = 0; i < a + n - 1; i++)
            System.out.print(t[i]);
        System.out.println();
        crc();
        System.out.print("Checksum:");
        for (int i = 0; i < n - 1; i++)
            System.out.println(cs[i]);
        System.out.println();
        for (e = a; e < a + n - 1; e++)
            t[e] = cs[e - a];
        System.out.println("Final Codeword:");
        for (int i = 0; i < a + n - 1; i++)
            System.out.print(t[i]);
        System.out.println();
        System.out.println("Test error detection? 0(yes) 1(no):");
        e = in.nextInt();
        if (e == 0) {
            System.out.format("Enter position between %d and %d where error is to be introduced ", 0, a - 1);
            e = in.nextInt();
            t[e] = (t[e] == 0) ? 1 : 0;
            System.out.print("Dataword after error:");
            for (int i = 0; i < a; i++)
                System.out.print(t[i]);
            System.out.println();
        }
        crc();
        for (e = 0; (e < n - 1) && (cs[e] != 1); e++) ;
        if (e < n - 1)
            System.out.println("Error detected");
        else
            System.out.println("No error detected");
    }
}