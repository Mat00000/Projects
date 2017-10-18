package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Deck.Deck;
import junit.framework.Assert;

public class DeckTest {
	Deck d;
	
	@Before
	public void init() {
		d = new Deck(52);
		
	}
	
	@Before
    public void setUp() throws Exception {
    }
	
	@SuppressWarnings("static-access")
	@Test(timeout=100)
	public void testDeckIsEmpty_32() {
		Deck deck = new Deck(32);
		boolean b = deck.cards.isEmpty();
		Assert.assertFalse(b);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testShuffleDeck() {
		Deck deck1 = new Deck(52);
		Deck deck2 = new Deck(52);
		deck1.shuffleDeck();
		deck2.shuffleDeck();
		String card1 = deck1.cards.get(1).toStr();
		String card2 = deck2.cards.get(1).toStr();
		Assert.assertEquals(card1, card2);
	}
	
	@SuppressWarnings("static-access")
	@Ignore
	public void testOutCard() {
		Deck deck = new Deck(32);
		deck.sortDeck();
		Assert.assertEquals("As Spade", deck.outCard());	
	}
	
	@Test 
	public void testOdKolegi() {
		Deck deck = new Deck(32);
		boolean b = deck.cards.isEmpty();
		Assert.assertTrue(b);
	}
	
	@Test
	public void testNotNullDeck() {
		Assert.assertNotNull(d);
	}
	@Test
	public void testOutError() {
		Assert.assertEquals("Error", d.outError());
	}
	
	@After
    public void tearDown() throws Exception {
    }
}
	