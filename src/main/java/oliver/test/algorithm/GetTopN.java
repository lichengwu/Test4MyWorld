package oliver.test.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 获取数组中的top N
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-04-19 9:30 AM
 */
public class GetTopN {

    public static Long[] getTopN(Long[] nums, int topN) {

        Long[] top = new Long[topN];

        //byte[] bitMap = new byte[nums.length];

        long bitMap = 0;

        int iCount = 0;
        for (int i = 0; i < nums.length /*&& iCount < topN*/; i++) {
            //int flag = bitMap[nums[i].intValue()];
            int mask = 1 << nums[i];
            if ((bitMap & mask) == 0) {
                bitMap = bitMap | mask;
                iCount++;
            }
        }

        //
        bitMap = bitMap ^ 1;
        // reset iCount
        iCount = 0;
        int index = 0;

        while (iCount < topN && bitMap > 0) {
            if ((bitMap & 1) == 1) {
                top[iCount] = Long.MAX_VALUE - Long.valueOf(index);
                iCount++;
            }
            bitMap = bitMap >> 1;
            index++;
        }
        return top;
    }

}
