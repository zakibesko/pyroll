import java.io.File;
import java.time.LocalDateTime;

public class AuditorThread extends Thread {

    private File directory;
    private int previousCount;

    public AuditorThread(String path) {

        directory = new File(path);

        previousCount = directory.listFiles().length;
    }

    @Override
    public void run() {

        while (true) {

            try {

                int currentCount =
                        directory.listFiles().length;

                if (currentCount != previousCount) {

                    System.out.println(
                            "[AUDIT] Security Alert: Payroll count changed. "
                                    + "Current files: "
                                    + currentCount
                                    + " at "
                                    + LocalDateTime.now()
                    );

                    previousCount = currentCount;
                }

                Thread.sleep(5000);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}