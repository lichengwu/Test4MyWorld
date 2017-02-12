package cn.lichengwu.test.gc;

/**
 * JVM常量定义
 *
 * @author 佐井
 * @version 1.0
 * @created 2015-04-15 12:35
 */
public interface JVMConstant {

    enum MemoryPool {
        DEF_NEW_EDEN("Eden Space"),
        DEF_NEW_SURVIVOR("Survivor Space"),
        PAR_NEW_EDEN("Par Eden Space"),
        PAR_NEW_SURVIVOR("Par Survivor Space"),
        PS_EDEN("PS Eden Space"),
        PS_SURVIVOR("PS Survivor Space"),
        G1_EDEN("G1 Eden Space"),
        G1_SURVIVOR("G1 Survivor Space"),
        SERIAL_TENURED("Tenured Gen"),
        CMS_TENURED("CMS Old Gen"),
        PS_TENURED("PS Old Gen"),
        G1_TENURED("G1 Old Gen"),
        CODE_CACHE("Code Cache"),
        COMPRESSED_CLASS_SPACE("Compressed Class Space"),
        METASPACE("Metaspace");

        MemoryPool(String name) {
            this.name = name;
        }

        private String name;


        public String getName() {
            return name;
        }

        public static MemoryPool nameOf(String name) {
            for (MemoryPool gcType : values()) {
                if (gcType.getName().equals(name)) {
                    return gcType;
                }
            }
            return null;
        }
    }

    enum GCType {
        MINOR_GC("minor GC"),

        MAJOR_GC("major GC");

        GCType(String name) {
            this.name = name;
        }

        private String name;


        public String getName() {
            return name;
        }

        public static GCType nameOf(String name) {
            for (GCType gcType : values()) {
                if (gcType.getName().equals(name)) {
                    return gcType;
                }
            }
            return null;
        }
    }
}
