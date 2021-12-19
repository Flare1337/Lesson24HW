import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CopyFileTask implements Callable<Integer> {
    public long GIGABYTE_IN_BYTES = 1073741824;
    public int bytesInFile;
    public String fromPath;
    public String toPath;

    public CopyFileTask(String fromPath, String toPath) {
        this.fromPath = fromPath;
        this.toPath = toPath;
    }

    @Override
    public Integer call() throws IOException, InterruptedException {
        try (FileInputStream fileInputStream = new FileInputStream(fromPath);
             FileOutputStream fileOutputStream = new FileOutputStream(toPath)) {
            byte[] buffer = new byte[2048];

            System.out.println("Process of copying file has been started!");
            while ((bytesInFile += fileInputStream.read(buffer)) > 0 && !Thread.currentThread().isInterrupted()) {
                if (bytesInFile > GIGABYTE_IN_BYTES) {
                    System.out.println("The file is too big!");
                    break;
                }
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Bytes have been written! Total written bytes " + bytesInFile);
                fileOutputStream.write(buffer);
            }

            if (bytesInFile == -1) {
                System.out.println("There is no bytes in this file...");
            }

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Whoops, something went wrong! The copying of the file has been interrupted...");
            }
            System.out.println("Process of copying the file is finished! Total number of bytes: " + (bytesInFile + 1));
        }
        return (bytesInFile + 1);
    }
}
