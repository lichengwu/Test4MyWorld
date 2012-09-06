/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.basic;

import org.junit.Test;

/**
 * Java移位运算测试
 * <p>
 * <b>环境:</b>32位虚拟机
 * </p>
 * 
 * @author lichengwu
 * @created 2012-9-6
 * 
 * @version 1.0
 */
public class Shifting {

	/**
	 * 左移测试
	 * 
	 * @author lichengwu
	 * @created 2012-9-6
	 * 
	 */
	@Test
	public void test1() {
		int var1 = 2;
		// 如果移动的位数超过了该类型的最大位数，那么编译器会对移动的位数取模。如对int型移动33位，实际上只移动了33%32=1位。
		System.out.println("var1<<33 = " + (var1 << 33));
		// 当左移的运算数是byte 和short类型时，将自动把这些类型扩大为 int 型。
		short var2 = 1;
		System.out.println("var2<<17 = " + (var2 << 17));
		byte var3 = 1;
		System.out.println("var3<<17 = " + (var3 << 17));
		// 移动的位数超过了该类型的最大位数， 如果移进高阶位（31或63位），那么该值将变为负值
		int var4 = 0x7FFFFFFF;
		System.out.println("var4<<1 = " + (var4 << 1));
	}

	/**
	 * 右移测试
	 * 
	 * @author lichengwu
	 * @created 2012-9-6
	 * 
	 */
	@Test
	public void test2() {
		// 按二进制形式把所有的数字向右移动对应的位数，低位移出(舍弃)，高位的空位补符号位，即正数补零，负数补1
		// 当右移的运算数是byte 和short类型时，将自动把这些类型扩大为 int 型
		int var1 = 33;
		System.out.println("var1 >> 1 = " + (var1 >> 2));
		// 无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位
		int var2 = 0xFFFFFFFE;
		System.out.println("var2 >>> 1 = " + (var2 >>> 1));

	}

}
