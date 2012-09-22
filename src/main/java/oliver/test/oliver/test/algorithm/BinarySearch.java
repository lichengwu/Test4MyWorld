package oliver.test.oliver.test.algorithm;

import org.junit.Test;

/**
 * 二分查找测试
 * User: lichengwu
 * Date: 9/22/12
 * Time: 8:44 PM
 */
public class BinarySearch {

    /**
     * 给的一个排序好的数组，查找出至少出现两次的一个元素
     */
    @Test
    public void findTwiceTest() {
        int[] array = {1, 2, 2, 2, 2, 2, 3, 4, 4, 5, 6, 7, 7, 7, 8, 9, 10};

        int begin = 0;
        int end = array.length - 1;
        findTwice(array, begin, end);

    }

    /**
     * 给的一个排序好的数组，查找出至少出现两次的一个元素
     *
     * @param array array to find
     * @param begin begin index
     * @param end   end index
     */
    private void findTwice(int[] array, int begin, int end) {
        int mid = (begin + end) / 2 + 1;
        if (array[mid] == mid) {
            System.out.println(mid);
        } else if (array[mid] < mid) {
            end = mid;
            findTwice(array, begin, end);
        } else {
            begin = mid;
            findTwice(array, begin, end);
        }
    }
}
