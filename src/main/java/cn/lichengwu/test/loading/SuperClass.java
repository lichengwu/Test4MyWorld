package cn.lichengwu.test.loading;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-11-29 23:47
 */
public class SuperClass {

    public static int a = initStaticField();

    static {
        System.out.println("super class init");
    }

    private static int initStaticField() {
        System.out.println("super class initStaticField");
        return 1;
    }

}
