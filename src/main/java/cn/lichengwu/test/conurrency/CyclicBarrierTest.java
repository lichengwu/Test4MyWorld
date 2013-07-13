package cn.lichengwu.test.conurrency;

import java.util.concurrent.*;

import org.junit.Test;

/**
 * CyclicBarrierTest
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-02-02 4:18 PM
 */
public class CyclicBarrierTest {

    @Test
    public void test() throws InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() + 1);

        final int gate_threshold = 4;
        final CyclicBarrier gate = new CyclicBarrier(gate_threshold, new Runnable() {
            @Override
            public void run() {
                System.out.println("4 threads arrived, gate open...");
            }
        });

        while (true) {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + " arrived");
                    try {
                        gate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
