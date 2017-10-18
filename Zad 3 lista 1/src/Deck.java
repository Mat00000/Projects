import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Deck {
	
	private static Card[] tab;
 
	private int numberofcards;
 
	public Deck(int numberofcards)
	{
		if(numberofcards==52 || numberofcards==32 || numberofcards==24)
		{
			this.numberofcards=numberofcards;
			tab = new Card[this.numberofcards];
		}
		else
		{
			System.out.println("niepoprawna ilosc kart");
			return;
		}
  
  FillDeck();
 
	}
 
	public Card takeout(int index)
	{
		return tab[index];
	
	}
 
	public void delete()
	{
		tab = null;
	}
 
	public boolean isvalid()
	{
		if(numberofcards==0){return false;}
		else{return true;}
	}
	private void FillDeck()
	{
		int index = 0;
		for(int i=13 - numberofcards/4; i<13; i++)
		{
			for(int j=0; j<4; j++)
			{
				tab[index] = new Card(Karta.values()[i], Kolor.values()[j]);
				index++;
			}
		}
	}
 
	public void showDeck()
	{
		for(int l=0; l<numberofcards; l++)
		{
			System.out.println(tab[l].properties());
		}
	}
 
	public void shuffle()
	{
		List<Card> list = Arrays.asList(tab);
		Collections.shuffle(list);
		tab = (Card[]) list.toArray();
	}
 
	public void Sorddeck()
	{
		Arrays.sort(tab);
	}
 
	public Card Cardout()
	{
		if(numberofcards != 0)
		{
			Card c = tab[tab.length-1];
			System.out.println(c.properties());
			return c;
		}
		else
		{
			return null;
		}
	}
 
	public static void main(String [] args)
	{
		Deck d = new Deck(52);
		d.showDeck();
		System.out.println("----------");
		d.shuffle();
		d.showDeck();
		System.out.println("----------");
		d.Sorddeck();
		d.showDeck();
		System.out.println("----------");
		d.Cardout();
	}
 
}