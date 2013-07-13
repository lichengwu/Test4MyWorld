package cn.lichengwu.test.thread;

import org.junit.Test;

/**
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-06 8:46 PM
 */
public class InterruptTest {

    @Test
    public void test1() {

        System.out.println("interrupt:" + Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("interrupt:" + Thread.currentThread().isInterrupted());

    }

    @Test
    public void test3() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running...");
            }
        });

        thread.interrupt();

        System.out.println("interrupt:" + thread.isInterrupted());

        // thread.start();
        //
        // thread.interrupt();
        //
        //
        // System.out.println("interrupt:" + thread.isInterrupted());

    }
}
