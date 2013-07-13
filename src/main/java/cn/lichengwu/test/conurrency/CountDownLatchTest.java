package cn.lichengwu.test.conurrency;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO 方法说明
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-02-02 1:03 PM
 */
public class CountDownLatchTest {


    @Test
    public void test() throws InterruptedException {
        // thread number
        final int threadNum = Runtime.getRuntime().availableProcessors() + 1;
        // start event
        final CountDownLatch startEvent = new CountDownLatch(1);
        // finish event
        final CountDownLatch finishEvent = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // await for start
                        startEvent.await();
                        System.out.println(Thread.currentThread() + " start at : " + System.currentTimeMillis());
                        // current thread finish
                        finishEvent.countDown();
                    } catch (InterruptedException ignore) {

                    }
                }
            }).start();

            // sleep 0.5ms
            TimeUnit.MILLISECONDS.sleep(500);
        }

        long startTime = System.currentTimeMillis();

        startEvent.countDown();
        // wait for all thread finish
        finishEvent.await();
        System.out.println("total finish cost : " + (System.currentTimeMillis() - startTime) + "ms");

    }

}
