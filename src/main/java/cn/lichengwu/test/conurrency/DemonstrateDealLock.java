package cn.lichengwu.test.conurrency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * a deadlock test
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-22 PM11:22
 */
public class DemonstrateDealLock {

    private static final int NUM_THREADS = 1;

    private static final int NUM_ACCOUNTS = 5;

    private static final int NUM_ITERATIONS = 1;

    private static final Object lock = new Object();

    @Test
    public void test() throws InterruptedException {

        final Random rnd = new Random();

        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
            accounts[i].id = i;
        }

        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(1000);
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }

        Thread.currentThread().isInterrupted();
        Thread.currentThread().join();
        System.out.println("end");
    }

    public static void transferMoney(final Account from, final Account to, final Integer amount) {
        class Helper {
            public void transfer() {
                from.debit(amount);
                to.credit(amount);
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    public static void transferMoney2(final Account from, final Account to, final Integer amount) {
        class Helper {
            public void transfer() {
                from.debit(amount);
                to.credit(amount);
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            while (true) {
                if (from.lock.tryLock()) {
                    try {
                        if (to.lock.tryLock()) {
                            try {
                                new Helper().transfer();
                                return;
                            } finally {
                                to.lock.unlock();
                            }
                        }
                    } finally {
                        from.lock.unlock();
                    }
                }
            }
        } else if (fromHash > toHash) {

            while (true) {

                if (to.lock.tryLock()) {
                    try {
                        if (from.lock.tryLock()) {
                            try {
                                new Helper().transfer();
                                return;
                            } finally {
                                from.lock.unlock();
                            }
                        }
                    } finally {
                        to.lock.unlock();
                    }
                }
            }

        } else {
            while (true) {
                if (from.lock.tryLock()) {
                    try {
                        if (to.lock.tryLock()) {
                            try {
                                new Helper().transfer();
                                return;
                            } finally {
                                to.lock.unlock();
                            }
                        }
                    } finally {
                        from.lock.unlock();
                    }
                }
            }
        }
    }

    static class Account {

        private Integer id;

        private Lock lock = new ReentrantLock();

        public void debit(Integer amount) {
            System.out.println("account " + id + " debit : " + amount);

        }

        public void credit(Integer amount) {
            System.out.println("account " + id + " credit : " + amount);
        }
    }
}
