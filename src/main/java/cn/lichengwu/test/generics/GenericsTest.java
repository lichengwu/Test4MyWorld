/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * 泛型测试
 *
 * @author lichengwu
 * @created 2012-6-17
 *
 * @version 1.0
 */

public class GenericsTest {

	/**
	 * 泛型擦除测试
	 * 
	 * @author lichengwu
	 * @created 2012-6-17
	 *
	 */
	@Test
	public void test1(){
		List<Integer> list = new ArrayList<Integer>();
		unsafeAdd(list, "11");
		unsafeAdd(list, new Date());
		printList(list);
		
	}
	
	@Test
	public void test2(){
		List<Object> list = new ArrayList<Object>();
		list.add("sss");
		list.add(11);
	}
	
	@Test
	public void test3(){
		List<?> genList = genList();
		System.out.println(genList.get(0).getClass());
		System.out.println(genList.get(1).getClass());
		
	}
	
	@Test
	public void test4(){
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Double> set2 = new HashSet<Double>();
		@SuppressWarnings("unused")
        Set<Number> union = GenericsTest.<Number>union(set1, set2);
		
	}
	
	@Test
	public void test5(){
		System.out.println(Arrays.asList(1,2,3,4));
		List<int[]> asList = Arrays.asList(new int[]{1,2,3,4});
		System.out.println(asList);
		
	}
	
	
	@SuppressWarnings("unchecked")
    private void unsafeAdd(@SuppressWarnings("rawtypes") List list,Object obj){
		list.add(obj);
	}
	
	@SuppressWarnings("rawtypes")
    private void printList(List list){
		for(Object obj : list){
			System.out.println(obj);
		}
	}
	
	@SuppressWarnings("unchecked")
    private List<?> genList(){
		@SuppressWarnings("rawtypes")
        List list = new ArrayList();
		list.add(111);
		list.add("ssss");
		return list;
	}
	
	public static<E> Set<E> union(Set<? extends E> set1 , Set<? extends E> set2){
		return null;
	}
}
