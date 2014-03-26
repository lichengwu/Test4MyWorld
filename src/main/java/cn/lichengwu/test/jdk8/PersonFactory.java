package cn.lichengwu.test.jdk8;

/**
 * TODO 方法说明
 *
 * @author 佐井
 * @version 1.0
 * @created 2014-03-22 下午12:59
 */
@FunctionalInterface
public interface PersonFactory {
    Person create(String name,Integer age);
}
