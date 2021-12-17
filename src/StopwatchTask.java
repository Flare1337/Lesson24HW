import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class StopwatchTask implements Callable<Integer> {
    public int endTimeInSeconds;
    public int timeInSeconds;

    public StopwatchTask(int timeInSeconds) throws NegativeNumberOfSecondsException {
        this.timeInSeconds = timeInSeconds;
        if (timeInSeconds < 0) {
            throw new NegativeNumberOfSecondsException("The seconds value should be 0 or bigger",
                                                        new IllegalArgumentException());
        }
    }

    @Override
    public Integer call() throws InterruptedException {
        System.out.println("The stopwatch started to work!");
        while (timeInSeconds >= 0 && !Thread.currentThread().isInterrupted()) {
            if (endTimeInSeconds > 0 && timeInSeconds == 0) {
                endTimeInSeconds = 0;
                break;
            }
            if (timeInSeconds == 0) {
                endTimeInSeconds = 0;
                System.out.println("We've just started, huh");
                break;
            }
            if (timeInSeconds > 60) {
                break;
            }
            System.out.println("Remains seconds: " + timeInSeconds);
            timeInSeconds--;
            endTimeInSeconds++;
            TimeUnit.SECONDS.sleep(1);
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Whoops, something went wrong! The countdown was interrupted");
        }

        System.out.println("Total work time: " + endTimeInSeconds);
        return endTimeInSeconds;
    }
}
