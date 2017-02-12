package cn.lichengwu.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 临时
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-07-04 1:59 PM
 */
public class Main implements Serializable {
    private static final long serialVersionUID = 6954888713124249060L;

    private volatile long i = 0;

    private AtomicLong l = new AtomicLong(0);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        int n = Runtime.getRuntime().availableProcessors();
        ExecutorService exc = Executors.newFixedThreadPool(n);

        final Main main = new Main();


        List<Future<Boolean>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(exc.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    for (int j = 0; j < 100000; j++) {
                        main.i++;
                        main.l.incrementAndGet();
                    }
                    return true;
                }
            }));
        }

        for (Future<Boolean> booleanFuture : list) {
            System.out.println(booleanFuture.get());
        }

        exc.shutdown();

        System.out.println(n);

        System.out.println(main.i);

        System.out.println(main.l.get());


    }
}
