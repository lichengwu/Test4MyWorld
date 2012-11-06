package oliver.test.algorithm;

import org.junit.Test;

/**
 * 求数字中最大连续子数组和
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2012-11-06 6:01 PM
 */
public class SumContinuousItem {

    /**
     * O(n^3)算法
     * 
     * @param array
     * @return
     */
    public static int getMax1(int[] array) {
        int max = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                for (int k = i; k <= j; k++) {
                    currentMax += array[k];
                }
                max = Math.max(max, currentMax);
                currentMax = 0;
            }
        }
        return max;
    }

    /**
     * O(n^2)算法
     * 
     * @param array
     * @return
     */
    public static int getMax2(int[] array) {
        int max = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                currentMax += array[j];
                max = Math.max(max, currentMax);
            }
            currentMax = 0;
        }
        return max;
    }

    /**
     * O(nlogn)算法
     * 
     * @param array
     * @return
     */
    public static int getMax3(int[] array) {

        return sub3(array, 0, array.length - 1);
    }

    /**
     * 递归
     * 
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int sub3(int[] array, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return Math.max(Integer.MIN_VALUE, array[start]);
        }

        int mid = (start + end) / 2;

        int lmax = 0;
        int sum = 0;
        for (int i = mid; i >= start; i--) {
            sum += array[i];
            lmax = Math.max(sum, lmax);
        }

        int rmax = 0;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += array[i];
            rmax = Math.max(sum, rmax);
        }
        return Math
                .max(Math.max((lmax + rmax), sub3(array, start, mid)), sub3(array, mid + 1, end));
    }

    /**
     * O(n)算法
     * 
     * @param array
     * @return
     */
    public int getMax4(int[] array) {
        int max = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int i = 0; i < array.length; i++) {
            currentMax = Math.max(0, currentMax += array[i]);
            max = Math.max(max, currentMax);
        }

        return max;
    }

    /**
     * <p>
     * 4282286 getMax1 cost:285627671578nanoseconds
     * </p>
     * <p>
     * 4282286 getMax2 cost:63400302nanoseconds
     * </p>
     * <p>
     * 4282286 getMax3 cost:7109451nanoseconds
     * </p>
     * <p>
     * 4282286 getMax4 cost:713332nanoseconds
     * </p>
     */
    @Test
    public void test() {
        final int LEN = 10000;
        int[] array = new int[LEN];
        for (int i = 0; i < array.length; i++) {

            double random = Math.random() * 1000;
            array[i] = (int) (Math.tan(random) * random);
        }
        long start = System.nanoTime();
        long end = start;
        System.out.println(getMax1(array));
        end = System.nanoTime();
        System.out.println("getMax1 cost:" + (end - start) + "nanoseconds");
        start = end;
        System.out.println(getMax2(array));
        end = System.nanoTime();
        System.out.println("getMax2 cost:" + (end - start) + "nanoseconds");
        start = end;
        System.out.println(getMax3(array));
        end = System.nanoTime();
        System.out.println("getMax3 cost:" + (end - start) + "nanoseconds");
        start = end;
        System.out.println(getMax4(array));
        end = System.nanoTime();
        System.out.println("getMax4 cost:" + (end - start) + "nanoseconds");
        start = end;
    }
}
