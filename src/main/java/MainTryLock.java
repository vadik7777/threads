import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTryLock {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            lock.lock();
            System.out.println(getName() + " start");
            try {
                sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(getName() + " stop");
            lock.unlock();
            System.out.println(getName() + " released");
        }
    }
    static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println(getName() + " start");
            while (true){
                if(lock.tryLock()){
                    System.out.println(getName() + " working");
                    break;
                } else
                    System.out.println(getName() + " waiting");
            }
        }
    }
}
