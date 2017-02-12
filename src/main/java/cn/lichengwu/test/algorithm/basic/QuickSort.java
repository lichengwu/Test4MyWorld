package cn.lichengwu.test.algorithm.basic;

import org.junit.Test;

/**
 * 快排
 *
 * @author 佐井
 * @since 2017-02-12 16:04
 */
public class QuickSort extends SortData {
    @Override
    @Test
    public void sort() {
        quickSort(0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + ",");
        }

    }

    private void quickSort(int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[left];
        int l = left;
        int r = right;

        while (l < r) {
            while (l < r && arr[r] >= base) {
                r--;
            }

            while (l < r && arr[l] <= base) {
                l++;
            }
            if (l < r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        arr[left] = arr[l];
        arr[l] = base;

        quickSort(left, l - 1);
        quickSort(l + 1, right);
    }
}
