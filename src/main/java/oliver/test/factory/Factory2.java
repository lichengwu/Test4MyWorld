package oliver.test.factory;

public class Factory2 implements AbstractFactory {

	@Override
    public Product createProductA() {
	    return new ProductA2();
    }

	@Override
    public Product createProductB() {
	    return new ProductB2();
    }

}
