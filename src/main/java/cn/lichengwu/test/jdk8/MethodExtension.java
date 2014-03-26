package cn.lichengwu.test.jdk8;

import java.util.HashMap;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-21 下午10:43
 */
public class MethodExtension implements MyInterface {

    @Override
    public void normalMethod() {
        System.out.println("normalMethod");
    }

    public static void main(String[] args) {
        MethodExtension extension = new MethodExtension();
        extension.normalMethod();
        extension.defaultMethod();
        MyInterface.utilMethod();
        HashMap hashMap = new HashMap();

    }
}
