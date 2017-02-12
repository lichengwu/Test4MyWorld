package cn.lichengwu.test.algorithm.basic;

import org.junit.Test;

/**
 * 冒泡排序
 *
 * @author 佐井
 * @since 2017-02-12 15:41
 */
public class BubbleSort extends SortData {


    @Override
    @Test
    public void sort() {

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }


        for (int i : arr) {
            System.out.print(i + ",");
        }


    }
}
