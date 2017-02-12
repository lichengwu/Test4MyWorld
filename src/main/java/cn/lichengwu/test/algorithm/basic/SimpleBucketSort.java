package cn.lichengwu.test.algorithm.basic;

/**
 * @author 佐井
 * @since 2017-02-12 15:35
 */
public class SimpleBucketSort {


    public static void main(String[] args) {
        int arr[] = {2, 46, 2, 5, 7, 32, 3, 1, 9, 1};

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
