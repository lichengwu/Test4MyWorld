/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.decorator;

/**
 * 装饰者B
 * 
 * @author lichengwu
 * @created 2012-2-18
 * 
 * @version 1.0
 */
public class ConcreateDecoratorB extends Decorator {

	public ConcreateDecoratorB(Component component) {
		super(component);
	}

	public int getValue() {
		return component.getValue() + 1;
	}

}
