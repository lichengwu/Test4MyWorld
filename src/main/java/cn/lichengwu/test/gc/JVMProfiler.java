package cn.lichengwu.test.gc;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * gc monitoring only on jdk7 or later
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-09-14 10:44 PM
 */
public class JVMProfiler {


    private static final Logger log = LoggerFactory.getLogger(JVMProfiler.class);

    private static final long JVM_START_TIME = ManagementFactory.getRuntimeMXBean().getStartTime();

    private static final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

    private static final double JAVA_VERSION;

    static {
        double v = 1.5;
        try {
            String version = System.getProperty("java.version", "1.5");
            v = Double.valueOf(version.substring(0, Math.min(version.length(), 3)));
        } catch (Throwable ignored) {
        }
        JAVA_VERSION = v;
    }

    private static Set<GCListener> listeners = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private static final long ONE_BYTE = 1024;

    private static final ExecutorService exec =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public static void registerGCListener(GCListener listener) {
        listeners.add(listener);
    }


    private static Map<JVMConstant.MemoryPool, MemoryUsage> convert(Map<String, MemoryUsage> usageMap) {
        Map<JVMConstant.MemoryPool, MemoryUsage> memoryUsageMap = new HashMap<>();
        if (usageMap != null) {
            for (Map.Entry<String, MemoryUsage> entry : usageMap.entrySet()) {
                String poolName = entry.getKey();
                JVMConstant.MemoryPool memoryPool = JVMConstant.MemoryPool.nameOf(poolName);
                if (memoryPool != null) {
                    memoryUsageMap.put(memoryPool, entry.getValue());
                } else {
                    log.error("unknown memory pool:" + poolName);
                }
            }
        }
        return memoryUsageMap;
    }

    public static void init() {

        if (JAVA_VERSION < 1.7) {
            log.error("support java version 1.7 or higher");
            return;
        }

        //get all GarbageCollectorMXBeans
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        //register every GarbageCollectorMXBean
        for (GarbageCollectorMXBean gcBean : gcBeans) {

            log.info("register " + gcBean.getName() + " for " + Arrays.deepToString(gcBean.getMemoryPoolNames()));

            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            //new listener
            NotificationListener listener = (notification, handback) -> {

                GCInfo gcInfo = buildGcInfo(notification);
                if (gcInfo == null) {
                    return;
                }
                DefaultGCLogHandBack defaultGCLogHandBack = (DefaultGCLogHandBack) handback;
                if (defaultGCLogHandBack != null && log.isErrorEnabled()) {
                    log.error("\n" + "\n" + defaultGCLogHandBack.handLog(gcInfo));
                }
                for (GCListener gcListener : listeners) {
                    if (gcListener.getGCType() != null && gcListener.getGCType() != gcInfo.getGcType()) {
                        continue;
                    }
                    // asynchronous
                    exec.submit(() -> gcListener.process(gcInfo));
                }
            };

            //add the listener
            emitter.addNotificationListener(listener, new NotificationFilter() {
                private static final long serialVersionUID = 3763793138186359389L;

                @Override
                public boolean isNotificationEnabled(Notification notification) {
                    //filter GC notification
                    return notification.getType()
                            .equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION);
                }
            }, null);
        }
    }


    private static GCInfo buildGcInfo(Notification notification) {
        //get gc userData
        GarbageCollectionNotificationInfo userData =
                GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
        //type
        String gcTypeName = userData.getGcAction();
        if (gcTypeName.length() > 7) {
            gcTypeName = gcTypeName.substring(7);
        }

        JVMConstant.GCType gcType = JVMConstant.GCType.nameOf(gcTypeName);
        if (gcType == null) {
            log.error("unsupported GC type:" + userData.getGcAction());
            return null;
        }
        //build info
        GcInfo gcInfo = userData.getGcInfo();
        GCInfo info = new GCInfo();
        info.setId(gcInfo.getId());
        info.setGcType(gcType);
        info.setGcName(userData.getGcName());
        info.setGcCause(userData.getGcCause());
        info.setStartTime(gcInfo.getStartTime());
        info.setEndTime(gcInfo.getEndTime());
        info.setUsageBeforeGc(convert(gcInfo.getMemoryUsageBeforeGc()));
        info.setUsageAfterGc(convert(gcInfo.getMemoryUsageAfterGc()));
        return info;
    }


    private static class DefaultGCLogHandBack {

        private DefaultGCLogHandBack() {
        }

        private static DefaultGCLogHandBack instance = new DefaultGCLogHandBack();

        public static DefaultGCLogHandBack getInstance() {
            return instance;
        }


        public String handLog(GCInfo gcInfo) {
            final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            StringBuilder logs = new StringBuilder();
            long totalGcTimeSpend = 0;
            //output
            String gcType = gcInfo.getGcType().getName();
            logs.append("gc name:").append(gcInfo.getGcName()).append("\n");
            //get a glance of gc
            logs.append(gcType).append(": - ").append(gcInfo.getId());
            logs.append(" (").append(gcInfo.getGcCause()).append(") ");
            logs.append("start: ").append(dateFormat.format(new Date(JVM_START_TIME + gcInfo.getStartTime())));
            logs.append(", end: ").append(dateFormat.format(new Date(JVM_START_TIME + gcInfo.getEndTime())));

            logs.append("\n");

            //memory info
            Map<JVMConstant.MemoryPool, MemoryUsage> beforeUsageMap = gcInfo.getUsageBeforeGc();
            Map<JVMConstant.MemoryPool, MemoryUsage> afterUsageMap = gcInfo.getUsageAfterGc();
            for (Map.Entry<JVMConstant.MemoryPool, MemoryUsage> entry : afterUsageMap.entrySet()) {
                JVMConstant.MemoryPool memoryPool = entry.getKey();
                String name = memoryPool.getName();
                MemoryUsage afterUsage = entry.getValue();
                MemoryUsage beforeUsage = beforeUsageMap.get(memoryPool);

                logs.append("\t[").append(name).append("] ");
                logs.append("init:").append(afterUsage.getInit() / ONE_BYTE).append("K; ");
                logs.append("used:")
                        .append(handUsage(beforeUsage.getUsed(), afterUsage.getUsed(), beforeUsage.getMax()))
                        .append("; ");
                logs.append("committed: ")
                        .append(handUsage(beforeUsage.getCommitted(), afterUsage.getCommitted(), beforeUsage.getMax()));

                logs.append("\n");
            }
            totalGcTimeSpend += gcInfo.getDuration();
            //summary
            long percent = (gcInfo.getEndTime() - totalGcTimeSpend) * 1000L / gcInfo.getEndTime();
            logs.append("duration:").append(gcInfo.getDuration()).append("ms");
            logs.append(", throughput:").append((percent / 10)).append(".").append(percent % 10).append('%');
            logs.append("\n");
            return logs.toString();
        }

        private String handUsage(long before, long after, long max) {
            StringBuilder usage = new StringBuilder();

            if (max == -1) {
                usage.append("").append(before / ONE_BYTE).append("K -> ").append(after / ONE_BYTE).append("K)");
                return usage.toString();
            }

            long beforePercent = ((before * 1000L) / max);
            long afterPercent = ((after * 1000L) / max);

            usage.append(beforePercent / 10).append('.').append(beforePercent % 10).append("%(")
                    .append(before / ONE_BYTE).append("K) -> ").append(afterPercent / 10).append('.')
                    .append(afterPercent % 10).append("%(").append(after / ONE_BYTE).append("K)");
            return usage.toString();

        }

    }


    /**
     * {@linkplain OperatingSystemMXBean#getSystemLoadAverage()}
     */
    public static double getSystemLoadAverage() {
        return osBean.getSystemLoadAverage();
    }


    public static void main(String[] args) throws InterruptedException {
        JVMProfiler.init();
        JVMProfiler.registerGCListener(new GCListener() {
            @Override
            public JVMConstant.GCType getGCType() {
                return JVMConstant.GCType.MINOR_GC;
            }

            @Override
            public void process(GCInfo gcInfo) {
                System.out.println(DefaultGCLogHandBack.getInstance().handLog(gcInfo));
            }
        });
        //noinspection unused
        Object a = new byte[1024][1024];
        System.gc();
        for (int i = 0; i < 10; i++) {
            System.out.println(getSystemLoadAverage());
            TimeUnit.SECONDS.sleep(1);
            //noinspection UnusedAssignment
            a = new byte[1024][1024];
        }
        System.exit(0);
    }

}