package cn.lichengwu.test.algorithm.basic;

import org.junit.Test;

/**
 * 桶排序简单版
 *
 * @author 佐井
 * @since 2017-02-12 15:35
 */
public class SimpleBucketSort extends SortData {


    @Test
    public void sort() {

        int buf[] = new int[47];

        for (int i : arr) {
            buf[i]++;
        }

        for (int i = 0; i < buf.length; i++) {
            int val = buf[i];
            if (val <= 0) {
                continue;
            }
            for (int j = 0; j < val; j++) {
                System.out.print(i);
                System.out.print(",");
            }
        }


    }

}
