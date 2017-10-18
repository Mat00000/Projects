import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
public class Test2 {
	
	Deck d;
	@Before
	public  void initialize(){	
		 
	 d = new Deck(52);
	 d.Sorddeck();
	}

	@Test
	public void test() {
		
		org.junit.Assert.assertEquals("as Pik",d.Cardout().properties());
	
	}
	
	@Test(timeout=100)
	public void time()
	{
		
		d.Sorddeck();
	}
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test2()
	{
		int index = 52;
		d.takeout(index);
		
	}
	

	
	@Ignore 
	@Test
	public void toignore()
	{
		
	}
	
	@After
	public void Delete()
	{
		d.delete();
	}
	

}
