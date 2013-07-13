package cn.lichengwu.test.conurrency;

/**
 * a class with build-in lock method
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2012-12-23 PM5:39
 */
public class LockObject {

    private int count = 0;

    // access by build-in lock
    public synchronized int get() {
        return count++;
    }

}
