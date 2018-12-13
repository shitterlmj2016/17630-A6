/******************************************************************************************************************
 * File: Server
 * Course: 17630
 * Project: Assignment A6
 * Copyright: Copyright (c) 2018 Carnegie Mellon University
 * Versions:
 *   1.0 November 2018 - Initial write of assignment 6 (ajl).
 *
 * Description: This class illustrates how to use sockets to receive job request from the client job submission
 * simulator (JobSim.java). You can use the class to create your application. You may not deviate from the
 * communication protocol used here.
 *
 * Parameters: None
 *
 * Internal Methods:
 *   none
 *
 * External Dependencies:
 *   none
 ******************************************************************************************************************/


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

                    char[] tempArray = msg.toCharArray();
                    char jobName = tempArray[7];
                    int jobType = (int) jobName - 64;//Convert A-D to 1-4
                    f.received++;
                    g.setReceived(f.received);
                    g.setQueued(f.received-f.finished);
                    g.setArrivalRate((double)(System.currentTimeMillis()-startTime)/f.received/1000);

//                    System.out.println("[New] A new job " + jobName+" is Received!");
//                    System.out.println("[Statistic]: Total jobs received: "+count+"; waiting:"+(count-f.finished)+"; finished:"+f.finished+";");
//                    System.out.println("[Statistic]: Average arrive rate: "+ (System.currentTimeMillis()-startTime)/(double)count/1000+" seconds/job");

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
