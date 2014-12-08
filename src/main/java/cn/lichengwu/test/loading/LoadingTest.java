package cn.lichengwu.test.loading;

import org.junit.Test;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-11-29 23:47
 */
public class LoadingTest {


    /**
     * -XX:+TraceClassLoading
     */
    @Test
    public void testArrayLoading() {
        SuperClass[] ss = new SuperClass[10];
    }
}
