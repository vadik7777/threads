import java.util.concurrent.Exchanger;

public class MainExchenger {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        new Vadim(exchanger);
        new Anket(exchanger);
    }

    static class Vadim extends Thread {
        Exchanger exchanger;

        public Vadim(Exchanger exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("Hello!!!");
                sleep(3000);
                exchanger.exchange("It's Vadim");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Anket extends Thread {
        Exchanger exchanger;

        public Anket(Exchanger exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null));
                System.out.println(exchanger.exchange(null));
                //System.out.println(exchanger.exchange(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
