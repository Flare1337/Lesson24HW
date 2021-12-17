import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FibonacciNumberComputeTask implements Callable<Integer> {
    public int numberToCalculate;

    public FibonacciNumberComputeTask(int numberToCalculate) {
        this.numberToCalculate = numberToCalculate;
    }

    @Override
    public Integer call() throws InterruptedException {
        int previous = 0;
        int current = 1;
        int newCurrent = 0;
        System.out.println("Beginning of computation");
        for (int num = 0; num <= 10 && !Thread.currentThread().isInterrupted(); num++) {
            System.out.printf("Previous n %d%nPrevious current n %d%nNew fibonacci value: %d%n%n",
                                previous, current , (newCurrent = previous + current));
            previous = current;
            current = newCurrent;
            TimeUnit.SECONDS.sleep(1);
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Whoops, something went wrong! The computing of the fibonacci sequence has been interrupted...");
        }

        System.out.println("End of calculation, final result: " + current);
        return current;
    }
}