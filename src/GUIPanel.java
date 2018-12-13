//17630-A6 Concurrency
//Andrew ID: xinchenh
//Name: Xincheng Huang
//GUI class
//This class servers as the GUI

import javax.swing.*;
import java.awt.*;

public class GUIPanel {
    //Frame for GUI
    private JFrame frame;

    //Different labels represent different values of the system
    private JLabel tool1;
    private JLabel tool2;
    private JLabel tool3;

    private JLabel received;
    private JLabel queued;
    private JLabel finished;

    private JLabel productA;
    private JLabel productB;
    private JLabel productC;
    private JLabel productD;

    private JLabel arrivalRate;
    private JLabel speed;

    //Constructor
    public GUIPanel() {
        frame = new JFrame("Panel");
        frame.setLayout(new GridLayout(12, 1));

        tool1 = new JLabel("Tool 1: Free", JLabel.CENTER);
        tool1.setFont(new Font("", Font.PLAIN, 26));

        tool2 = new JLabel("Tool 2: Free", JLabel.CENTER);
        tool2.setFont(new Font("", Font.PLAIN, 26));

        tool3 = new JLabel("Tool 3: Free", JLabel.CENTER);
        tool3.setFont(new Font("", Font.PLAIN, 26));

        received = new JLabel("Total received: 0", JLabel.CENTER);
        received.setFont(new Font("", Font.PLAIN, 26));

        queued = new JLabel("Total queued: 0", JLabel.CENTER);
        queued.setFont(new Font("", Font.PLAIN, 26));

        finished = new JLabel("Total finished: 0", JLabel.CENTER);
        finished.setFont(new Font("", Font.PLAIN, 26));


        //Record who is using the tool
        // 0 - nobody (free)
        // 1 - A productA
        // 2 - A productB
        // 3 - A productC
        // 4 - A productD

        productA = new JLabel("Product A finished: 0", JLabel.CENTER);
        productA.setFont(new Font("", Font.PLAIN, 26));

        productB = new JLabel("Product B finished: 0", JLabel.CENTER);
        productB.setFont(new Font("", Font.PLAIN, 26));

        productC = new JLabel("Product C finished: 0", JLabel.CENTER);
        productC.setFont(new Font("", Font.PLAIN, 26));

        productD = new JLabel("Product D finished: 0", JLabel.CENTER);
        productD.setFont(new Font("", Font.PLAIN, 26));

        arrivalRate = new JLabel("Arrival rate: 0 seconds/job", JLabel.CENTER);
        arrivalRate.setFont(new Font("", Font.PLAIN, 26));

        speed = new JLabel("Producing speed: 0 seconds/job", JLabel.CENTER);
        speed.setFont(new Font("", Font.PLAIN, 26));

        frame.add(tool1);
        frame.add(tool2);
        frame.add(tool3);

        frame.add(received);
        frame.add(finished);
        frame.add(queued);
        frame.add(productA);
        frame.add(productB);
        frame.add(productC);
        frame.add(productD);

        frame.add(arrivalRate);
        frame.add(speed);

        frame.setBackground(Color.WHITE);

        Dimension dim = new Dimension();

        dim.setSize(1024, 768);
        frame.setSize(dim);
        frame.setVisible(true);
    }

    //Test functions

    //Setters
    public void setTool1(String job) {
        tool1.setText("Tool 1: " + job);
    }

    public void setTool2(String job) {
        tool2.setText("Tool 2: " + job);
    }

    public void setTool3(String job) {
        tool3.setText("Tool 3: " + job);
    }

    public void setReceived(int number) {
        String text = "Total received: " + String.valueOf(number);
        received.setText(text);
    }

    public void setQueued(int number) {
        String text = "Total queued: " + String.valueOf(number);
        queued.setText(text);
    }

    public void setFinished(int number) {
        String text = "Total finished: " + String.valueOf(number);
        finished.setText(text);
    }

    public void setProductA(int number) {
        String text = "Product A finished: " + String.valueOf(number);
        productA.setText(text);
    }

    public void setProductB(int number) {
        String text = "Product B finished: " + String.valueOf(number);
        productB.setText(text);
    }

    public void setProductC(int number) {
        String text = "Product C finished: " + String.valueOf(number);
        productC.setText(text);
    }

    public void setProductD(int number) {
        String text = "Product D finished: " + String.valueOf(number);
        productD.setText(text);
    }

    public void setArrivalRate(double number) {
        String text = "Arrival rate: " + String.valueOf(number)+" seconds/job";
        arrivalRate.setText(text);
    }

    public void setSpeed(double number) {
        String text = "Producing speed: " + String.valueOf(number)+" seconds/job";
        speed.setText(text);
    }
}
