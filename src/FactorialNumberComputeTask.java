import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialNumberComputeTask implements Callable<Integer> {
    public int factorial;

    public FactorialNumberComputeTask(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public Integer call() throws InterruptedException {
        int result = 1;
        System.out.println("Factorial is started to calculate");
        for (int num = 1; num <= 7 && !Thread.currentThread().isInterrupted(); num++) {
            System.out.println("Current value of factorial: " + (result *= num));
            TimeUnit.SECONDS.sleep(1);
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Whoops, something went wrong! The computing of the factorial has been interrupted...");
        }
        System.out.println("Calculating is finished, value: " + result);
        return result;
    }
}
