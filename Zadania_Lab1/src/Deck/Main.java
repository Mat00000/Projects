package Deck;

import java.util.Scanner;
public class Main {
	static int amount, x;
	public static void main(String[]args) {
		Deck str = new Deck(0);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose deck (52, 32, 24)");
		amount=Integer.parseInt(scan.nextLine());
		if(amount==52) {
			new Deck(52);
			System.out.println("You created a deck");
			int var=0;
			while(var==0) {
				System.out.println("\nChoose option:\n1 - shuffle\n2 - hand out card\n3 - sort\n4 - show deck\n5 - exit");
				x=Integer.parseInt(scan.nextLine());
				if(x==1) {
					Deck.shuffleDeck();
				}
				else if(x==2) {
					Deck.handOutCard();
				}
				else if(x==3) {
					Deck.sortDeck();
				}
				else if(x==4) {
					Deck.showDeck();
				}
				else if(x==5){
					var=1;
				}
				else {
					str.outError();
				}
			}
		}
		else if(amount==32) {
			new Deck(32);
			System.out.println("You created a deck");
			int var=0;
			while(var==0) {
				System.out.println("\nChoose option:\n1 - shuffle\n2 - hand out card\n3 - sort\n4 - show deck\n5 - exit");
				x=Integer.parseInt(scan.nextLine());
				if(x==1) {
					Deck.shuffleDeck();
				}
				else if(x==2) {
					Deck.handOutCard();
				}
				else if(x==3) {
					Deck.sortDeck();
				}
				else if(x==4) {
					Deck.showDeck();
				}
				else if(x==5){
					var=1;
				}
				else {
					str.outError();
				}
			}
		}
		else if(amount==24) {
			new Deck(24);
			System.out.println("You created a deck");
			int var=0;
			while(var==0) {
				System.out.println("\nChoose option:\n1 - shuffle\n2 - hand out card\n3 - sort\n4 - show deck\n5 - exit");
				x=Integer.parseInt(scan.nextLine());
				if(x==1) {
					Deck.shuffleDeck();
				}
				else if(x==2) {
					Deck.handOutCard();
				}
				else if(x==3) {
					Deck.sortDeck();
				}
				else if(x==4) {
					Deck.showDeck();
				}
				else if(x==5){
					var=1;
				}
				else {
					str.outError();
				}
			}
		}
		else {
			str.outError();
			System.exit(0);
		}
	}
}
