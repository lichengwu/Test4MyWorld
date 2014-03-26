package cn.lichengwu.test.jdk8;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-21 下午10:45
 */
public interface MyInterface {
    void normalMethod();

    default void defaultMethod() {
        System.out.println("defaultMethod");
    }
    static void utilMethod() {
        System.out.println("utilMethod");
    }
}

