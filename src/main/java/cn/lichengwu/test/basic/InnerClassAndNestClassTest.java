package cn.lichengwu.test.basic;

import org.junit.Test;

/**
 * 内部类和匿名类测试
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-05-06 上午12:34
 */
public class InnerClassAndNestClassTest {

    @Test
    public void test() {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();

    }

}
