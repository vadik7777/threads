import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainWaitNotify2 {

    static List<String> strings = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new Operator().start();
        new Machine().start();
    }
    static class Operator extends Thread{
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true){
                synchronized (strings){
                    System.out.println("in operator");
                    strings.add(scanner.nextLine());
                    System.out.println(strings.get(0));
                    strings.notify();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    static class Machine extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (strings){
                    System.out.println("in machine");
                    try {
                        strings.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(strings.remove(0));
                }
            }
        }
    }

}
