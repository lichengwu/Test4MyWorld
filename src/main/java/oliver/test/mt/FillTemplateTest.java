package oliver.test.mt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * 模板回填测试
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-04 7:44 PM
 */
public class FillTemplateTest {

    final ExecutorService exec = Executors.newFixedThreadPool(4);

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    @Test
    public void test() throws InterruptedException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/lichengwu/tmp/deal_id.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                queue.put(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String baseUrl = "http://localhost:8088/engine/form/show?textType=2&dealId={0}";
        try {
            while (!queue.isEmpty()) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = null;
                        try {
                            url = MessageFormat.format(baseUrl, queue.take());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                        try {
                            Document document = Jsoup.connect(url).get();
                            Elements elements = document.select("div.no-fill-desc");
                            String html = elements.html();
                            if (html.contains("http://") || html.contains("imgUrl") || html.contains("src")) {
                                System.out.println(url);
                            }
                        } catch (IOException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
        /**
         * wait for other thread
         */
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test2() throws IOException {
        Document document = Jsoup.connect("http://localhost:8088/engine/form/show?textType=2&dealId=639861").get();
        Elements elements = document.select(".no-fill-desc");
        System.out.println(document.html());
        System.out.println(elements.html().contains("模板变更导致无法填入表单"));
    }

}
