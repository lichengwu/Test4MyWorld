package oliver.test.algorithm;

import oliver.lang.Random;
import oliver.test.common.BaseTest;
import oliver.test.common.Repeat;
import org.junit.Test;
import org.mapdb.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * tree map test
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-04-28 1:06 PM
 */
public class TestBTree extends BaseTest {

    public static Map<String, Integer> STORE_DATA = new TreeMap<>();

    public static final int MAX_ITEM_COUNT = 1000000;


    static {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_ITEM_COUNT; i++) {
            STORE_DATA.put(randomString(10), Random.rangeRandom(0, MAX_ITEM_COUNT));
        }
        System.out.println("init time used:" + (System.currentTimeMillis() - start));
    }


    @Test
    @Repeat
    public void testTreeMap() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : STORE_DATA.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }

    }


    @Test
    public void testMemoryDB() {
        DB inMemory = new DB(new StoreHeap());
        Map treeMap = inMemory.createTreeMap("test", 32, false, false, null, null, null);
        for (Map.Entry<String, Integer> entry : STORE_DATA.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
    }

    public void testRemoveFromMapDB(){

    }

    @Test
    public void testHashMap() {
        Map<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> entry : STORE_DATA.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }



}
