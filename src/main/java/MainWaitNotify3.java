
import java.util.List;
        import java.util.Scanner;
        import java.util.concurrent.CopyOnWriteArrayList;

public class MainWaitNotify3 {
    static List<String> strings = new CopyOnWriteArrayList<>();
    static boolean noEnd = true;

    public static void main(String[] args) {
        new Operator().start();
        new Machine().start();
    }

    static class Operator extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (noEnd) {
                synchronized (strings) {
                    while (!strings.isEmpty()) {
                        try {
                            strings.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String string = scanner.nextLine();
                    strings.add(string);
                    if (string.equals("")) {
                        noEnd = false;
                    }
                    strings.notify();
                }
            }
        }
    }

    static class Machine extends Thread {
        @Override
        public void run() {
            while (noEnd) {
                synchronized (strings) {
                    while (strings.isEmpty()) {
                        try {
                            strings.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (strings.get(0).equals("")){
                        noEnd = false;
                    }
                    System.out.println(strings.remove(0));
                    strings.notify();
                }
            }
        }
    }
}
