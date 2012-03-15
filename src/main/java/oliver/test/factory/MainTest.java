package oliver.test.factory;

import org.junit.Test;

public class MainTest {

	@Test
	public void test1(){
		Client client1 = new Client(new Factory1());
		client1.print();
		Client client2 = new Client(new Factory2());
		client2.print();
	}
	
}
