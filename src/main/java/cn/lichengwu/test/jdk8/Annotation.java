package cn.lichengwu.test.jdk8;

import org.junit.Test;

import java.util.List;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-23 下午9:23
 */
public class Annotation {

    @Test
    public void testAnnotation() {

    }


    public void testMethod(@notnull List<String> list) {
        System.out.println(list.size());
    }
}
