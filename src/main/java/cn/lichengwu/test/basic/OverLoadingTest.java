package cn.lichengwu.test.basic;

import org.junit.Test;

/**
 * Overloading between two classes
 * User: lichengwu
 * Date: 12-10-11
 * Time: 下午12:34
 */
public class OverLoadingTest {

    class A {
        public void m(Integer m) {
            System.out.println("class A" + m);
        }
    }

    class B extends A {

        public void m(Number m) {
            System.out.println("class B" + m);
        }
    }

    @Test
    public void test() {

        A a = new B();
        a.m(2);     // 实际输出的是class A2.0 因为B中并没有Override A的方法，所有B中有了两个方法 m(Integer)和m(Number)
        // 由于方法的选择是在编译期确定的，a 的编译期类型是A 所以选择了 A中的m(Integer) 方法
    }
}
