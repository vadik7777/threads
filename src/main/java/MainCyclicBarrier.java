import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        new SportsMan(cyclicBarrier);
        new SportsMan(cyclicBarrier);
        new SportsMan(cyclicBarrier);
        //new SportsMan(cyclicBarrier);
    }
    static class Run extends Thread{
        @Override
        public void run() {
            System.out.println("Run is begin");
        }
    }

    static class SportsMan extends Thread{
        CyclicBarrier cyclicBarrier;

        public SportsMan(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
