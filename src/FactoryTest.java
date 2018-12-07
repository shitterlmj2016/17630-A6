public class FactoryTest {

    public static void main(String[] args) {
        Factory f = new Factory();
        productA a1 = new productA(f);
        productA a2 = new productA(f);

        //
        productB b1 = new productB(f);
        productB b2 = new productB(f);


        productC c1 = new productC(f);
        productD d1 = new productD(f);

        a1.start();
        a2.start();
        b1.start();
        b2.start();
        c1.start();
        d1.start();

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
        System.out.println("Product " + id + " A Started!");


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


        try {
            Thread.sleep(8000);//8
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productA++;

        System.out.println("Product " + id + " A finished!");
        System.out.println("Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);

        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("Average time cost per job: "+ averageTime);

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
        System.out.println("Product " + id + " B Started!");


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


        try {
            Thread.sleep(6000);//6
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productB++;
        System.out.println("Product " + id + " B finished!");
        System.out.println("Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("Average time cost per job: "+ averageTime);
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
        System.out.println("Product " + id + " C Started!");


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


        try {
            Thread.sleep(9000);//6
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productC++;
        System.out.println("Product " + id + " C finished!");
        System.out.println("Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("Average time cost per job: "+ averageTime);
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
        System.out.println("Product " + id + " D Started!");


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


        try {
            Thread.sleep(5000);//5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print

        factory.finished++;
        factory.productD++;
        System.out.println("Product " + id + " D finished!");
        System.out.println("Total finished products: " + factory.finished + "; A: " + factory.productA + "; B: " + factory.productB + "; C: " + factory.productC + "; D: " + factory.productD);
        long totalTime=System.currentTimeMillis()-factory.startTime;
        double averageTime=(double)totalTime/factory.finished/1000;
        System.out.println("Average time cost per job: "+ averageTime);
        factory.tool3.release();
    }

}
