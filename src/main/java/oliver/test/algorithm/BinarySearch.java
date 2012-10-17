package oliver.test.algorithm;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 二分查找测试 User: lichengwu Date: 9/22/12 Time: 8:44 PM
 */
public class BinarySearch {

    public static final int LENGTH = 10000000;

    private static int[] array;

    @BeforeClass
    public static void init() {
        array = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            array[i] = i;
        }
    }

    /**
     * 给的一个排序好的数组，查找出至少出现两次的一个元素
     */
    @Test
    public void findTwiceTest() {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11 };

        int begin = 0;
        int end = array.length - 1;
        findTwice(array, begin, end);

    }

    /**
     * 给的一个排序好的数组，查找出至少出现两次的一个元素
     * 
     * @param array
     *            array to find
     * @param begin
     *            begin index
     * @param end
     *            end index
     */
    private void findTwice(int[] array, int begin, int end) {
        int mid = (begin + end) / 2;
        int midValue = array[0] + mid;
        if (begin + 1 == end) {
            System.out.println(midValue);
        } else if (array[mid] < midValue) {
            end = mid;
            findTwice(array, begin, end);
        } else {
            begin = mid;
            findTwice(array, begin, end);
        }
    }

    /**
     * 循环形式的二分查找
     * 
     * @param array
     * @param search
     * @return
     */
    int binarySearch1(int[] array, int search) {
        int lower = 0;
        int upper = array.length - 1;
        int position = -1;
        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            if (array[mid] < search) {
                lower = mid + 1;
            } else if (array[mid] > search) {
                upper = mid - 1;
            } else {
                position = mid;
                break;
            }

        }
        return position;
    }

    /**
     * 递归方式的二分查找
     * 
     * @param array
     * @param begin
     * @param end
     * @param search
     * @return
     */
    int binarySearch2(int[] array, int begin, int end, int search) {
        int position = -1;
        if (begin > end) {
            return position;
        }
        int mid = (begin + end) / 2;
        if (array[mid] < search) {
            position = binarySearch2(array, mid + 1, end, search);
        } else if (array[mid] > search) {
            position = binarySearch2(array, begin, mid - 1, search);
        } else {
            position = mid;
        }
        return position;
    }

    /**
     * 从测试结果看  binarySearch1 7s
     *             binarySearch2 8s
     * 差距不是很大
     */
    @Test
    public void testBinarySearch() {
        for (int i = 0; i < LENGTH; i++) {
            int search = (int) (Math.random() * LENGTH);
            Assert.assertEquals(search, binarySearch2(array, 0, array.length - 1, search));
            //Assert.assertEquals(search, binarySearch1(array, search));
        }

    }
}
