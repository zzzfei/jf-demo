package junit.test;

import org.junit.Test;

public class OtherTest {
	
	@Test
	public void testLong(){
		Long l1 = new Long("1");
		Long l2 = new Long("1");
		
		System.out.println(l1.equals(l2));
		System.out.println(l1 == l2);
	}
	
	@Test
	public void test01(){
		System.out.println(-6%5);
	}
	
	@Test
	public void test02(){
		int a = 10;
		int b = a++;
		System.out.println(a);
		System.out.println(a);
		System.out.println(a);
		System.out.println(a);
		System.out.println(a);
	
		
	}
}
