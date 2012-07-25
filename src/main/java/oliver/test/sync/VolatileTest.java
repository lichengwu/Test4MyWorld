/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.sync;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 * @author lichengwu
 * @created 2012-7-2
 * 
 * @version 1.0
 */
public class VolatileTest {

	@Test
	public void test1() throws InterruptedException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					WithOutIncrease.one();

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					WithOutIncrease.two();

			}
		}).start();
		TimeUnit.SECONDS.sleep(100000000);
	}
	@Test
	public void test2() throws InterruptedException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					WithIncrease.one();

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					WithIncrease.two();

			}
		}).start();
		TimeUnit.SECONDS.sleep(100000000);
	}
}



class WithOutIncrease {
	static int i = 0;
	static int j = 0;

	public static void one() {
		i++;
		j++;
	}

	public static void two() {
		System.out.println("i=" + i + "j=" + j);
	}
}

class WithIncrease {
	volatile static int i = 0;
	volatile static int j = 0;

	public static void one() {
		i++;
		j++;
	}

	public static void two() {
		System.out.println("i=" + i + "j=" + j);
	}
}
