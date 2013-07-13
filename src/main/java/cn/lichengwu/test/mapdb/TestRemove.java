package cn.lichengwu.test.mapdb;

import lichengwu.lang.Random;
import cn.lichengwu.test.common.BaseTest;
import cn.lichengwu.test.common.Repeat;
import org.junit.Test;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.StoreHeap;

import java.util.*;

/**
 * TestRemove
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-05-02 10:39 AM
 */
public class TestRemove extends BaseTest {


    public static Map<String, Integer> hashMap = new HashMap<>();
    public static Map<String, Integer> treeMap = new TreeMap<>();

    public static final int MAX_ITEM_COUNT = 1000000;

    static List<String> removeList = new ArrayList<>();

    public static DB DB;


    static {
        DB = new DB(new StoreHeap());
        BTreeMap<Object, Object> dbMap = DB.createTreeMap("test", 32, false, false, null, null, null);
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_ITEM_COUNT; i++) {
            int value = Random.rangeRandom(0, MAX_ITEM_COUNT);
            String key = randomString(10);
            hashMap.put(key, value);
            treeMap.put(key, value);
            dbMap.put(key, value);
            if (i % 5 == 0) {
                removeList.add(key);
            }
        }
        System.out.println("init time used:" + (System.currentTimeMillis() - start));
    }


    @Test
    public void doNothing() {

    }


    @Test
    @Repeat(1)
    public void testTreeMapRemove() {
        for(String key : removeList){
            treeMap.remove(key);
        }
    }

    @Test
    @Repeat(1)
    public void testHashMapRemove() {
        for(String key : removeList){
            hashMap.remove(key);
        }
    }

    @Test
    @Repeat(1)
    public void testMapDBRemove() {
        BTreeMap<String, Integer> dbMap = DB.getTreeMap("test");
        for(String key : removeList){
            dbMap.remove(key);
        }
    }

}
