package cn.lichengwu.test.basic;

import junit.framework.Assert;

import org.junit.Test;

/**
 * TryCatchFinallyTest
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-01-29 4:44 PM
 */
public class TryCatchFinallyTest {

    @Test
    public void test1() {
        try {
            System.out.println("try");
        } finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test2() {
        Assert.assertEquals(multiReturn(), 0);
    }

    private int multiReturn() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}
