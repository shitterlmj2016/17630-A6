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
