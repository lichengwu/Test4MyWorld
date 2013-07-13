package cn.lichengwu.test.conurrency;

/**
 * lazy init by placeholder(thread safe)
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-31 10:19 PM
 */
public class Resource {

    public static Resource getInstance() {
        return ResourceHolder.INSTANCE;
    }


    private static class ResourceHolder {
        static final Resource INSTANCE = new Resource();
    }

}
