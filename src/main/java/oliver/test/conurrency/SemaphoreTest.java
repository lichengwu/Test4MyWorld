package oliver.test.conurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * SemaphoreTest
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-02-02 2:24 PM
 */
public class SemaphoreTest {


    @Test
    public void test() throws InterruptedException {

        final BoundedList<Integer> list = new BoundedList<>(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    try {
                        list.add(index++);
                        System.out.println(System.currentTimeMillis() + " add " + index);
                    } catch (InterruptedException ignore) {

                    }
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    try {
                        list.remove(index++);
                        System.out.println(System.currentTimeMillis() + " remove " + index);
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        }).start();
        Thread.currentThread().join();
    }

    static class BoundedList<T> {

        private final List<T> list;
        private final Semaphore semaphore;

        public BoundedList(int bound) {
            this.list = new ArrayList<>();
            semaphore = new Semaphore(bound);
        }

        public boolean add(T o) throws InterruptedException {
            semaphore.acquire();
            boolean added = false;
            try {
                added = list.add(o);
                return added;
            } finally {
                if (!added) {
                    semaphore.release();
                }
            }
        }

        public boolean remove(T o) {
            boolean removed = list.remove(o);
            if (removed) {
                semaphore.release();
            }
            return removed;
        }
    }

}
