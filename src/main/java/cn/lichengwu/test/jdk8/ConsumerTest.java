package cn.lichengwu.test.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-10-31 17:35
 */
public class ConsumerTest {

    @Test
    public void test() {
        List<String> ss = new ArrayList<>();
        ss.sort((a, b) -> a.compareTo(b));
    }

}
