package cn.lichengwu.test.jdk8;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-22 下午12:37
 */
@FunctionalInterface
public interface ToStringFunction<T> {
    String convert2String(T t);
    default String origin2String(T t) {
        return t.toString();
    }
}
