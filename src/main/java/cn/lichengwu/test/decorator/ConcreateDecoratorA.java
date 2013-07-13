/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package cn.lichengwu.test.decorator;

/**
 * 装饰者A
 *
 * @author lichengwu
 * @created 2012-2-18
 *
 * @version 1.0
 */
public class ConcreateDecoratorA extends Decorator {

	public ConcreateDecoratorA(Component component) {
	    super(component);
    }

	public int getValue() {
	    return component.getValue()+2;
    }

}
