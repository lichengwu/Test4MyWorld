package cn.lichengwu.test.gc;

/**
 * 垃圾回收监听器
 *
 * @author 佐井
 * @version 1.0
 * @created 2015-04-12 21:30
 */
public interface GCListener {

    JVMConstant.GCType getGCType();

    void process(GCInfo gcInfo);


}
