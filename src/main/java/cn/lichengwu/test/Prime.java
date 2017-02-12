package cn.lichengwu.test;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 素数算法
 *
 * @author 佐井
 * @version 1.0
 * @created 2015-03-29 14:36
 */
public class Prime {


    public static boolean isPrime(long num) {
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static List<Integer> prime(int num) {
        boolean[] flags = new boolean[num + 1];
        //初始化，认为都是素数
        for (int i = 0; i < flags.length; i++) {
            flags[i] = true;
        }
        //筛选
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (flags[i]) {
                for (int multi = 2, j = multi * i; j <= num; multi++, j = multi * i) {
                    if (flags[j] && j % i == 0) {
                        flags[j] = false;
                    }
                }
            }
        }
        //整理结果
        List<Integer> rs = new ArrayList<>();
        for (int i = 2; i < flags.length; i++) {
            if (flags[i]) {
                rs.add(i);
            }

        }
        return rs;
    }


    @Test
    public void test() {
        //第一种方式
        long start = System.currentTimeMillis();
        List<Integer> prime1 = new ArrayList<>();
        int num = Short.MAX_VALUE * 500;
        for (int i = 2; i < num; i++) {
            if (isPrime(i)) {
                prime1.add(i);
            }
        }
        System.out.println("prime1 time used:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();

        //第二种方式
        List<Integer> prime2 = prime(num);
        System.out.println("prime2 time used:" + (System.currentTimeMillis() - start) + "ms");

        //数据校验
        Assert.assertTrue(Objects.deepEquals(prime1, prime2));
    }
}
