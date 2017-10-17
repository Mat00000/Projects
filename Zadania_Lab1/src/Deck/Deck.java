package Deck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	public static ArrayList<Card> cards = new ArrayList<>();
	private static int n;
	
	public Deck(int amount) {
		if(amount==52) {
			n=51;
			for(int i=0; i<4; i++) {
				for(int j=0; j<13; j++) {
					cards.add(new Card(Value.values()[j], Suit.values()[i]));
				}
			}
		}
		else if(amount==32) {
			n=31;
			for(int i=0; i<4; i++) {
				for(int j=5; j<13; j++) {
					cards.add(new Card(Value.values()[j], Suit.values()[i]));
				}
			}
		}
		else {
			n=23;
			for(int i=0; i<4; i++) {
				for(int j=7; j<13; j++) {
					cards.add(new Card(Value.values()[j], Suit.values()[i]));
				}
			}
		}
	}
	
	public static void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	public static void handOutCard() {
		System.out.println(cards.get(n).toStr());
	}
	
	public static String outCard() {
		return cards.get(n).toStr();
	}
	
	public static void showDeck() {
		for(int l=0;l <= n;l++) {
			System.out.println(cards.get(l).toStr());
		}
	}
	
	public String outError() {
		return "Error";
	}
	
	public static void sortDeck() {
		Collections.sort(cards);
	}
}
