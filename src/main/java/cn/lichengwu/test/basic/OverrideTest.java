/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.basic;

import org.junit.Test;

/**
 * 成员变量测试
 * 
 * @author lichengwu
 * @created 2012-7-25
 * 
 * @version 1.0
 */
public class OverrideTest {

	class A {
		int i = 0;
		
		public A(){
			i=1;
			System.out.println(getValue());
		}
		
		public int getValue(){
			return i;
		}
		
		public void methodA(){
			System.out.println("A.methodA()");
		}
		
		public void m(int i){
			System.out.println("int");
		}
		
		public void m(Integer i){
			System.out.println("Integer");
		}
		
		public void m(Object i){
			System.out.println("Object");
		}
		
		public void m(A i){
			System.out.println("A");
		}
		public void m(B i){
			System.out.println("B");
		}
	}

	class B extends A {
		int i = 1;
		
		int j;
		
		public B (){
			j=2;
		}
		
		/**
		 * @see cn.lichengwu.test.basic.OverrideTest.A#getValue()
		 */
		@Override
		public int getValue() {
		    return j;
		}
		
		@Override
		public void methodA() {
		    System.out.println("B.methodA()");
		}
	}
	
	@Test
	public void memberVariable(){
		A a = new OverrideTest().new B();
		System.out.println(a.i);
		B b = new OverrideTest().new B();
		System.out.println(b.i);
		System.out.println("===============");
		a.methodA();
		b.methodA();
		
		a.m(b);
		b.m(a);
	}
	
	@Test
	public void initTest(){
		@SuppressWarnings("unused")
        B b = new OverrideTest().new B();
	}

}
