package cn.lichengwu.test.basic;

import org.junit.Test;

import java.util.TreeMap;

/**
 * TreeMapTest
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-05-13 上午8:25
 */
public class TreeMapTest {

    @Test
    public void test(){
        TreeMap<Integer,String>  map = new TreeMap<>();

        for(int i=0;i<100;i++){
            map.put(i*2,""+i);
        }

        System.out.println(map.get(5));

    }

}
