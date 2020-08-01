import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    //java online compiler
    //https://repl.it/languages/java10
    //https://www.tutorialspoint.com/compile_java_online.php
    //https://www.mycompiler.io/new/java

    static final String URL_DDOS = "http://localhost/";
    static int REQUEST_COUNT = 100;

    static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    static boolean error = false;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        while (REQUEST_COUNT > 0 && !error) {
            executorService.submit(new DDosnRunnable());
            REQUEST_COUNT--;
        }
        executorService.shutdown();
    }

    static class DDosnRunnable implements Runnable {

        @Override
        public void run() {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(URL_DDOS).openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                System.out.println(URL_DDOS + " -> bytes = "+ bytes.length);
                is.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
            }
        }
    }
}
