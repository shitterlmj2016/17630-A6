//17630-A6 Concurrency
//Andrew ID: xinchenh
//Name: Xincheng Huang
//Server class
//This class servers as the server and test harness

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {

        serverThread S = new serverThread();
        S.run();
    }

} // class

class serverThread extends Thread {

    public void run() {
        //Declare shared factory class and GUI panel class
        Factory f = new Factory();
        GUIPanel g=new GUIPanel();

        long startTime = System.currentTimeMillis();

        // We create a listener socket and wait for the client.
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9090);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Waiting for client...");

        try {
            // Here we accept a client and the print a message that we have connected.
            Socket s = listener.accept();
            System.out.println("Client connected...");

            while (true) {


                    // Here we read the job. You will get one of four strings from the client job
                    // simulator: ProductA, ProductB, ProductC, ProductD for each of the possible products.
                    // Once received, I print out the strings.

                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String msg = input.readLine();
                    //Convert from string to different job type
                    char[] tempArray = msg.toCharArray();
                    char jobName = tempArray[7];
                    int jobType = (int) jobName - 64;//Convert A-D to 1-4
                    f.received++;
                    //calculated different types of jobs
                    //updated on the GUI
                    g.setReceived(f.received);
                    g.setQueued(f.received-f.finished);
                    g.setArrivalRate((double)(System.currentTimeMillis()-startTime)/f.received/1000);



                    //Create different threads based on different types of jobs
                    if(jobType == 1)
                    {
                        productA job=new productA(f,g,f.received);
                        job.start();
                    }

                    if(jobType == 2)
                    {
                        productB job=new productB(f,g,f.received);
                        job.start();
                    }

                    if(jobType == 3)
                    {
                        productC job=new productC(f,g,f.received);
                        job.start();
                    }

                    if(jobType == 4)
                    {
                        productD job=new productD(f,g,f.received);
                        job.start();
                    }


                }



        } catch (Exception e) {

            System.out.println("Error connecting to client:: " + e);

        } // try
    }

}
