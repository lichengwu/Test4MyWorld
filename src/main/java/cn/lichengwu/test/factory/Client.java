package cn.lichengwu.test.factory;

public class Client {
	
	private AbstractFactory factory;
	
	public Client(AbstractFactory factory){
		this.factory = factory;
	}
	
	public void print(){
		System.out.println(factory.createProductA().getName());
		System.out.println(factory.createProductB().getName());
	}
}
