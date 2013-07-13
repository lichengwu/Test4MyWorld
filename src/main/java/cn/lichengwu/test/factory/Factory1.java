package cn.lichengwu.test.factory;

public class Factory1 implements AbstractFactory {

	@Override
	public Product createProductA() {
		return new ProductA1();
	}

	@Override
	public Product createProductB() {
		return new ProductB1();
	}

}
