import java.util.concurrent.Semaphore;

public class MainSemaphor {

    public static void main(String[] args) {
        Semaphore table = new Semaphore(2);
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
    }

}

class Person extends Thread {

    public Person(Semaphore table) {
        this.table = table;
    }

    Semaphore table;

    @Override
    public void run() {
        System.out.println(this.getName() + " waiting for table");
        try {
            table.acquire();
            System.out.println(this.getName() + " eat at table");
            sleep(2000);
            System.out.println(this.getName() + " release table");
            table.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
