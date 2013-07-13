package cn.lichengwu.test.basic;

/**
 * 内部类和匿名类测试
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-05-06 上午12:06
 */
public class Outer {

    public Outer(){
        Nest n = new Nest();
    }

    private int a;

    private static int x;

    public void print() {
        System.out.println("a");
    }

    public class Inner {
        private int b;

        public void methodName() {
            a = 1;
            print();
        }
    }

    public static class Nest {

        public Nest(){
            System.out.println("nest");
        }

        private int c;

        public void methodName() {
            x = 1;

        }
    }

}
