/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.benchmark;

import org.junit.Test;

/**
 * 自动拆箱装箱测试
 *
 * @author lichengwu
 * @created 2012-6-17
 *
 * @version 1.0
 */
public class AutoBoxingPerformance {

	/**
	 * 自动装箱 耗时18.680s
	 * 
	 * @author lichengwu
	 * @created 2012-6-17
	 *
	 */
	@Test
	public void boxing(){
		Long result =0L;
		for(int i=0;i<Integer.MAX_VALUE;i++){
			result+=1;
		}
		System.out.println(result);
	}
	
	/**
	 * 自动拆箱 耗时 3.733s
	 * 
	 * @author lichengwu
	 * @created 2012-6-17
	 *
	 */
	@Test
	public void unboxing(){
		long result =0L;
		for(int i=0;i<Integer.MAX_VALUE;i++){
			result+=Integer.valueOf(1);
		}
		System.out.println(result);
	}
	
	/**
	 * 基本类型测试 耗时 0.252s
	 * 
	 * @author lichengwu
	 * @created 2012-6-17
	 *
	 */
	@Test
	public void base(){
		long result =0L;
		for(int i=0;i<Integer.MAX_VALUE;i++){
			result+=1;
		}
		System.out.println(result);
	}
}
