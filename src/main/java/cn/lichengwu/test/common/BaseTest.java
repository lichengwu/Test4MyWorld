package cn.lichengwu.test.common;

import lichengwu.lang.Random;
import org.junit.runner.RunWith;

/**
 * base test class for extends
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-04-21 下午10:45
 */
@RunWith(DefaultLoggerTestRunner.class)
public class BaseTest {


    private static char[] KEY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public static String randomString(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(KEY[Random.rangeRandom(0, length)]);
        }
        return str.toString();
    }
}
