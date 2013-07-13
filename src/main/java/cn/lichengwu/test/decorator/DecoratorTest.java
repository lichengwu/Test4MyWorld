package cn.lichengwu.test.decorator;

import org.junit.Assert;
import org.junit.Test;

public class DecoratorTest {

	@Test
	public void test(){
		ConcreateDecoratorA a = new ConcreateDecoratorA(new ConcreateDecoratorB(new ConcreteComponent()));
		Assert.assertTrue(a.getValue()==8);
		ConcreateDecoratorB b = new ConcreateDecoratorB(new ConcreateDecoratorB(new ConcreateDecoratorA(new ConcreteComponent())));
		Assert.assertTrue(b.getValue()==9);
	}
	
}
