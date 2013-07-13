/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.enumeration;

import java.util.EnumSet;

import org.junit.Test;

/**
 *
 * @author lichengwu
 * @created 2012-6-24
 *
 * @version 1.0
 */
public class EnumTest {

	
	@Test
	public void test1(){
		EnumSet<Type> set = EnumSet.noneOf(Type.class);
		System.out.println(set);
	}
	
	@Test
	public void test2(){
		EnumSet<Type> set = EnumSet.allOf(Type.class);
		System.out.println(set);
	}
	
	@Test
	public void test3(){
		EnumSet<Type> set1 = EnumSet.of(Type.type1);
		EnumSet<Type> set2 = EnumSet.complementOf(set1);
		System.out.println(set2);
	}
	
	enum Type{
		type1,type2,type3;
	}
}
