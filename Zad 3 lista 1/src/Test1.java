import org.junit.Assert;

import junit.framework.TestCase;

public class Test1 extends TestCase {

	@org.junit.Test
	public void test() {
		Deck d = new Deck(52);
		d.Sorddeck();
		Assert.assertEquals("as Pik", d.Cardout().properties());
		
	}
	@org.junit.Test
	public void test2(){
		
		Deck d = new Deck(32);
		Assert.assertNotNull(d.Cardout());
	}
	
	@org.junit.Test
	public void test3(){
		
		Deck d = new Deck(33);
		Assert.assertNull(d.Cardout());
	}
	
	@org.junit.Test
	public void test4(){
		
		Deck d = new Deck(52);
		Assert.assertTrue(d.isvalid());
	}
	@Test
	public void test7() {
		
	}
	
	@org.junit.Test
	public void test5(){
		
		Deck d = new Deck(0);
		Assert.assertFalse(d.isvalid());
	}

}
