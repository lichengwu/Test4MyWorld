package cn.lichengwu.test.conurrency;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * LinkedQueueTest
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-30 11:05 PM
 */
public class LinkedQueueTest {

    private static final int NUM_THREAD = Runtime.getRuntime().availableProcessors() + 1;
    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end = new CountDownLatch(NUM_THREAD);

    ExecutorService exec = Executors.newFixedThreadPool(NUM_THREAD);

    final static LinkedQueue<Integer> queue = new LinkedQueue<>();


    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < NUM_THREAD; i++) {
            exec.execute(new Worker(i * 100000, (i + 1) * 100000));
        }
        long startTime = System.currentTimeMillis();
        start.countDown();
        end.await();
        long endTile = System.currentTimeMillis();
        System.out.println("time used :" + (endTile - startTime) + " ms");

    }

    static class Worker implements Runnable {

        final int start;

        final int end;

        public Worker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                LinkedQueueTest.start.await();
                for (int i = start; i < end; i++) {
                    queue.offer(i);
                }
                LinkedQueueTest.end.countDown();
            } catch (InterruptedException e) {

            }
        }
    }
}
