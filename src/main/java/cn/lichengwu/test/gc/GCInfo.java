package cn.lichengwu.test.gc;

import java.io.Serializable;
import java.lang.management.MemoryUsage;
import java.util.Map;

/**
 * GC info
 *
 * @author 佐井
 * @version 1.0
 * @created 2015-04-15 12:36
 */
public class GCInfo implements Serializable {

    private static final long serialVersionUID = 6756903035423619623L;

    /**
     * unique id
     */
    private long id;

    /**
     * the time when gc begin
     */
    private long startTime;

    /**
     * gc end time
     */
    private long endTime;

    /**
     * the reason cause gc occur
     */
    private String gcCause;

    /**
     * {@linkplain GCInfo}
     */
    private JVMConstant.GCType gcType;

    /**
     * name of gc
     */
    private String gcName;

    private Map<JVMConstant.MemoryPool, MemoryUsage> usageBeforeGc;

    private Map<JVMConstant.MemoryPool, MemoryUsage> usageAfterGc;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getGcCause() {
        return gcCause;
    }

    public void setGcCause(String gcCause) {
        this.gcCause = gcCause;
    }

    public JVMConstant.GCType getGcType() {
        return gcType;
    }

    public void setGcType(JVMConstant.GCType gcType) {
        this.gcType = gcType;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public Map<JVMConstant.MemoryPool, MemoryUsage> getUsageBeforeGc() {
        return usageBeforeGc;
    }

    public void setUsageBeforeGc(Map<JVMConstant.MemoryPool, MemoryUsage> usageBeforeGc) {
        this.usageBeforeGc = usageBeforeGc;
    }

    public Map<JVMConstant.MemoryPool, MemoryUsage> getUsageAfterGc() {
        return usageAfterGc;
    }

    public void setUsageAfterGc(Map<JVMConstant.MemoryPool, MemoryUsage> usageAfterGc) {
        this.usageAfterGc = usageAfterGc;
    }

    public long getDuration() {
        return this.endTime - this.startTime;
    }
}
