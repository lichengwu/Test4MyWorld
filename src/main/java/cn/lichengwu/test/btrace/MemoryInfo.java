package cn.lichengwu.test.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * show memory info
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-04-25 4:34 PM
 */


@BTrace
public class MemoryInfo {


    @OnMethod(
            clazz = "java.lang.StringBuilder",
            method = "<init>")
    public static void ppp() {
        String str = BTraceUtils.jstackStr(10);

        //get start with com.taobao or com.alibaba
        String taobao = "";
        String[] arr = str.split("\n");
        for (String string : arr) {
            if (string.startsWith("com.taobao") || string.startsWith("com.alibaba")) {
                taobao = string;
                break;
            }
        }

        if (taobao.length() > 0) {
            if (!resultMap.containsKey(taobao)) {
                resultMap.put(taobao, 0l);
            }
            Long _count = resultMap.get(taobao);
            resultMap.put(taobao, _count + 1);
        }

//        if (count % 10 == 0) {
//            BTraceUtils.println("==========================count: " + count);
//            //get Map count
//            long _count = 0;
//            for (Map.Entry<String, Long> en : resultMap.entrySet()) {
//                BTraceUtils.println(en.getValue() + "\t" + en.getKey());
//                _count = _count + en.getValue();
//            }
//            BTraceUtils.println("========================== map count: " + _count + "\n");
//
//        }

    }

    private volatile static Map<String, Long> resultMap = new ConcurrentHashMap<String, Long>();


    private  static Set<Long> countSet = Collections.newSetFromMap(new ConcurrentHashMap<Long, Boolean>());


    @TLS
    private static AtomicInteger count = new AtomicInteger(0);

    @OnMethod(clazz = "com.taobao.trip.atw.result.PriceMerger", method = "fcy")
    public static void onnew(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        long key = (pcn + "." + pmn).hashCode();
        int s = args != null ? Arrays.hashCode(args) : 0;
        key = (key << 32) | s;

        countSet.add(key);
        count.incrementAndGet();
    }

    @OnTimer(5000)
    public static void print() {
        BTraceUtils.print("total:");
        BTraceUtils.print(count.get());
        BTraceUtils.print(",real:");
        BTraceUtils.println(countSet.size());
    }

    @TLS
    private static Map<String, Integer> countMap = BTraceUtils.newHashMap();

    private static String prefix = "package";

    @OnMethod(clazz = "java.lang.String", method = "/.*/")
    public static void traceMethodInvoke() {
        String str = BTraceUtils.jstackStr();
        for (String currentClass : str.split("\\n")) {
            if (BTraceUtils.Strings.startsWith(currentClass, prefix)) {
                if (!countMap.containsKey(currentClass)) {
                    countMap.put(currentClass, 1);
                } else {
                    countMap.put(currentClass, countMap.get(currentClass) + 1);
                }
                break;
            }
        }
    }



    private static Map<String, long[]> callMap = BTraceUtils.newHashMap();

    @OnMethod(clazz = "/com\\.taobao\\.trip\\..*/", method = "/.*/", location = @Location(Kind.CALL))
    public static void onCall(@ProbeClassName String clazz, @ProbeMethodName String method) {
        String full = clazz + "#" + method;
        // 0:call times
        // 1:avg time usage
        // 3:current call time
        if (!callMap.containsKey(full)) {
            callMap.put(full, new long[]{0, 0, BTraceUtils.timeNanos()});
        } else {
            long[] args = callMap.get(full);
            args[2] = BTraceUtils.timeNanos();
        }
    }

    @OnMethod(clazz = "/com\\.taobao\\.trip\\..*/", method = "/.*/", location = @Location(Kind.RETURN))
    public static void onReturn(@ProbeClassName String clazz, @ProbeMethodName String method) {
        String full = clazz + "#" + method;
        long[] args = callMap.get(full);
        if (args != null) {
            long usage = BTraceUtils.timeNanos() - args[2];
            args[1] = (args[0] * args[1] + usage) / (args[0] + 1);
            args[0] = args[0] + 1;
        }
    }


    @OnTimer(5000)
    public static void printCall() {
        BTraceUtils.println("=====================START===================");
        for (Map.Entry<String, long[]> entry : callMap.entrySet()) {
            if (entry.getValue()[0] > 500) {
                BTraceUtils.println(entry.getValue()[0] + "\t" + entry.getValue()[1] + "\t" + entry.getKey());
            }
        }

        BTraceUtils.println("=====================END======================");
    }


}
