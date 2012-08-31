/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.basic;

import org.junit.Test;

/**
 * 
 * @author lichengwu
 * @created 2012-8-11
 * 
 * @version 1.0
 */
public class TryCatch {

	public int test1() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}

	}

	public String test2(String x) {
		try {
			x = "a";
			return x;
		} catch (Exception e) {
			x = "b";
			return x;
		} finally {
			x = "c";
		}
	}

	@Test
	public void test() {
		System.out.println(test1());
		String i = "-";
		System.out.println(test2(i));
		System.out.println(i);
	}

}
