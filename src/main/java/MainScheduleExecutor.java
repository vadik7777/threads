import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainScheduleExecutor {

    public static void main(String[] args) {
        System.out.println("start");
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new MyThread(), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        System.out.println("end");
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(1);
        }
    }
}
