public class MainWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();
        synchronized (threadA){
            threadA.wait();
        }
        System.out.println(threadA.total);
    }

    static class ThreadA extends Thread {
        int total;

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    total += i;
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify();
            }
        }
    }
}
