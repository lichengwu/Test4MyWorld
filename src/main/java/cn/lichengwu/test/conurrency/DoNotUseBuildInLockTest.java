package cn.lichengwu.test.conurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * do not use build-in lock
 * 
 * if a class use build-in lock ensure concurrence access it's field, thread
 * will be blocked by other threads who had acquired the lock.
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2012-12-23 PM5:38
 */
public class DoNotUseBuildInLockTest {

    public static void main(String[] args) throws InterruptedException {
        // lock object use build-in lock in method get()
        final LockObject object = new LockObject();

        // a thread acquire the LockObject's build-in lock
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 acquire lock : " + new Date().toString());
                synchronized (object) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("thread 1 release lock : " + new Date().toString());
            }
        }).start();

        // sleep main thread ensure the thread above had been started.
        TimeUnit.SECONDS.sleep(2);

        // the other start and call get() method, but the build-in lock
        // had been hold by the thread above.
        // get() method must wait for the build-in lock.
        new Thread(new Runnable() {
            @Override
            public void run() {
                long begin = System.currentTimeMillis();
                object.get();
                System.out.println("get() method wait :" + (System.currentTimeMillis() - begin)
                        + "ms");
            }
        }).start();

    }
}
