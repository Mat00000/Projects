package Deck;

public class Card implements Comparable<Card> {
	private Value cardValue;
	private Suit cardSuit;
	
	public Card(Value value, Suit suit) {
		cardValue=value;
		cardSuit=suit;
	}
	
	public Value getCardValue() {
		return cardValue;
	}
	
	public Suit getCardSuit() {
		return cardSuit;
	}
	
	public String toStr() {
		return cardValue.toString() + " " + cardSuit.toString();
	}

	@Override
	public int compareTo(Card a) {
		int compareValue = cardValue.compareTo(a.cardValue);
		if(compareValue==0) {
			return cardSuit.compareTo(a.cardSuit);
		}
		else {
			return compareValue;
		}
	}
	
}
