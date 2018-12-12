import java.util.concurrent.Semaphore;

public class Factory {
    Semaphore tool1;
    Semaphore tool2;
    Semaphore tool3;

    int started;//Job that has been started
    int finished;//Job that has been finished
    //started - finished = queued

    //Count how many products of each kind have been finished
    int productA;
    int productB;
    int productC;
    int productD;

    //Record who is using the tool
    // 0 - nobody (free)
    // 1 - A productA
    // 2 - A productB
    // 3 - A productC
    // 4 - A productD

    int useTool1;
    int useTool2;
    int useTool3;

    //Still need a timer to calculate the throughput
    long startTime = System.currentTimeMillis();

    //Constructor
    public Factory() {
        this.tool1 = new Semaphore(1);
        this.tool2 = new Semaphore(1);
        this.tool3 = new Semaphore(1);
        this.started = 0;
        this.finished = 0;
        this.productA = 0;
        this.productB = 0;
        this.productC = 0;
        this.productD = 0;
        this.useTool1 = 0;
        this.useTool2 = 0;
        this.useTool3 = 0;
    }
}

class productA extends Thread {

    Factory factory;
    int id;

    productA(Factory f) {
        factory = f;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section
        id = factory.started++;
        System.out.println("[Start] Product " + id + "-A Started!");
        System.out.println("[Tool 1] Product " + id + "-A is using Tool1...");

        try {
            Thread.sleep(10000);//10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore

        factory.tool1.release();

        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("[Tool 1] Product " + id + "-A is using Tool2...");

        try {
            Thread.sleep(8000);//8
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Set result and print

        factory.finished++;
        factory.productA++;

        System.out.println("[Completed] Product " + id + "-A is completed!");
        System.out.println("[Statistic] Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);

        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("[Speed] Average speed: "+ averageTime + " seconds/job");

        factory.tool2.release();
    }

}

class productB extends Thread {

    Factory factory;
    int id;

    productB(Factory f) {
        factory = f;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section
        id = factory.started++;
        System.out.println("[Start] Product " + id + "-B Started!");
        System.out.println("[Tool 2] Product " + id + "-B is using Tool2...");

        try {
            Thread.sleep(12000);//12s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore

        factory.tool2.release();

        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Tool 3] Product " + id + "-B is using Tool3...");
        try {
            Thread.sleep(6000);//6
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productB++;
        System.out.println("[Completed] Product " + id + "-B is completed!");
        System.out.println("[Statistic] Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("[Speed] Average speed: "+ averageTime + " seconds/job");
        factory.tool3.release();
    }

}

class productC extends Thread {

    Factory factory;
    int id;

    productC(Factory f) {
        factory = f;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section
        id = factory.started++;
        System.out.println("[Start] Product " + id + "-C Started!");
        System.out.println("[Tool 1] Product " + id + "-C is using Tool1...");

        try {
            Thread.sleep(11000);//12s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore

        factory.tool1.release();

        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[Tool 3] Product " + id + "-C is using Tool3...");

        try {
            Thread.sleep(9000);//9
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productC++;
        System.out.println("[Completed] Product " + id + "-C is completed!");
        System.out.println("[Statistic] Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("[Speed] Average speed: "+ averageTime + " seconds/job");
        factory.tool3.release();
    }

}


class productD extends Thread {

    Factory factory;
    int id;

    productD(Factory f) {
        factory = f;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.tool1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Critical section
        id = factory.started++;
        System.out.println("[Start] Product " + id + "-D Started!");
        System.out.println("[Tool 1] Product " + id + "-D is using Tool1...");

        try {
            Thread.sleep(7000);//7s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore

        factory.tool1.release();


        //acquire tool 2
        try {
            factory.tool2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Tool 2] Product " + id + "-D is using Tool2...");

        try {
            Thread.sleep(6000);//6s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical Section Ends
        //Release Semaphore

        factory.tool2.release();


        try {
            factory.tool3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Tool 3] Product " + id + "-D is using Tool3...");

        try {
            Thread.sleep(5000);//5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productD++;
        System.out.println("[Completed] Product " + id + "-D is completed!");
        System.out.println("[Statistic] Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("[Speed] Average speed: "+ averageTime + " seconds/job");
        factory.tool3.release();
    }

}

