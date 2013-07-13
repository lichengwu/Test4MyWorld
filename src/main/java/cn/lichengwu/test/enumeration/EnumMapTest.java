/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.enumeration;

import java.util.EnumMap;

import cn.lichengwu.test.enumeration.EnumTest.Type;

import org.junit.Test;

/**
 *
 * @author lichengwu
 * @created 2012-6-24
 *
 * @version 1.0
 */
public class EnumMapTest {
	
	@Test
	public void test1(){
		EnumMap<Type,Object> map = new EnumMap<Type, Object>(Type.class);
		map.put(Type.type1, "type1");
		System.out.println(map);
	}
}
