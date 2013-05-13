package oliver.test.mapdb;

import oliver.lang.Random;
import oliver.test.common.BaseTest;
import oliver.test.common.Repeat;
import org.junit.Test;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.StoreHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO 方法说明
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-05-02 10:39 AM
 */
public class TestPut extends BaseTest {


    public static Map<String, Integer> STORE_MAP = new HashMap<>();

    public static final int MAX_ITEM_COUNT = 1000000;

    public static DB DB;


    static {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_ITEM_COUNT; i++) {
            STORE_MAP.put(randomString(10), Random.rangeRandom(0, MAX_ITEM_COUNT));
        }
        // init Map
        DB = new DB(new StoreHeap());
        DB.createTreeMap("test", 32, false, false, null, null, null);
        System.out.println("init time used:" + (System.currentTimeMillis() - start));
    }


    @Test
    public void doNothing() {

    }


    @Test
    @Repeat(1)
    public void testTreeMapPut(){
        Map<String,Integer> map = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : STORE_MAP.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    @Test
    @Repeat(1)
    public void testHashMapPut(){
        Map<String,Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> entry : STORE_MAP.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    @Test
    @Repeat(1)
    public void testMapDBPut() {
        BTreeMap<String, Integer> map = DB.getTreeMap("test");
        for (Map.Entry<String, Integer> entry : STORE_MAP.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

}
