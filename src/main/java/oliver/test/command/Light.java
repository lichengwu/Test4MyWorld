/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.command;

/**
 * 
 * @author lichengwu
 * @created 2012-2-22
 * 
 * @version 1.0
 */
public class Light {

	private String name;

	public void on() {
		System.out.println(name + ":on");
	}

	public void off() {
		System.out.println(name + ":off");
	}

	public Light(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
