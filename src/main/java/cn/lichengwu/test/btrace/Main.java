package cn.lichengwu.test.btrace;

import java.util.concurrent.TimeUnit;

/**
 * 被btrace监控的类
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-04-25 4:16 PM
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(10);
        }
    }

}
