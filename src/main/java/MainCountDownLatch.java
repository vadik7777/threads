import java.util.concurrent.CountDownLatch;

public class MainCountDownLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        countDownLatch.await();

        System.out.println("all work done");

    }
}

class Work extends Thread{
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try{
            sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("done work");
        countDownLatch.countDown();
    }
}
