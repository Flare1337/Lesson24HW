import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        try {
            new Main().doSomeTasks();
        } catch (NegativeNumberOfSecondsException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doSomeTasks() throws NegativeNumberOfSecondsException, ExecutionException, InterruptedException {
        FactorialNumberComputeTask factorialComputeTask = new FactorialNumberComputeTask(5);
        FibonacciNumberComputeTask fibonacciComputeTask = new FibonacciNumberComputeTask(5);
        CopyFileTask copyFileTask = new CopyFileTask("E:\\HW\\text111.txt","E:\\HW\\text222.txt");
        StopwatchTask stopwatchTask = new StopwatchTask(60);

        ExecutorService workStealingPoolExecutor = Executors.newFixedThreadPool(4);

        List<Future<Integer>> tasks = new ArrayList<>();
        tasks.add(workStealingPoolExecutor.submit(factorialComputeTask));
        tasks.add(workStealingPoolExecutor.submit(fibonacciComputeTask));
        tasks.add(workStealingPoolExecutor.submit(copyFileTask));
        tasks.add(workStealingPoolExecutor.submit(stopwatchTask));


        Future<Integer> integerFuture = null;
        var counter = 0;
        var counterTwo = tasks.size();

        while (counterTwo > 0) {
            integerFuture = tasks.get(counter);
            counter++;
            try {
                System.out.println();
                System.out.printf("The value of the task %d: %d%n",counter ,integerFuture.get(5, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.out.printf("The task %d has been canceled! The value of the task: %d%n", counter, integerFuture.get());
                integerFuture.cancel(true);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            --counterTwo;
        }
    }
}
