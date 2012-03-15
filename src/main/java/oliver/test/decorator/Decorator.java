/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.decorator;

/**
 * 装饰者
 * 
 * @author lichengwu
 * @created 2012-2-18
 * 
 * @version 1.0
 */
public abstract class Decorator extends Component {

	Component component;

	public Decorator(Component component) {
		this.component = component;
	}
}
