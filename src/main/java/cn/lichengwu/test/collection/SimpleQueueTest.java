package cn.lichengwu.test.collection;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author 佐井
 * @version 1.0
 * @created 2013-10-27 12:03 AM
 */
public class SimpleQueueTest {

    @Test
    public void test() {
        final int size = 10000;
        SimpleQueue<Integer> queue = new SimpleQueue<>(2);
        for (int i = 0; i < size; i++) {
            queue.enQueue(i);
        }

        Assert.assertEquals(size,queue.size());

        for (Integer i = 0; i < size; i++) {
            Assert.assertEquals(i, queue.deQueue());
        }
    }
}
