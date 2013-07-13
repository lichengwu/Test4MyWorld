/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.command;

import org.junit.Test;

/**
 *
 * @author lichengwu
 * @created 2012-2-22
 *
 * @version 1.0
 */
public class TestCommand {

	@Test
	public void test(){
		Light light = new Light("light");
		LightOnCommand on = new LightOnCommand(light);
		LightOffCommand off = new LightOffCommand(light);
		CommadController controller = new CommadController();
		controller.setCommand(0, on, off);
		controller.ButtonOn(0);
		controller.ButtonOff(0);
	}
}
