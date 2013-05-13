package oliver.test.btrace;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.Sys;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnTimer;

/**
 * show memory info
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-04-25 4:34 PM
 */


@BTrace
public class MemoryInfo {

    @OnTimer(500)
    public static void showALl() {
        println("heap:");
        println(Sys.Memory.heapUsage());
        println("nonHeap:");
        println(Sys.Memory.nonHeapUsage());
    }

}
