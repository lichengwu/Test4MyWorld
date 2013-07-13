package cn.lichengwu.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 临时
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-07-04 1:59 PM
 */
public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("1");

        System.out.println(set.add("1"));
    }
}
