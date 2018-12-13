//17630-A6 Concurrency
//Andrew ID: xinchenh
//Name: Xincheng Huang
//Factory class
//This class provides different semaphores (tools)
//It also servers as the recorder of each thread

import java.util.concurrent.Semaphore;

public class Factory {
    Semaphore tool1;
    Semaphore tool2;
    Semaphore tool3;

    int received;//Job that has been received

    int finished;//Job that has been finished
    //started - finished = queued

    //Count how many products of each kind have been finished
    int productA;
    int productB;
    int productC;
    int productD;


    //Still need a timer to calculate the throughput
    long startTime = System.currentTimeMillis();

    //Constructor
    public Factory() {
        this.tool1 = new Semaphore(1);
        this.tool2 = new Semaphore(1);
        this.tool3 = new Semaphore(1);

        this.finished = 0;
        this.productA = 0;
        this.productB = 0;
        this.productC = 0;
        this.productD = 0;
        this.received = 0;

    }
}

class productA extends Thread {

    Factory factory;
    GUIPanel p;
    int id;

    productA(Factory f, GUIPanel p, int id) {
        factory = f;
        this.p = p;
        this.id = id;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section

        p.setTool1(String.valueOf(id) + "-A");

        try {
            Thread.sleep(10000);//10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore
        p.setTool1("Free");
        factory.tool1.release();

        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p.setTool2(String.valueOf(id) + "-A");


        try {
            Thread.sleep(8000);//8
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.setTool2("Free");
        //Set result and print

        factory.finished++;
        factory.productA++;


        p.setFinished(factory.finished);
        p.setProductA(factory.productA);
        p.setQueued(factory.received - factory.finished);

        long totalTime = System.currentTimeMillis() - factory.startTime;
        double averageTime = (double) totalTime / factory.finished / 1000;
        p.setSpeed(averageTime);
        factory.tool2.release();

    }

}

class productB extends Thread {

    Factory factory;
    GUIPanel g;
    int id;

    productB(Factory f, GUIPanel g, int id) {
        factory = f;
        this.g = g;
        this.id = id;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section

        g.setTool2(String.valueOf(id) + "-B");
        try {
            Thread.sleep(12000);//12s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setTool2("Free");
        //Critical Section Ends
        //Release Semaphore

        factory.tool2.release();

        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setTool3(String.valueOf(id) + "-B");
        try {
            Thread.sleep(6000);//6
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setTool3("Free");

        //Set result and print

        factory.finished++;
        factory.productB++;

        g.setFinished(factory.finished);
        g.setProductB(factory.productB);
        g.setQueued(factory.received - factory.finished);

        long totalTime = System.currentTimeMillis() - factory.startTime;
        double averageTime = (double) totalTime / factory.finished / 1000;
        g.setSpeed(averageTime);
        factory.tool3.release();
    }

}

class productC extends Thread {

    Factory factory;
    GUIPanel g;
    int id;

    productC(Factory f, GUIPanel g, int id) {
        factory = f;
        this.g = g;
        this.id = id;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section
        g.setTool1(String.valueOf(id) + "-C");

        try {
            Thread.sleep(11000);//12s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setTool1("Free");

        //Critical Section Ends
        //Release Semaphore

        factory.tool1.release();

        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setTool3(String.valueOf(id) + "-C");
        try {
            Thread.sleep(9000);//9
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setTool3("Free");


        //Set result and print

        factory.finished++;
        factory.productC++;
        g.setFinished(factory.finished);
        g.setProductC(factory.productC);
        g.setQueued(factory.received - factory.finished);
        long totalTime = System.currentTimeMillis() - factory.startTime;
        double averageTime = (double) totalTime / factory.finished / 1000;
        g.setSpeed(averageTime);
        factory.tool3.release();
    }

}


class productD extends Thread {

    Factory factory;
    int id;
    GUIPanel g;

    productD(Factory f, GUIPanel g, int id) {
        factory = f;
        this.g = g;
        this.id = id;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section

        g.setTool1(String.valueOf(id) + "-D");

        try {
            Thread.sleep(7000);//7s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore
        g.setTool1("Free");
        factory.tool1.release();


        //acquire tool 2
        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setTool2((String.valueOf(id) + "-D"));
        try {
            Thread.sleep(6000);//6s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore
        g.setTool2("Free");
        factory.tool2.release();


        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setTool3(String.valueOf(id) + "-D");
        try {
            Thread.sleep(5000);//5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setTool3("Free");
        //Set result and print

        factory.finished++;
        factory.productD++;


        g.setFinished(factory.finished);
        g.setProductD(factory.productD);
        g.setQueued(factory.received-factory.finished);

        long totalTime = System.currentTimeMillis() - factory.startTime;
        double averageTime = (double) totalTime / factory.finished / 1000;
        g.setSpeed(averageTime);
        factory.tool3.release();
    }

}

