package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Deck.Card;
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
	@Test(timeout=10)
	public void testSortDeck_32() {
		Deck deck1 = new Deck(32);
		Deck deck2 = new Deck(32);
		deck1.sortDeck();
		deck2.sortDeck();
		Card card1 = deck1.cards.get(20);
		Card card2 = deck2.cards.get(20);
		Assert.assertSame(card1, card2);;
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
	
	
	
	/*@SuppressWarnings("static-access")
	@Test(expected=error)
	public void test() {
		d.cards.clear();
	}*/
	
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
	