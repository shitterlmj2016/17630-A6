//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class JobSim {
    public JobSim() {
    }

    public static void main(String[] var0) throws IOException {
        boolean var3 = false;
        boolean var4 = false;
        String var5 = null;
        Random var6 = new Random();
        Socket var7 = new Socket("localhost", 9090);

        while(true) {
            int var2 = var6.nextInt(4) + 1;
            switch(var2) {
                case 1:
                    var5 = "ProductA";
                    break;
                case 2:
                    var5 = "ProductB";
                    break;
                case 3:
                    var5 = "ProductC";
                    break;
                case 4:
                    var5 = "ProductD";
            }

            System.out.println("Product String:: " + var5);

            try {
                PrintWriter var8 = new PrintWriter(var7.getOutputStream(), true);
                var8.println(var5);
            } catch (Exception var10) {
                System.out.println("Error writing to socket::" + var10);
                var7.close();
                System.exit(0);
            }

            int var11 = var6.nextInt(6) + 1;
            System.out.println("Inter-order delay: " + var11 + " seconds.");
            var11 *= 1000;

            try {
                Thread.sleep((long)var11);
            } catch (InterruptedException var9) {
                System.out.println("Error in sleep::" + var9);
                var7.close();
                System.exit(0);
            }
        }
    }
}
