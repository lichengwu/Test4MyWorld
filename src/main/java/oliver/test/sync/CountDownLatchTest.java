package oliver.test.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;

/**
 * test for {@link CountDownLatch}
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-02 3:17 PM
 */
public class CountDownLatchTest {

    @Test
    public void test() throws InterruptedException {

        final int nThread = 5;

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);

        for (int i = 1; i <= nThread; i++) {
            final int sleep = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        TimeUnit.SECONDS.sleep(new Integer(sleep));
                    } catch (InterruptedException e) {
                    } finally {
                        endGate.countDown();
                    }
                }
            }).start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        Assert.assertEquals(nThread, (end - start) / 1000);

    }
}
