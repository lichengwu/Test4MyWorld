package cn.lichengwu.test.sync;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * test for {@link CopyOnWriteArrayList}
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-02 1:22 PM
 */
public class CopyOnWriteArrayListTest {

    /**
     * this size does not match, because iterator is a copy of elements
     */
    @Test
    public void test() throws InterruptedException {

        final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int listSize = list.size();
                    try {
                        TimeUnit.MILLISECONDS.sleep((int)Math.random()*1000);
                    } catch (InterruptedException e) {
                    }
                    Iterator<Integer> iterator = list.iterator();
                    int iCount=0;
                    while(iterator.hasNext()){
                        iCount++;
                    }
                    Assert.assertEquals(listSize,iCount);
                    
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add(i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();



        TimeUnit.SECONDS.sleep(10);

    }
}
